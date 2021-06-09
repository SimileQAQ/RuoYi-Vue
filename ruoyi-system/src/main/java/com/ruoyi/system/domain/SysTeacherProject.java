package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目对象 sys_teacher_project
 * 
 * @author ruoyi
 * @date 2021-05-23
 */
public class SysTeacherProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 部门 */
    @Excel(name = "部门")
    private String comp;

    /** 项目编号 */
    @Excel(name = "项目编号")
    private String projectId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 负责人 */
    @Excel(name = "负责人")
    private String projectUser;

    /** 负责人ID */
    @Excel(name = "负责人ID")
    private String userId;

    /** 归口部门 */
    @Excel(name = "归口部门")
    private String relevantDepartment;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setComp(String comp) 
    {
        this.comp = comp;
    }

    public String getComp() 
    {
        return comp;
    }
    public void setProjectId(String projectId) 
    {
        this.projectId = projectId;
    }

    public String getProjectId() 
    {
        return projectId;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setProjectUser(String projectUser) 
    {
        this.projectUser = projectUser;
    }

    public String getProjectUser() 
    {
        return projectUser;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setRelevantDepartment(String relevantDepartment) 
    {
        this.relevantDepartment = relevantDepartment;
    }

    public String getRelevantDepartment() 
    {
        return relevantDepartment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("comp", getComp())
            .append("projectId", getProjectId())
            .append("projectName", getProjectName())
            .append("projectUser", getProjectUser())
            .append("userId", getUserId())
            .append("relevantDepartment", getRelevantDepartment())
            .toString();
    }
}
