package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysTeacherProjectMapper;
import com.ruoyi.system.domain.SysTeacherProject;
import com.ruoyi.system.service.ISysTeacherProjectService;

/**
 * 项目Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-23
 */
@Service
public class SysTeacherProjectServiceImpl implements ISysTeacherProjectService 
{
    @Autowired
    private SysTeacherProjectMapper sysTeacherProjectMapper;

    /**
     * 查询项目
     * 
     * @param id 项目ID
     * @return 项目
     */
    @Override
    public SysTeacherProject selectSysTeacherProjectById(Long id)
    {
        return sysTeacherProjectMapper.selectSysTeacherProjectById(id);
    }

    /**
     * 查询项目列表
     * 
     * @param sysTeacherProject 项目
     * @return 项目
     */
    @Override
    public List<SysTeacherProject> selectSysTeacherProjectList(SysTeacherProject sysTeacherProject)
    {
        return sysTeacherProjectMapper.selectSysTeacherProjectList(sysTeacherProject);
    }

    /**
     * 新增项目
     * 
     * @param sysTeacherProject 项目
     * @return 结果
     */
    @Override
    public int insertSysTeacherProject(SysTeacherProject sysTeacherProject)
    {
        return sysTeacherProjectMapper.insertSysTeacherProject(sysTeacherProject);
    }

    /**
     * 修改项目
     * 
     * @param sysTeacherProject 项目
     * @return 结果
     */
    @Override
    public int updateSysTeacherProject(SysTeacherProject sysTeacherProject)
    {
        return sysTeacherProjectMapper.updateSysTeacherProject(sysTeacherProject);
    }

    /**
     * 批量删除项目
     * 
     * @param ids 需要删除的项目ID
     * @return 结果
     */
    @Override
    public int deleteSysTeacherProjectByIds(Long[] ids)
    {
        return sysTeacherProjectMapper.deleteSysTeacherProjectByIds(ids);
    }

    /**
     * 删除项目信息
     * 
     * @param id 项目ID
     * @return 结果
     */
    @Override
    public int deleteSysTeacherProjectById(Long id)
    {
        return sysTeacherProjectMapper.deleteSysTeacherProjectById(id);
    }
}
