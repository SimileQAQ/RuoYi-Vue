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
 * 财务订单管理Controller
 *
 * @author ruoyi
 * @date 2021-05-18
 */
@RestController
@RequestMapping("/system/financeorder")
public class SysFinanceOrderController extends BaseController
{
    @Autowired
    private ISysOrderService sysOrderService;

    /**
     * 查询财务订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:financeorder:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysOrder sysOrder)
    {
        startPage();
        if (sysOrder.getFinanceStatu()==null || sysOrder.getFinanceStatu().length()==0){
            sysOrder.setFinanceStatu("0");
        }
        List<SysOrder> list = sysOrderService.selectSysOrderList(sysOrder);
        return getDataTable(list);
    }

    /**
     * 导出财务订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:financeorder:export')")
    @Log(title = "财务订单管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysOrder sysOrder)
    {
        List<SysOrder> list = sysOrderService.selectSysOrderList(sysOrder);
        ExcelUtil<SysOrder> util = new ExcelUtil<SysOrder>(SysOrder.class);
        return util.exportExcel(list, "财务订单管理数据");
    }

    /**
     * 获取财务订单管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:financeorder:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysOrderService.selectSysOrderById(id));
    }

    /**
     * 新增财务订单管理
     */
    @PreAuthorize("@ss.hasPermi('system:financeorder:add')")
    @Log(title = "财务订单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysOrder sysOrder)
    {
        return toAjax(sysOrderService.insertSysOrder(sysOrder));
    }

    /**
     * 修改财务订单管理
     */
    @PreAuthorize("@ss.hasPermi('system:financeorder:edit')")
    @Log(title = "财务订单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysOrder sysOrder)
    {
        sysOrder.setFinanceAuditor(SecurityUtils.getUsername());
        // ==1为 同意 ==2为不同意 ==0 为未审核
        if(sysOrder.getFinanceStatu().equals("1")){
            //审核通过后把 进入资产管理处未审核状态   订单状态变为等待资产管理处审核
            sysOrder.setProcurementStatu("0");
            sysOrder.setStatus("3");
        }else if(sysOrder.getFinanceStatu().equals("2")){
            //不同意 把订单置为未通过
            sysOrder.setStatus("0");
        }

        return toAjax(sysOrderService.updateSysOrder(sysOrder));
    }

    /**
     * 删除财务订单管理
     */
    @PreAuthorize("@ss.hasPermi('system:financeorder:remove')")
    @Log(title = "财务订单管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysOrderService.deleteSysOrderByIds(ids));
    }
}
