package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysOrder;

/**
 * 总订单管理Mapper接口
 * 
 * @author ruoiyi
 * @date 2021-05-13
 */
public interface SysOrderMapper 
{
    /**
     * 查询总订单管理
     * 
     * @param orderId 总订单管理ID
     * @return 总订单管理
     */
    public SysOrder selectSysOrderById(Long orderId);

    /**
     * 查询总订单管理列表
     * 
     * @param sysOrder 总订单管理
     * @return 总订单管理集合
     */
    public List<SysOrder> selectSysOrderList(SysOrder sysOrder);

    /**
     * 新增总订单管理
     * 
     * @param sysOrder 总订单管理
     * @return 结果
     */
    public int insertSysOrder(SysOrder sysOrder);

    /**
     * 修改总订单管理
     * 
     * @param sysOrder 总订单管理
     * @return 结果
     */
    public int updateSysOrder(SysOrder sysOrder);

    /**
     * 删除总订单管理
     * 
     * @param orderId 总订单管理ID
     * @return 结果
     */
    public int deleteSysOrderById(Long orderId);

    /**
     * 批量删除总订单管理
     * 
     * @param orderIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysOrderByIds(Long[] orderIds);
}
