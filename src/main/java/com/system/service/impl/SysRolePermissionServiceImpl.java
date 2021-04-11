package com.system.service.impl;

import com.system.model.SysRolePermission;
import com.system.mapper.SysRolePermissionMapper;
import com.system.service.ISysRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色權限關聯信息 服务实现类
 * </p>
 *
 * @author Ablett_Chen
 * @since 2020-12-17
 */
@Service("iSysRolePermissionService")
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements ISysRolePermissionService {
	 @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;
}
