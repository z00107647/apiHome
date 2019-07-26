package apihome.modules.global.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * Created by zyanycall@gmail.com on 2019-07-25 20:01.
 */
@Data
@TableName("test_host")
public class TestHostEntity {

    /**
     * host id
     */
    @TableId
    private Long hostId;

    /**
     * 项目id，关联ID
     */
    private Long projectId;

    /**
     * host名称
     */
    @NotBlank(message="host名称不能为空")
    private String hostName;

    /**
     * host ip port
     */
    @NotBlank(message="host IP PORT不能为空")
    private String hostIp;

    /**
     * host状态  0：正常状态（默认状态）  1：禁止关闭
     */
    private Integer status = 0;

    /**
     * host类型  0：全局host  1：接口测试项目使用   2：性能测试项目使用
     */
    @NotBlank(message="host类型不能为空")
    private String hostType;

    /**
     * host信息描述
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


    public Long getHostId() {
        return hostId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getHostType() {
        return hostType;
    }

    public void setHostType(String hostType) {
        this.hostType = hostType;
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
