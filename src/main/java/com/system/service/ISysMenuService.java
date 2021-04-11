package com.system.service;

import com.system.model.SysMenu;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统菜单表 服务类
 * </p>
 *
 * @author Ablett_Chen
 * @since 2020-12-17
 */
public interface ISysMenuService extends IService<SysMenu> {
	//查詢菜單
	public Map<String, Object> menu();
}
