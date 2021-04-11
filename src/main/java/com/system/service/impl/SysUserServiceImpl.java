package com.system.service.impl;

import com.system.model.SysUser;
import com.system.mapper.SysUserMapper;
import com.system.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用戶信息 服务实现类
 * </p>
 *
 * @author Ablett_Chen
 * @since 2020-10-17
 */
@Service("iSysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
	 @Autowired
    private SysUserMapper sysUserMapper;
}
