package com.system.controller;


import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.system.model.ReturnT;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.system.model.SysUserRole;
import javax.annotation.Resource;
import com.system.service.ISysUserRoleService;
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
 * 用戶角色關連信息 前端控制器
 * </p>
 *
 * @author Ablett_Chen
 * @since 2020-12-17
 */
 
@Api(value="SysUserRoleController",tags={"用戶角色關連信息"})
@RestController
@RequestMapping("/sysUserRole")
public class SysUserRoleController {
	@Resource
    public ISysUserRoleService iSysUserRoleService;
	
	@ApiOperation("添加用戶角色關連信息")
    @PostMapping("/insert" )
    @Log(detail = "添加用戶角色關連信息",operationUnit = OperationUnit.SYSTEM,operationType = OperationType.INSERT)
	public ReturnT<String> insert(@RequestBody SysUserRole sysUserRole) {
		boolean ret= iSysUserRoleService.save(sysUserRole);
		return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
	}
	
	@ApiOperation("更改用戶角色關連信息")
    @PutMapping("/update" )
    @Log(detail = "更改用戶角色關連信息",operationUnit = OperationUnit.SYSTEM,operationType = OperationType.UPDATE)
	public ReturnT<String> updateById(@RequestBody SysUserRole sysUserRole) {
		boolean ret= iSysUserRoleService.updateById(sysUserRole);
		return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
	}
	
	@ApiOperation("更改或添加用戶角色關連信息")
    @PostMapping("/save" )
    @Log(detail = "更改或添加用戶角色關連信息",operationUnit = OperationUnit.SYSTEM,operationType = OperationType.UPDATE)
	public ReturnT<String> save(@RequestBody SysUserRole sysUserRole) {
		if(sysUserRole.getId()!=null){
			boolean ret= iSysUserRoleService.updateById(sysUserRole);
			return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
		}
		else{
			boolean ret= iSysUserRoleService.save(sysUserRole);
			return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
		}
	}
	
	@ApiOperation("刪除用戶角色關連信息")
    @DeleteMapping("/{id}" )
    @Log(detail = "刪除用戶角色關連信息",operationUnit = OperationUnit.SYSTEM,operationType = OperationType.DELETE)
	public ReturnT<String> delete(@PathVariable int id) {
		boolean ret= iSysUserRoleService.removeById(id);
		return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
	}
	
	@ApiOperation("通過id查詢用戶角色關連信息")
    @GetMapping("/{id}" )
	public ReturnT<SysUserRole> selectById(@PathVariable int id) {
		ReturnT<SysUserRole> ret=new ReturnT<SysUserRole>(iSysUserRoleService.getById(id));
		ret.setCount(1);
		ret.setCode(200);
		return ret;
	}
	
	@ApiOperation("查詢用戶角色關連信息(不分頁)")
	@PostMapping("/list" )
	public ReturnT<List<SysUserRole>> list(@RequestBody Map<String,Object> columnMap) {
		ReturnT<List<SysUserRole>> result=new  ReturnT<List<SysUserRole>>(null);
        result.setCount(iSysUserRoleService.listByMap(columnMap).size());
        result.setData(iSysUserRoleService.listByMap(columnMap));
        return result;
	}
	
	
	@ApiOperation("查詢用戶角色關連信息(分頁)")
	@GetMapping("/pageList" )
	public ReturnT<List<SysUserRole>> pageList(int limit,int page) {
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<SysUserRole>();
        Page<SysUserRole> pages = new Page<SysUserRole>(page,limit);
        IPage<SysUserRole> iPage=iSysUserRoleService.page(pages, wrapper);
        ReturnT<List<SysUserRole>> result=new  ReturnT<List<SysUserRole>>(null);
        result.setCode(0);
        result.setCount((int) iPage.getTotal());
        result.setData(iPage.getRecords());
        return result;
	}
	
	
}

