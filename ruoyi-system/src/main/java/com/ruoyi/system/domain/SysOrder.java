package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 总订单管理对象 sys_order
 * 
 * @author ruoyi
 * @date 2021-05-17
 */
public class SysOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @Excel(name = "编号")
    private Long id;

    /** 订单号
 */
    @Excel(name = "订单号")
    private Long orderId;

    /** 项目号
 */
    @Excel(name = "项目号")
    private String projectId;

    /** 是否进口
 */
    @Excel(name = "是否进口")
    private String projectImport;

    /** 品目编码
 */
    @Excel(name = "品目编码")
    private String itemCode;

    /** 货物名称 */
    @Excel(name = "货物名称")
    private String productName;

    /** 技术参数 */
    @Excel(name = "技术参数")
    private String technicalParams;

    /** 商品单价
 */
    @Excel(name = "商品单价")
    private String productPrice;

    /** 商品数量
 */
    @Excel(name = "商品数量")
    private String projectNum;

    /** 商品总价
 */
    @Excel(name = "商品总价")
    private String totalPrices;

    /** 归口单位
 */
    @Excel(name = "归口单位")
    private String relevantDepartment;

    /** 订单状态
 */
    @Excel(name = "订单状态")
    private String status;

    /** 申请人姓名
 */
    @Excel(name = "申请人姓名")
    private String username;

    /** 申请人ID
 */
    @Excel(name = "申请人ID")
    private String userId;

    /** 邮箱
 */
    @Excel(name = "邮箱")
    private String email;

    /** 归口单位审核人 */
    @Excel(name = "归口单位审核人")
    private String relevantAuditor;

    /** 归口单位审核状态 */
    @Excel(name = "归口单位审核状态")
    private String relevantStatu;

    /** 归口单位审核留言 */
    @Excel(name = "归口单位审核留言")
    private String relevantMessage;

    /** 财务处审核人 */
    @Excel(name = "财务处审核人")
    private String financeAuditor;

    /** 财务处审核状态 */
    @Excel(name = "财务处审核状态")
    private String financeStatu;

    /** 财务处审核留言 */
    @Excel(name = "财务处审核留言")
    private String financeMessage;

    /** 资产管理处审核人 */
    @Excel(name = "资产管理处审核人")
    private String procurementAuditor;

    /** 资产管理处审核状态 */
    @Excel(name = "资产管理处审核状态")
    private String procurementStatu;

    /** 资产管理处审核留言 */
    @Excel(name = "资产管理处审核留言")
    private String procurementMessage;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setProjectId(String projectId) 
    {
        this.projectId = projectId;
    }

    public String getProjectId() 
    {
        return projectId;
    }
    public void setProjectImport(String projectImport) 
    {
        this.projectImport = projectImport;
    }

    public String getProjectImport() 
    {
        return projectImport;
    }
    public void setItemCode(String itemCode) 
    {
        this.itemCode = itemCode;
    }

    public String getItemCode() 
    {
        return itemCode;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setProductPrice(String productPrice) 
    {
        this.productPrice = productPrice;
    }

    public void setTechnicalParams(String technicalParams)
    {
        this.technicalParams = technicalParams;
    }
    public String getTechnicalParams()
    {
        return technicalParams;
    }

    public String getProductPrice() 
    {
        return productPrice;
    }
    public void setProjectNum(String projectNum) 
    {
        this.projectNum = projectNum;
    }

    public String getProjectNum() 
    {
        return projectNum;
    }
    public void setTotalPrices(String totalPrices) 
    {
        this.totalPrices = totalPrices;
    }

    public String getTotalPrices() 
    {
        return totalPrices;
    }
    public void setRelevantDepartment(String relevantDepartment) 
    {
        this.relevantDepartment = relevantDepartment;
    }

    public String getRelevantDepartment() 
    {
        return relevantDepartment;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setRelevantAuditor(String relevantAuditor) 
    {
        this.relevantAuditor = relevantAuditor;
    }

    public String getRelevantAuditor() 
    {
        return relevantAuditor;
    }
    public void setRelevantStatu(String relevantStatu) 
    {
        this.relevantStatu = relevantStatu;
    }

    public String getRelevantStatu() 
    {
        return relevantStatu;
    }
    public void setRelevantMessage(String relevantMessage) 
    {
        this.relevantMessage = relevantMessage;
    }

    public String getRelevantMessage() 
    {
        return relevantMessage;
    }
    public void setFinanceAuditor(String financeAuditor) 
    {
        this.financeAuditor = financeAuditor;
    }

    public String getFinanceAuditor() 
    {
        return financeAuditor;
    }
    public void setFinanceStatu(String financeStatu) 
    {
        this.financeStatu = financeStatu;
    }

    public String getFinanceStatu() 
    {
        return financeStatu;
    }
    public void setFinanceMessage(String financeMessage) 
    {
        this.financeMessage = financeMessage;
    }

    public String getFinanceMessage() 
    {
        return financeMessage;
    }
    public void setProcurementAuditor(String procurementAuditor) 
    {
        this.procurementAuditor = procurementAuditor;
    }

    public String getProcurementAuditor() 
    {
        return procurementAuditor;
    }
    public void setProcurementStatu(String procurementStatu) 
    {
        this.procurementStatu = procurementStatu;
    }

    public String getProcurementStatu() 
    {
        return procurementStatu;
    }
    public void setProcurementMessage(String procurementMessage) 
    {
        this.procurementMessage = procurementMessage;
    }

    public String getProcurementMessage() 
    {
        return procurementMessage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("orderId", getOrderId())
                .append("projectId", getProjectId())
                .append("projectImport", getProjectImport())
                .append("itemCode", getItemCode())
                .append("productName", getProductName())
                .append("technicalParams", getTechnicalParams())
                .append("productPrice", getProductPrice())
                .append("projectNum", getProjectNum())
                .append("totalPrices", getTotalPrices())
                .append("relevantDepartment", getRelevantDepartment())
                .append("status", getStatus())
                .append("username", getUsername())
                .append("userId", getUserId())
                .append("email", getEmail())
                .append("relevantAuditor", getRelevantAuditor())
                .append("relevantStatu", getRelevantStatu())
                .append("relevantMessage", getRelevantMessage())
                .append("financeAuditor", getFinanceAuditor())
                .append("financeStatu", getFinanceStatu())
                .append("financeMessage", getFinanceMessage())
                .append("procurementAuditor", getProcurementAuditor())
                .append("procurementStatu", getProcurementStatu())
                .append("procurementMessage", getProcurementMessage())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }


}
