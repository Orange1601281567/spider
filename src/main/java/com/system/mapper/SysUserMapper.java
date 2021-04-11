package com.system.mapper;

import com.system.model.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用戶信息 Mapper 接口
 * </p>
 *
 * @author Ablett_Chen
 * @since 2020-10-17
 */
 
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}
