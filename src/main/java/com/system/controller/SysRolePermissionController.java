package com.system.controller;


import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.system.model.ReturnT;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.system.model.SysRolePermission;
import javax.annotation.Resource;
import com.system.service.ISysRolePermissionService;
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
 * 角色權限關聯信息 前端控制器
 * </p>
 *
 * @author Ablett_Chen
 * @since 2020-12-17
 */
 
@Api(value="SysRolePermissionController",tags={"角色權限關聯信息"})
@RestController
@RequestMapping("/sysRolePermission")
public class SysRolePermissionController {
	@Resource
    public ISysRolePermissionService iSysRolePermissionService;
	
	@ApiOperation("添加角色權限關聯信息")
    @PostMapping("/insert" )
    @Log(detail = "添加角色權限關聯信息",operationUnit = OperationUnit.SYSTEM,operationType = OperationType.INSERT)
	public ReturnT<String> insert(@RequestBody SysRolePermission sysRolePermission) {
		boolean ret= iSysRolePermissionService.save(sysRolePermission);
		return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
	}
	
	@ApiOperation("更改角色權限關聯信息")
    @PutMapping("/update" )
    @Log(detail = "更改角色權限關聯信息",operationUnit = OperationUnit.SYSTEM,operationType = OperationType.UPDATE)
	public ReturnT<String> updateById(@RequestBody SysRolePermission sysRolePermission) {
		boolean ret= iSysRolePermissionService.updateById(sysRolePermission);
		return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
	}
	
	@ApiOperation("更改或添加角色權限關聯信息")
    @PostMapping("/save" )
    @Log(detail = "更改或添加角色權限關聯信息",operationUnit = OperationUnit.SYSTEM,operationType = OperationType.UPDATE)
	public ReturnT<String> save(@RequestBody SysRolePermission sysRolePermission) {
		if(sysRolePermission.getId()!=null){
			boolean ret= iSysRolePermissionService.updateById(sysRolePermission);
			return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
		}
		else{
			boolean ret= iSysRolePermissionService.save(sysRolePermission);
			return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
		}
	}
	
	@ApiOperation("刪除角色權限關聯信息")
    @DeleteMapping("/{id}" )
    @Log(detail = "刪除角色權限關聯信息",operationUnit = OperationUnit.SYSTEM,operationType = OperationType.DELETE)
	public ReturnT<String> delete(@PathVariable int id) {
		boolean ret= iSysRolePermissionService.removeById(id);
		return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
	}
	
	@ApiOperation("通過id查詢角色權限關聯信息")
    @GetMapping("/{id}" )
	public ReturnT<SysRolePermission> selectById(@PathVariable int id) {
		ReturnT<SysRolePermission> ret=new ReturnT<SysRolePermission>(iSysRolePermissionService.getById(id));
		ret.setCount(1);
		ret.setCode(200);
		return ret;
	}
	
	@ApiOperation("查詢角色權限關聯信息(不分頁)")
	@PostMapping("/list" )
	public ReturnT<List<SysRolePermission>> list(@RequestBody Map<String,Object> columnMap) {
		ReturnT<List<SysRolePermission>> result=new  ReturnT<List<SysRolePermission>>(null);
        result.setCount(iSysRolePermissionService.listByMap(columnMap).size());
        result.setData(iSysRolePermissionService.listByMap(columnMap));
        return result;
	}
	
	
	@ApiOperation("查詢角色權限關聯信息(分頁)")
	@GetMapping("/pageList" )
	public ReturnT<List<SysRolePermission>> pageList(int limit,int page) {
        QueryWrapper<SysRolePermission> wrapper = new QueryWrapper<SysRolePermission>();
        Page<SysRolePermission> pages = new Page<SysRolePermission>(page,limit);
        IPage<SysRolePermission> iPage=iSysRolePermissionService.page(pages, wrapper);
        ReturnT<List<SysRolePermission>> result=new  ReturnT<List<SysRolePermission>>(null);
        result.setCode(0);
        result.setCount((int) iPage.getTotal());
        result.setData(iPage.getRecords());
        return result;
	}
	
	
}

