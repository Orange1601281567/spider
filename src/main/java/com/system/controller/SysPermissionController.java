package com.system.controller;


import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.system.model.ReturnT;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.system.model.SysPermission;
import javax.annotation.Resource;
import com.system.service.ISysPermissionService;
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
 * 權限信息 前端控制器
 * </p>
 *
 * @author Ablett_Chen
 * @since 2020-12-17
 */
 
@Api(value="SysPermissionController",tags={"權限信息"})
@RestController
@RequestMapping("/sysPermission")
public class SysPermissionController {
	@Resource
    public ISysPermissionService iSysPermissionService;
	
	@ApiOperation("添加權限信息")
    @PostMapping("/insert" )
    @Log(detail = "添加權限信息",operationUnit = OperationUnit.SYSTEM,operationType = OperationType.INSERT)
	public ReturnT<String> insert(@RequestBody SysPermission sysPermission) {
		boolean ret= iSysPermissionService.save(sysPermission);
		return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
	}
	
	@ApiOperation("更改權限信息")
    @PutMapping("/update" )
    @Log(detail = "更改權限信息",operationUnit = OperationUnit.SYSTEM,operationType = OperationType.UPDATE)
	public ReturnT<String> updateById(@RequestBody SysPermission sysPermission) {
		boolean ret= iSysPermissionService.updateById(sysPermission);
		return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
	}
	
	@ApiOperation("更改或添加權限信息")
    @PostMapping("/save" )
    @Log(detail = "更改或添加權限信息",operationUnit = OperationUnit.SYSTEM,operationType = OperationType.UPDATE)
	public ReturnT<String> save(@RequestBody SysPermission sysPermission) {
		if(sysPermission.getId()!=null){
			boolean ret= iSysPermissionService.updateById(sysPermission);
			return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
		}
		else{
			boolean ret= iSysPermissionService.save(sysPermission);
			return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
		}
	}
	
	@ApiOperation("刪除權限信息")
    @DeleteMapping("/{id}" )
    @Log(detail = "刪除權限信息",operationUnit = OperationUnit.SYSTEM,operationType = OperationType.DELETE)
	public ReturnT<String> delete(@PathVariable int id) {
		boolean ret= iSysPermissionService.removeById(id);
		return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
	}
	
	@ApiOperation("通過id查詢權限信息")
    @GetMapping("/{id}" )
	public ReturnT<SysPermission> selectById(@PathVariable int id) {
		ReturnT<SysPermission> ret=new ReturnT<SysPermission>(iSysPermissionService.getById(id));
		ret.setCount(1);
		ret.setCode(200);
		return ret;
	}
	
	@ApiOperation("查詢權限信息(不分頁)")
	@PostMapping("/list" )
	public ReturnT<List<SysPermission>> list(@RequestBody Map<String,Object> columnMap) {
		ReturnT<List<SysPermission>> result=new  ReturnT<List<SysPermission>>(null);
        result.setCount(iSysPermissionService.listByMap(columnMap).size());
        result.setData(iSysPermissionService.listByMap(columnMap));
        return result;
	}
	
	
	@ApiOperation("查詢權限信息(分頁)")
	@GetMapping("/pageList" )
	public ReturnT<List<SysPermission>> pageList(int limit,int page) {
        QueryWrapper<SysPermission> wrapper = new QueryWrapper<SysPermission>();
        Page<SysPermission> pages = new Page<SysPermission>(page,limit);
        IPage<SysPermission> iPage=iSysPermissionService.page(pages, wrapper);
        ReturnT<List<SysPermission>> result=new  ReturnT<List<SysPermission>>(null);
        result.setCode(0);
        result.setCount((int) iPage.getTotal());
        result.setData(iPage.getRecords());
        return result;
	}
	
	
}

