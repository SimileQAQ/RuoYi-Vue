package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
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
 * 总订单管理Controller
 *
 * @author ruoiyi
 * @date 2021-05-13
 */

@RestController
@RequestMapping("/system/order")
public class SysOrderController extends BaseController {
    @Autowired
    private ISysOrderService sysOrderService;

    /**
     * 查询总订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysOrder sysOrder) {
        startPage();
        List<SysOrder> list = sysOrderService.selectSysOrderList(sysOrder);
        return getDataTable(list);
    }

    /**
     * 导出总订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:export')")
    @Log(title = "总订单管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysOrder sysOrder) {
        List<SysOrder> list = sysOrderService.selectSysOrderList(sysOrder);
        ExcelUtil<SysOrder> util = new ExcelUtil<SysOrder>(SysOrder.class);
        return util.exportExcel(list, "总订单管理数据");
    }

    /**
     * 获取总订单管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:order:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") Long orderId) {
        return AjaxResult.success(sysOrderService.selectSysOrderById(orderId));
    }

    /**
     * 新增总订单管理
     */
    @PreAuthorize("@ss.hasPermi('system:order:add')")
    @Log(title = "总订单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysOrder sysOrder) {


        return toAjax(sysOrderService.insertSysOrder(sysOrder));
    }

    /**
     * 修改总订单管理
     */
    @PreAuthorize("@ss.hasPermi('system:order:edit')")
    @Log(title = "总订单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysOrder sysOrder) {
        return toAjax(sysOrderService.updateSysOrder(sysOrder));
    }

    /**
     * 删除总订单管理
     */
    @PreAuthorize("@ss.hasPermi('system:order:remove')")
    @Log(title = "总订单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds) {
        return toAjax(sysOrderService.deleteSysOrderByIds(orderIds));
    }
}
