package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import io.github.biezhi.ome.OhMyEmail;
import io.github.biezhi.ome.SendMailException;
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

import static io.github.biezhi.ome.OhMyEmail.SMTP_QQ;

/**
 * 资产管理处订单管理Controller
 * 
 * @author ruoyi
 * @date 2021-05-18
 */
@RestController
@RequestMapping("/system/procurementorder")
public class SysProcurementOrderController extends BaseController
{
    @Autowired
    private ISysOrderService sysOrderService;

    /**
     * 查询资产管理处订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:procurementorder:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysOrder sysOrder)
    {
        //System.out.println(sysOrder.getProcurementStatu());
        startPage();
        if (sysOrder.getProcurementStatu()==null || sysOrder.getProcurementStatu().length()==0){
            sysOrder.setProcurementStatu("0");
        }
        List<SysOrder> list = sysOrderService.selectSysOrderList(sysOrder);
        return getDataTable(list);
    }

    /**
     * 导出资产管理处订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:procurementorder:export')")
    @Log(title = "资产管理处订单管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysOrder sysOrder)
    {
        List<SysOrder> list = sysOrderService.selectSysOrderList(sysOrder);
        ExcelUtil<SysOrder> util = new ExcelUtil<SysOrder>(SysOrder.class);
        return util.exportExcel(list, "资产管理处订单管理数据");
    }

    /**
     * 获取资产管理处订单管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:procurementorder:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysOrderService.selectSysOrderById(id));
    }

    /**
     * 新增资产管理处订单管理
     */
    @PreAuthorize("@ss.hasPermi('system:procurementorder:add')")
    @Log(title = "资产管理处订单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysOrder sysOrder)
    {
        return toAjax(sysOrderService.insertSysOrder(sysOrder));
    }

    /**
     * 修改资产管理处订单管理
     */
    @PreAuthorize("@ss.hasPermi('system:procurementorder:edit')")
    @Log(title = "资产管理处订单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysOrder sysOrder)
    {
        sysOrder.setProcurementAuditor(SecurityUtils.getUsername());
        // ==1为 同意 ==2为不同意 ==0 为未审核
        if(sysOrder.getProcurementStatu().equals("1")){
            //订单状态变为等待资产管理处采购
            //sysOrder.setProcurementStatu("0");
            sysOrder.setStatus("4");
        }else if(sysOrder.getProcurementStatu().equals("2")){
            //不同意 把订单置为未通过
            sysOrder.setStatus("0");
        }
        return toAjax(sysOrderService.updateSysOrder(sysOrder));
    }

    /**
     *
     *    修改为 已采购  发邮件
     */
    @PreAuthorize("@ss.hasPermi('system:procurementorder:remove')")
    @Log(title = "资产管理处订单管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long ids)
    {

        SysOrder sysOrder = sysOrderService.selectSysOrderById(ids);
        sysOrder.setStatus("5");

        String email = sysOrder.getEmail();

        OhMyEmail.config(SMTP_QQ(false), "1979271432@qq.com", "ojhsruegbptdjcci");
        try {
            OhMyEmail.subject("资产管理处采购通知")
                    .from("资产管理处")
                    .to(email)
                    .text(sysOrder.getUsername()+"老师您好, 您的订单已采购, 请及时前往资产管理处提取")
                    .send();
        } catch (SendMailException e) {
            e.printStackTrace();
        }
        return toAjax(sysOrderService.updateSysOrder(sysOrder));
    }
}
