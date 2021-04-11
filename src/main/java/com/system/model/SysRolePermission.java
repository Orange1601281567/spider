package com.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 角色權限關聯信息
 * </p>
 *
 * @author Ablett_Chen
 * @since 2020-12-17
 */
@ApiModel(value="SysRolePermission对象", description="角色權限關聯信息")
public class SysRolePermission extends Model<SysRolePermission> {

    private static final long serialVersionUID = 1L;

      /**
     * 主鍵
     */
        @TableId(type = IdType.AUTO)
  	@ApiModelProperty(value = "主鍵",name = "id")
    private Integer id;

      /**
     * 權限id
     */
  	@ApiModelProperty(value = "權限id",name = "permissionid")
    private Integer permissionid;

      /**
     * 角色id
     */
  	@ApiModelProperty(value = "角色id",name = "roleid")
    private Integer roleid;

    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public Integer getPermissionid() {
        return permissionid;
    }

      public void setPermissionid(Integer permissionid) {
          this.permissionid = permissionid;
      }
    
    public Integer getRoleid() {
        return roleid;
    }

      public void setRoleid(Integer roleid) {
          this.roleid = roleid;
      }

      public static final String ID = "id";

      public static final String PERMISSIONID = "permissionid";

      public static final String ROLEID = "roleid";

      @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "SysRolePermission{" +
              "id=" + id +
                  ", permissionid=" + permissionid +
                  ", roleid=" + roleid +
              "}";
    }
}
