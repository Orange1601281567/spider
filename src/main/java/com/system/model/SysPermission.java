package com.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 權限信息
 * </p>
 *
 * @author Ablett_Chen
 * @since 2020-12-17
 */
@ApiModel(value="SysPermission对象", description="權限信息")
public class SysPermission extends Model<SysPermission> {

    private static final long serialVersionUID = 1L;

      @TableId(type = IdType.AUTO)
  	@ApiModelProperty(value = "",name = "id")
    private Integer id;

      /**
     * 權限名稱
     */
  	@ApiModelProperty(value = "權限名稱",name = "name")
    private String name;

      /**
     * 描述
     */
  	@ApiModelProperty(value = "描述",name = "description")
    private String description;

      /**
     * 父id
     */
  	@ApiModelProperty(value = "父id",name = "pid")
    private Integer pid;

    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public String getName() {
        return name;
    }

      public void setName(String name) {
          this.name = name;
      }
    
    public String getDescription() {
        return description;
    }

      public void setDescription(String description) {
          this.description = description;
      }
    
    public Integer getPid() {
        return pid;
    }

      public void setPid(Integer pid) {
          this.pid = pid;
      }

      public static final String ID = "id";

      public static final String NAME = "name";

      public static final String DESCRIPTION = "description";

      public static final String PID = "pid";

      @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "SysPermission{" +
              "id=" + id +
                  ", name=" + name +
                  ", description=" + description +
                  ", pid=" + pid +
              "}";
    }
}
