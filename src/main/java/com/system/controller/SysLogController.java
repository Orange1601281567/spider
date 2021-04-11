package com.system.controller;


import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.system.model.ReturnT;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.system.model.SysLog;
import javax.annotation.Resource;
import com.system.service.ISysLogService;
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
 * 日志信息 前端控制器
 * </p>
 *
 * @author Ablett_Chen
 * @since 2020-12-17
 */
 
@Api(value="SysLogController",tags={"日志信息"})
@RestController
@RequestMapping("/sysLog")
public class SysLogController {
	@Resource
    public ISysLogService iSysLogService;
	
	@ApiOperation("查詢日志信息(不分頁)")
	@PostMapping("/list" )
	public ReturnT<List<SysLog>> list(@RequestBody Map<String,Object> columnMap) {
		ReturnT<List<SysLog>> result=new  ReturnT<List<SysLog>>(null);
        result.setCount(iSysLogService.listByMap(columnMap).size());
        result.setData(iSysLogService.listByMap(columnMap));
        return result;
	}
	
	
	@ApiOperation("查詢日志信息(分頁)")
	@GetMapping("/pageList" )
	public ReturnT<List<SysLog>> pageList(int limit,int page) {
        QueryWrapper<SysLog> wrapper = new QueryWrapper<SysLog>();
        Page<SysLog> pages = new Page<SysLog>(page,limit);
        IPage<SysLog> iPage=iSysLogService.page(pages, wrapper);
        ReturnT<List<SysLog>> result=new  ReturnT<List<SysLog>>(null);
        result.setCode(0);
        result.setCount((int) iPage.getTotal());
        result.setData(iPage.getRecords());
        return result;
	}
	
	
}

