package com.system.secunity;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.system.model.SysPermission;
import com.system.model.SysRole;
import com.system.model.SysUser;
import com.system.model.SysUserRole;
import com.system.service.ISysPermissionService;
import com.system.service.ISysRoleService;
import com.system.service.ISysUserRoleService;
import com.system.service.ISysUserService;

/**
 * 自定义的认证用户获取服务类
 * @author Ablett_Chen
 *
 */
@Component("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Resource
    public ISysUserService iSysUserService;
	
	@Resource
    public ISysRoleService iSysRoleService;
	
	@Resource
    public ISysUserRoleService iSysUserRoleService;
	
	@Resource
    public ISysPermissionService iSysPermissionService;
    /**
     * 根据用户名获取认证用户信息
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("UserDetailsService没有接收到用户账号");
        } else {
            /**
             * 根据用户名查找用户信息
             */
        	
        	SysUser sysUser =new SysUser();
        	QueryWrapper<SysUser> queryWrapper=new QueryWrapper<SysUser>();
        	queryWrapper.eq("username", username);
        	sysUser = iSysUserService.getOne(queryWrapper);
            if(sysUser == null) {
                throw new UsernameNotFoundException(String.format("用户'%s'不存在", username));
            }
            QueryWrapper<SysUserRole> queryWrapper2=new QueryWrapper<SysUserRole>();
        	queryWrapper2.eq("userid", sysUser.getId());
        	SysUserRole sysUserRole= iSysUserRoleService.getOne(queryWrapper2);
        	
        	QueryWrapper<SysRole> queryWrapper3=new QueryWrapper<SysRole>();
        	queryWrapper3.eq("id", sysUserRole.getRoleid());
            List<SysRole> sysRoles= iSysRoleService.list(queryWrapper3);
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            if(sysRoles.size()>0) {
            	for(SysRole sysRole:sysRoles) {
                	//設置角色
                    grantedAuthorities.add(new SimpleGrantedAuthority(sysRole.getName()));
                    //查詢角色的權限信息
                    QueryWrapper<SysPermission> queryWrapper4=new QueryWrapper<SysPermission>();
                	queryWrapper4.eq("pid", sysRole.getId());
                    List<SysPermission> sysPermissions= iSysPermissionService.list(queryWrapper4);
                    //循環角色的權限信息
                    if(sysPermissions.size()>0) {
                    	for(SysPermission sysPermission:sysPermissions) {
                    		//設置權限
                    		grantedAuthorities.add(new SimpleGrantedAuthority(sysPermission.getName()));
                        }
                    }
                }
            }
          
            /**
             * 创建一个用于认证的用户对象并返回，包括：用户名，密码，角色
             */
            return new User(sysUser.getUsername(), sysUser.getPassword(), grantedAuthorities);
        }
    }
}