package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysTeacherProject;

/**
 * 项目Service接口
 * 
 * @author ruoyi
 * @date 2021-05-23
 */
public interface ISysTeacherProjectService 
{
    /**
     * 查询项目
     * 
     * @param id 项目ID
     * @return 项目
     */
    public SysTeacherProject selectSysTeacherProjectById(Long id);

    /**
     * 查询项目列表
     * 
     * @param sysTeacherProject 项目
     * @return 项目集合
     */
    public List<SysTeacherProject> selectSysTeacherProjectList(SysTeacherProject sysTeacherProject);

    /**
     * 新增项目
     * 
     * @param sysTeacherProject 项目
     * @return 结果
     */
    public int insertSysTeacherProject(SysTeacherProject sysTeacherProject);

    /**
     * 修改项目
     * 
     * @param sysTeacherProject 项目
     * @return 结果
     */
    public int updateSysTeacherProject(SysTeacherProject sysTeacherProject);

    /**
     * 批量删除项目
     * 
     * @param ids 需要删除的项目ID
     * @return 结果
     */
    public int deleteSysTeacherProjectByIds(Long[] ids);

    /**
     * 删除项目信息
     * 
     * @param id 项目ID
     * @return 结果
     */
    public int deleteSysTeacherProjectById(Long id);
}
