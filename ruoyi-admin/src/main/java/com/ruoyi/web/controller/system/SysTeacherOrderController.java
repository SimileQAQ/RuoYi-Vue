package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.SysTeacherProject;
import com.ruoyi.system.service.ISysTeacherProjectService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysOrder;
import com.ruoyi.system.service.ISysOrderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 教师订单管理Controller
 * 
 * @author ruoiyi
 * @date 2021-05-17
 */
@RestController
@RequestMapping("/system/teacherorder")
public class SysTeacherOrderController extends BaseController
{
    @Autowired
    private ISysOrderService sysOrderService;

    @Autowired
    private ISysTeacherProjectService sysTeacherProjectService;

    /**
     * 查询教师订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:teacherorder:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysOrder sysOrder)
    {
        startPage();
        String username = SecurityUtils.getUsername();
//        //查询工号为传入参数的 项目
//        SysTeacherProject sysTeacherProject = new SysTeacherProject();
//        sysTeacherProject.setUserId(username);
//        List<SysTeacherProject> sysTeacherProjects = sysTeacherProjectServicse.selectSysTeacherProjectList(sysTeacherProject);

        //订单申请人 查找自己的订单
        sysOrder.setUsername(username);
        //点击首先展示未审批订单
        if (sysOrder.getStatus() == null || sysOrder.getStatus().length() == 0) {
            sysOrder.setStatus("1");
        }
        List<SysOrder> list = sysOrderService.selectSysOrderList(sysOrder);
        return getDataTable(list);
    }

    /**
     * 导出教师订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:teacherorder:export')")
    @Log(title = "教师订单管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysOrder sysOrder)
    {
        List<SysOrder> list = sysOrderService.selectSysOrderList(sysOrder);
        ExcelUtil<SysOrder> util = new ExcelUtil<SysOrder>(SysOrder.class);
        return util.exportExcel(list, "教师订单管理数据");
    }

    /**
     * 获取教师订单管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:teacherorder:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") Long orderId)
    {
        return AjaxResult.success(sysOrderService.selectSysOrderById(orderId));
    }

    /**
     * 新增教师订单管理
     */
    @PreAuthorize("@ss.hasPermi('system:teacherorder:add')")
    @Log(title = "教师订单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysOrder sysOrder)
    {
        //获取项目表的 ID 去找project_id字段, 前端有bug直接传输project_id有错
        //在分别把归口单位和 Id 存入 实体
        String id = sysOrder.getProjectId();
        SysTeacherProject sysTeacherProject = sysTeacherProjectService.selectSysTeacherProjectById(Long.valueOf(id));

        String projectId = sysTeacherProject.getProjectId();
        String relevantDepartment = sysTeacherProject.getRelevantDepartment();



        sysOrder.setRelevantDepartment(relevantDepartment);
        sysOrder.setProjectId(projectId);


        return toAjax(sysOrderService.insertSysOrder(sysOrder));
    }

    /**
     * 修改教师订单管理
     */
    @PreAuthorize("@ss.hasPermi('system:teacherorder:edit')")
    @Log(title = "教师订单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysOrder sysOrder)
    {
        return toAjax(sysOrderService.updateSysOrder(sysOrder));
    }

    /**
     * 删除教师订单管理
     */
    @PreAuthorize("@ss.hasPermi('system:teacherorder:remove')")
    @Log(title = "教师订单管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds)
    {
        return toAjax(sysOrderService.deleteSysOrderByIds(orderIds));
    }
}
