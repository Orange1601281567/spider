package com.system.controller;


import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.system.model.ReturnT;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.system.model.SysUser;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.system.service.ISysUserService;
import com.utils.Excel.ExcelUtils;

import java.util.List;
import java.util.Map;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import io.swagger.annotations.*;
import com.system.model.Log;
import com.system.model.OperationType;
import com.system.model.OperationUnit;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
/**
 * <p>
 * 用戶信息 前端控制器
 * </p>
 *
 * @author Ablett_Chen
 * @since 2020-10-17
 */
 
@Api(value="SysUserController",tags={"用戶信息"})
@RestController
@RequestMapping("/sysUser")
public class SysUserController {
	@Resource
    public ISysUserService iSysUserService;
	
	@ApiOperation("添加用戶信息")
    @PostMapping("/insert" )
	@CacheEvict(value="userlist",allEntries = true)
    @Log(detail = "添加用戶信息",operationUnit = OperationUnit.SYSTEM,operationType = OperationType.INSERT)
	public ReturnT<String> insert(@RequestBody SysUser sysUser) {
		boolean ret= iSysUserService.save(sysUser);
		return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
	}
	
	@ApiOperation("更改用戶信息")
    @PutMapping("/update" )
    @Log(detail = "更改用戶信息",operationUnit = OperationUnit.SYSTEM,operationType = OperationType.UPDATE)
	public ReturnT<String> updateById(@RequestBody SysUser sysUser) {
		boolean ret= iSysUserService.updateById(sysUser);
		return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
	}
	
	@ApiOperation("刪除用戶信息")
    @DeleteMapping("/{id}" )
    @Log(detail = "刪除用戶信息",operationUnit = OperationUnit.SYSTEM,operationType = OperationType.DELETE)
	public ReturnT<String> delete(@PathVariable int id) {
		boolean ret= iSysUserService.removeById(id);
		return ret==true?ReturnT.SUCCESS:ReturnT.FAIL;
	}
	
	@ApiOperation("通過id查詢用戶信息")
    @GetMapping("/{id}")
//	@PreAuthorize("hasRole('ADMIN')")
	public ReturnT<SysUser> selectById(@PathVariable int id) {
		ReturnT<SysUser> ret=new ReturnT<SysUser>(iSysUserService.getById(id));
		return ret;
	}
	
	@ApiOperation("查詢用戶信息(不分頁)")
	@PostMapping("/list" )
	@Cacheable(value="userlist")
	public List<SysUser> list(@RequestBody Map<String,Object> columnMap) {
		return iSysUserService.listByMap(columnMap);
	}
	
	
	@ApiOperation("查詢用戶信息(分頁)")
	@GetMapping("/pageList" )
	@Cacheable(value="userlist")
	public ReturnT<List<SysUser>> pageList(int limit,int page) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<SysUser>();
        Page<SysUser> pages = new Page<SysUser>(page,limit);
        IPage<SysUser> iPage=iSysUserService.page(pages, wrapper);
        ReturnT<List<SysUser>> result=new  ReturnT<List<SysUser>>(null);
        result.setCode(0);
        result.setCount((int) iPage.getTotal());
        result.setData(iPage.getRecords());
        return result;
	}
	
	
    /**
    * 單個導出
    */
	@ApiOperation("流文件測試")
    @GetMapping("/export")
    public void export(HttpServletRequest req, HttpServletResponse res) throws IOException{
        String fileName="測試導出表";
        //填充projects数据
        String columnNames[]={"序號","SN", "裝箱時間", "裝箱工號", "綫別","去向"};//列名
        String keys[] = {"no", "sn", "inputtime", "op", "inputline","there"};//map中的key
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("sheetName", "sheet1");
            listmap.add(map);
            ExcelUtils.createWorkBook(listmap,keys,columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        res.reset();
        res.setContentType("application/vnd.ms-excel;charset=utf-8");
        res.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xlsx").getBytes(), "iso-8859-1"));
        res.setHeader("Access-Control-Allow-Origin", "*");
        ServletOutputStream out = res.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }
	
	
}

