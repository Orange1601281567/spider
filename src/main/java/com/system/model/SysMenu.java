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
@ApiModel(value="SysMenu对象", description="系统菜单表")
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

      /**
     * ID
     */
        @TableId(type = IdType.AUTO)
  	@ApiModelProperty(value = "ID",name = "id")
    private Integer id;

      /**
     * 父ID
     */
  	@ApiModelProperty(value = "父ID",name = "pid")
    private Integer pid;

      /**
     * 名称
     */
  	@ApiModelProperty(value = "名称",name = "title")
    private String title;

      /**
     * 菜单图标
     */
  	@ApiModelProperty(value = "菜单图标",name = "icon")
    private String icon;

      /**
     * 链接
     */
  	@ApiModelProperty(value = "链接",name = "href")
    private String href;

      /**
     * 链接打开方式
     */
  	@ApiModelProperty(value = "链接打开方式",name = "target")
    private String target;

      /**
     * 菜单排序
     */
  	@ApiModelProperty(value = "菜单排序",name = "sort")
    private Integer sort;

      /**
     * 状态(0:禁用,1:启用)
     */
  	@ApiModelProperty(value = "状态(0:禁用,1:启用)",name = "status")
    private Boolean status;

      /**
     * 备注信息
     */
  	@ApiModelProperty(value = "备注信息",name = "remark")
    private String remark;

      /**
     * 创建时间
     */
  	@ApiModelProperty(value = "创建时间",name = "createAt")
    private Date createAt;

      /**
     * 更新时间
     */
  	@ApiModelProperty(value = "更新时间",name = "updateAt")
    private Date updateAt;

      /**
     * 删除时间
     */
  	@ApiModelProperty(value = "删除时间",name = "deleteAt")
    private Date deleteAt;
    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public Integer getPid() {
        return pid;
    }

      public void setPid(Integer pid) {
          this.pid = pid;
      }
    
    public String getTitle() {
        return title;
    }

      public void setTitle(String title) {
          this.title = title;
      }
    
    public String getIcon() {
        return icon;
    }

      public void setIcon(String icon) {
          this.icon = icon;
      }
    
    public String getHref() {
        return href;
    }

      public void setHref(String href) {
          this.href = href;
      }
    
    public String getTarget() {
        return target;
    }

      public void setTarget(String target) {
          this.target = target;
      }
    
    public Integer getSort() {
        return sort;
    }

      public void setSort(Integer sort) {
          this.sort = sort;
      }
    
    public Boolean getStatus() {
        return status;
    }

      public void setStatus(Boolean status) {
          this.status = status;
      }
    
    public String getRemark() {
        return remark;
    }

      public void setRemark(String remark) {
          this.remark = remark;
      }
    
    public Date getCreateAt() {
        return createAt;
    }

      public void setCreateAt(Date createAt) {
          this.createAt = createAt;
      }
    
    public Date getUpdateAt() {
        return updateAt;
    }

      public void setUpdateAt(Date updateAt) {
          this.updateAt = updateAt;
      }
    
    public Date getDeleteAt() {
        return deleteAt;
    }

      public void setDeleteAt(Date deleteAt) {
          this.deleteAt = deleteAt;
      }


	public static final String ID = "id";

      public static final String PID = "pid";

      public static final String TITLE = "title";

      public static final String ICON = "icon";

      public static final String HREF = "href";

      public static final String TARGET = "target";

      public static final String SORT = "sort";

      public static final String STATUS = "status";

      public static final String REMARK = "remark";

      public static final String CREATE_AT = "create_at";

      public static final String UPDATE_AT = "update_at";

      public static final String DELETE_AT = "delete_at";

      @Override
    protected Serializable pkVal() {
          return this.id;
      }

	@Override
	public String toString() {
		return "SysMenu [id=" + id + ", pid=" + pid + ", title=" + title + ", icon=" + icon + ", href=" + href
				+ ", target=" + target + ", sort=" + sort + ", status=" + status + ", remark=" + remark + ", createAt="
				+ createAt + ", updateAt=" + updateAt + ", deleteAt=" + deleteAt + "]";
	}

}
