package com.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 系统菜单表
 * </p>
 *
 * @author Ablett_Chen
 * @since 2020-12-17
 */
@ApiModel(value="SysMenuTree", description="樹狀菜單實體類")
public class SysMenuTree extends SysMenu {
	@ApiModelProperty(value = "子菜單",name = "child")
    private List<SysMenu> child;

	public List<SysMenu> getChild() {
		return child;
	}

	public void setChild(List<SysMenu> child) {
		this.child = child;
	}
	
	
	
}
