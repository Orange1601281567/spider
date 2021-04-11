package com.system.controller;


import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.system.model.ReturnT;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.system.model.SysRole;
import javax.annotation.Resource;
import com.system.service.ISysRoleService;
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
 * 角色信息 前端控制器
 * </p>
 *
 * @author Ablett_Chen
 * @since 2020-12-17
 */
 
@Api(value="SysRoleController",tags={"角色信息"})
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {
	@Resource
    public ISysRoleService iSysRoleService;
	
	@ApiOperation("添加角色信息")
    @PostMapping("/insert" )
    @Log(detail = "添加角色信息",operationUnit = OperationUnit.SYSTEM,operationType = OperationType.INSERT)
	public ReturnT<String> insert(@RequestBody SysRole sysRole) {
		boolean ret= iSysRoleService.save(sysRole);
		return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
	}
	
	@ApiOperation("更改角色信息")
    @PutMapping("/update" )
    @Log(detail = "更改角色信息",operationUnit = OperationUnit.SYSTEM,operationType = OperationType.UPDATE)
	public ReturnT<String> updateById(@RequestBody SysRole sysRole) {
		boolean ret= iSysRoleService.updateById(sysRole);
		return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
	}
	
	@ApiOperation("更改或添加角色信息")
    @PostMapping("/save" )
    @Log(detail = "更改或添加角色信息",operationUnit = OperationUnit.SYSTEM,operationType = OperationType.UPDATE)
	public ReturnT<String> save(@RequestBody SysRole sysRole) {
		if(sysRole.getId()!=null){
			boolean ret= iSysRoleService.updateById(sysRole);
			return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
		}
		else{
			boolean ret= iSysRoleService.save(sysRole);
			return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
		}
	}
	
	@ApiOperation("刪除角色信息")
    @DeleteMapping("/{id}" )
    @Log(detail = "刪除角色信息",operationUnit = OperationUnit.SYSTEM,operationType = OperationType.DELETE)
	public ReturnT<String> delete(@PathVariable int id) {
		boolean ret= iSysRoleService.removeById(id);
		return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
	}
	
	@ApiOperation("通過id查詢角色信息")
    @GetMapping("/{id}" )
	public ReturnT<SysRole> selectById(@PathVariable int id) {
		ReturnT<SysRole> ret=new ReturnT<SysRole>(iSysRoleService.getById(id));
		ret.setCount(1);
		ret.setCode(200);
		return ret;
	}
	
	@ApiOperation("查詢角色信息(不分頁)")
	@PostMapping("/list" )
	public ReturnT<List<SysRole>> list(@RequestBody Map<String,Object> columnMap) {
		ReturnT<List<SysRole>> result=new  ReturnT<List<SysRole>>(null);
        result.setCount(iSysRoleService.listByMap(columnMap).size());
        result.setData(iSysRoleService.listByMap(columnMap));
        return result;
	}
	
	
	@ApiOperation("查詢角色信息(分頁)")
	@GetMapping("/pageList" )
	public ReturnT<List<SysRole>> pageList(int limit,int page) {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<SysRole>();
        Page<SysRole> pages = new Page<SysRole>(page,limit);
        IPage<SysRole> iPage=iSysRoleService.page(pages, wrapper);
        ReturnT<List<SysRole>> result=new  ReturnT<List<SysRole>>(null);
        result.setCode(0);
        result.setCount((int) iPage.getTotal());
        result.setData(iPage.getRecords());
        return result;
	}
	
	
}

