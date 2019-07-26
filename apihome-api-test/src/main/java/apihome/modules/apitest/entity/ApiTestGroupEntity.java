package apihome.modules.apitest.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * Created by zyanycall@gmail.com on 2019-07-26 15:20.
 */
@Data
@TableName("api_test_group")
public class ApiTestGroupEntity {

    /**
     * 主键id
     */
    @TableId
    private Long groupId;

    /**
     * 项目id，关联ID
     */
    private Long projectId;

    /**
     * 分组名称
     */
    @NotBlank(message="分组名称不能为空")
    private String groupName;

    /**
     * 状态  0：正常状态（默认状态）  1：禁止关闭
     */
    private Integer status = 0;

    /**
     * 分组级别
     */
    private Integer groupLevel;

    /**
     * 描述
     */
    private String remark;

    /**
     * 提交的人
     */
    private String createBy;

    /**
     * 修改的人
     */
    private String modifyBy;

    /**
     * 提交的时间
     */
    private Date gmtCreate;

    /**
     * 更新的时间
     */
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModify;


    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGroupLevel() {
        return groupLevel;
    }

    public void setGroupLevel(Integer groupLevel) {
        this.groupLevel = groupLevel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }
}
