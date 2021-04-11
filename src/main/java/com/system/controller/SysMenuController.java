package com.system.controller;


import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.system.model.ReturnT;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.system.model.SysMenu;
import javax.annotation.Resource;
import com.system.service.ISysMenuService;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import io.swagger.annotations.*;
import com.system.model.Log;
import com.system.model.OperationType;
import com.system.model.OperationUnit;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 系统菜单表 前端控制器
 * </p>
 *
 * @author Ablett_Chen
 * @since 2020-12-17
 */
 
@Api(value="SysMenuController",tags={"系统菜单表"})
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {
	@Resource
    public ISysMenuService iSysMenuService;
	
	@ApiOperation("添加系统菜单表")
    @PostMapping("/insert" )
    @Log(detail = "添加系统菜单表",operationUnit = OperationUnit.SYSTEM,operationType = OperationType.INSERT)
	public ReturnT<String> insert(@RequestBody SysMenu sysMenu) {
		boolean ret= iSysMenuService.save(sysMenu);
		return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
	}
	
	@ApiOperation("更改系统菜单表")
    @PutMapping("/update" )
    @Log(detail = "更改系统菜单表",operationUnit = OperationUnit.SYSTEM,operationType = OperationType.UPDATE)
	public ReturnT<String> updateById(@RequestBody SysMenu sysMenu) {
		boolean ret= iSysMenuService.updateById(sysMenu);
		return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
	}
	
	@ApiOperation("更改或添加系统菜单表")
    @PostMapping("/save" )
    @Log(detail = "更改或添加系统菜单表",operationUnit = OperationUnit.SYSTEM,operationType = OperationType.UPDATE)
	public ReturnT<String> save(@RequestBody SysMenu sysMenu) {
		if(sysMenu.getId()!=null){
			boolean ret= iSysMenuService.updateById(sysMenu);
			return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
		}
		else{
			boolean ret= iSysMenuService.save(sysMenu);
			return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
		}
	}
	
	@ApiOperation("刪除系统菜单表")
    @DeleteMapping("/{id}" )
    @Log(detail = "刪除系统菜单表",operationUnit = OperationUnit.SYSTEM,operationType = OperationType.DELETE)
	public ReturnT<String> delete(@PathVariable int id) {
		boolean ret= iSysMenuService.removeById(id);
		return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
	}
	
	@ApiOperation("通過id查詢系统菜单表")
    @GetMapping("/{id}" )
	public ReturnT<SysMenu> selectById(@PathVariable int id) {
		ReturnT<SysMenu> ret=new ReturnT<SysMenu>(iSysMenuService.getById(id));
		ret.setCount(1);
		ret.setCode(200);
		return ret;
	}
	
	@ApiOperation("獲取系统菜单表")
    @GetMapping("/menu" )
	public Map<String,Object> menu() {
		return iSysMenuService.menu();
	}
	
	@ApiOperation("查詢系统菜单表(不分頁)")
	@GetMapping("/list" )
	public Map<String, Object> list() {
		QueryWrapper<SysMenu> queryWrapper=new QueryWrapper<SysMenu>();
		queryWrapper.orderByAsc("sort");
		List<SysMenu> list=(List<SysMenu>) iSysMenuService.list(queryWrapper);
		//将结果存入map进行传送
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("code", 0);
        result.put("msg", "");
        result.put("count",list.size());
        result.put("data", list);
        return result;
	}
	
	
	@ApiOperation("查詢系统菜单表(分頁)")
	@GetMapping("/pageList" )
	public ReturnT<List<SysMenu>> pageList(int limit,int page) {
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<SysMenu>();
        Page<SysMenu> pages = new Page<SysMenu>(page,limit);
        IPage<SysMenu> iPage=iSysMenuService.page(pages, wrapper);
        ReturnT<List<SysMenu>> result=new  ReturnT<List<SysMenu>>(null);
        result.setCode(0);
        result.setCount((int) iPage.getTotal());
        result.setData(iPage.getRecords());
        return result;
	}
	
	
}

