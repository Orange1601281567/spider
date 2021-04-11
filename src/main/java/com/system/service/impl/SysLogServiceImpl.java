package com.system.service.impl;

import com.system.model.SysLog;
import com.system.mapper.SysLogMapper;
import com.system.service.ISysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志信息 服务实现类
 * </p>
 *
 * @author Ablett_Chen
 * @since 2020-12-17
 */
@Service("iSysLogService")
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {
	 @Autowired
    private SysLogMapper sysLogMapper;
}
