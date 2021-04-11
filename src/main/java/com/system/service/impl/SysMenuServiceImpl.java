package com.system.service.impl;

import com.system.model.SysMenu;
import com.system.model.SysMenuTree;
import com.system.model.TreeUtil;
import com.system.mapper.SysMenuMapper;
import com.system.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统菜单表 服务实现类
 * </p>
 *
 * @author Ablett_Chen
 * @since 2020-12-17
 */
@Service("iSysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
	 @Autowired
    private SysMenuMapper sysMenuMapper;
	 
		@Override
		public Map<String, Object> menu() {
			  Map<String, Object> map = new HashMap<>(16);
		        Map<String,Object> home = new HashMap<>(16);
		        Map<String,Object> logo = new HashMap<>(16);
		        List<SysMenu> menuList = sysMenuMapper.selectList(null);
		        List<SysMenuTree> menuInfo = new ArrayList<>();
		        for (SysMenu e : menuList) {
		        	SysMenuTree sysMenuTree = new SysMenuTree();
		        	sysMenuTree.setId(e.getId());
		        	sysMenuTree.setPid(e.getPid());
		        	sysMenuTree.setHref(e.getHref());
		        	sysMenuTree.setTitle(e.getTitle());
		        	sysMenuTree.setIcon(e.getIcon());
		        	sysMenuTree.setTarget(e.getTarget());
		            menuInfo.add(sysMenuTree);
		        }
		        map.put("menuInfo", TreeUtil.toTree(menuInfo, 0));
		        home.put("title","首页");
		        home.put("href","page/welcome-1.html");//控制器路由,自行定义
		        logo.put("title","后台管理系统");
		        logo.put("image","images/logo.png");//静态资源文件路径,可使用默认的logo.png 
		        logo.put("href","");
		        map.put("homeInfo", home);
		        map.put("logoInfo", logo);
		        return map;
		}
}
