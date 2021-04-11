package com.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 用戶角色關連信息
 * </p>
 *
 * @author Ablett_Chen
 * @since 2020-12-17
 */
@ApiModel(value="SysUserRole对象", description="用戶角色關連信息")
public class SysUserRole extends Model<SysUserRole> {

    private static final long serialVersionUID = 1L;

      /**
     * 主鍵
     */
        @TableId(type = IdType.AUTO)
  	@ApiModelProperty(value = "主鍵",name = "id")
    private Integer id;

      /**
     * 角色id
     */
  	@ApiModelProperty(value = "角色id",name = "roleid")
    private Integer roleid;

      /**
     * 用戶id
     */
  	@ApiModelProperty(value = "用戶id",name = "userid")
    private Integer userid;

    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public Integer getRoleid() {
        return roleid;
    }

      public void setRoleid(Integer roleid) {
          this.roleid = roleid;
      }
    
    public Integer getUserid() {
        return userid;
    }

      public void setUserid(Integer userid) {
          this.userid = userid;
      }

      public static final String ID = "id";

      public static final String ROLEID = "roleid";

      public static final String USERID = "userid";

      @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "SysUserRole{" +
              "id=" + id +
                  ", roleid=" + roleid +
                  ", userid=" + userid +
              "}";
    }
}
