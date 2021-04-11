package com.system.service.impl;

import com.system.model.SysUserRole;
import com.system.mapper.SysUserRoleMapper;
import com.system.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用戶角色關連信息 服务实现类
 * </p>
 *
 * @author Ablett_Chen
 * @since 2020-12-17
 */
@Service("iSysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {
	 @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
}
