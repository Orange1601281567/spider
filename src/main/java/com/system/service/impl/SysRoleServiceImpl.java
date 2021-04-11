package com.system.service.impl;

import com.system.model.SysRole;
import com.system.mapper.SysRoleMapper;
import com.system.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色信息 服务实现类
 * </p>
 *
 * @author Ablett_Chen
 * @since 2020-12-17
 */
@Service("iSysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
	 @Autowired
    private SysRoleMapper sysRoleMapper;
}
