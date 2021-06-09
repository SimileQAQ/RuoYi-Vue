package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.SysTeacherProject;
import com.ruoyi.system.service.ISysTeacherProjectService;
import com.ruoyi.system.service.ISysUserService;
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
 * 归口订单管理Controller
 *
 * @author ruoyi
 * @date 2021-05-17
 */
@RestController
@RequestMapping("/system/relevantorder")
public class SysRelevantOrderController extends BaseController {
    @Autowired
    private ISysOrderService sysOrderService;

    @Autowired
    private ISysTeacherProjectService sysTeacherProjectService;

    @Autowired
    private ISysUserService userService;


    /**
     * 查询归口订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:relevantorder:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysOrder sysOrder) {
        startPage();
        if (sysOrder.getRelevantStatu() == null || sysOrder.getRelevantStatu().length() == 0) {
            sysOrder.setRelevantStatu("0");
        }
        //获取登录名 即工号
        String username = SecurityUtils.getUsername();
        //根据登录名查询部门  设置归口查询部门
        SysUser sysUser = userService.selectUserByUserName(username);
        if (!sysUser.getDept().getDeptName().equals("山东理工大学")){
            sysOrder.setRelevantDepartment(sysUser.getDept().getDeptName());
        }
        List<SysOrder> list = sysOrderService.selectSysOrderList(sysOrder);
        return getDataTable(list);
    }

    /**
     * 导出归口订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:relevantorder:export')")
    @Log(title = "归口订单管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysOrder sysOrder) {
        List<SysOrder> list = sysOrderService.selectSysOrderList(sysOrder);
        ExcelUtil<SysOrder> util = new ExcelUtil<SysOrder>(SysOrder.class);
        return util.exportExcel(list, "归口订单管理数据");
    }

    /**
     * 获取归口订单管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:relevantorder:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(sysOrderService.selectSysOrderById(id));
    }

    /**
     * 新增归口订单管理
     */
    @PreAuthorize("@ss.hasPermi('system:relevantorder:add')")
    @Log(title = "归口订单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysOrder sysOrder) {
        return toAjax(sysOrderService.insertSysOrder(sysOrder));
    }

    /**
     * 修改归口订单管理
     */
    @PreAuthorize("@ss.hasPermi('system:relevantorder:edit')")
    @Log(title = "归口订单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysOrder sysOrder) {
        //System.out.println(sysOrder+"------------before-------------------");
        sysOrder.setRelevantAuditor(SecurityUtils.getUsername());
        // ==1为 同意 ==2为不同意 ==0 为未审核
        String statu = sysOrder.getRelevantStatu();
        if (statu.equals("1")) {
            sysOrder.setFinanceStatu("0");
            sysOrder.setStatus("2");
        } else if (statu.equals("2")) {
            //不同意 把订单置为未通过
            sysOrder.setStatus("0");
        }
        //System.out.println(sysOrder+"------------after-------------------");

        return toAjax(sysOrderService.updateSysOrder(sysOrder));
    }

    /**
     * 删除归口订单管理
     */
    @PreAuthorize("@ss.hasPermi('system:relevantorder:remove')")
    @Log(title = "归口订单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(sysOrderService.deleteSysOrderByIds(ids));
    }
}
