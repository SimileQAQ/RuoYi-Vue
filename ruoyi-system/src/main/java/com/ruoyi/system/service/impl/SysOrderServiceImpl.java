package com.ruoyi.system.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysOrderMapper;
import com.ruoyi.system.domain.SysOrder;
import com.ruoyi.system.service.ISysOrderService;

/**
 * 总订单管理Service业务层处理
 *
 * @author ruoiyi
 * @date 2021-05-13
 */
@Service
public class SysOrderServiceImpl implements ISysOrderService {
    @Autowired
    private SysOrderMapper sysOrderMapper;

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询总订单管理
     *
     * @param orderId 总订单管理ID
     * @return 总订单管理
     */
    @Override
    public SysOrder selectSysOrderById(Long orderId) {
        return sysOrderMapper.selectSysOrderById(orderId);
    }

    /**
     * 查询总订单管理列表
     *
     * @param sysOrder 总订单管理
     * @return 总订单管理
     */
    @Override
    public List<SysOrder> selectSysOrderList(SysOrder sysOrder) {
        return sysOrderMapper.selectSysOrderList(sysOrder);
    }

    /**
     * 新增总订单管理
     *
     * @param sysOrder 总订单管理
     * @return 结果
     */
    @Override
    public int insertSysOrder(SysOrder sysOrder) {
        //生成订单号
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");
        String date = sf.format(DateUtils.getNowDate());

        sysOrder.setOrderId(Long.valueOf(date));


        //用户名 用户ID 根据登录信息记录
        sysOrder.setUsername(SecurityUtils.getUsername());
        sysOrder.setUserId(SecurityUtils.getUserId());



        //订单状态设为 待归口单位审核
        sysOrder.setStatus("1");

        //归口单位审核 字段设为0 未审核
        sysOrder.setRelevantStatu("0");

        sysOrder.setCreateTime(DateUtils.getNowDate());
        return sysOrderMapper.insertSysOrder(sysOrder);
    }

    /**
     * 修改总订单管理
     *
     * @param sysOrder 总订单管理
     * @return 结果
     */
    @Override
    public int updateSysOrder(SysOrder sysOrder) {
        sysOrder.setUpdateTime(DateUtils.getNowDate());
        return sysOrderMapper.updateSysOrder(sysOrder);
    }

    /**
     * 批量删除总订单管理
     *
     * @param orderIds 需要删除的总订单管理ID
     * @return 结果
     */
    @Override
    public int deleteSysOrderByIds(Long[] orderIds) {
        return sysOrderMapper.deleteSysOrderByIds(orderIds);
    }

    /**
     * 删除总订单管理信息
     *
     * @param orderId 总订单管理ID
     * @return 结果
     */
    @Override
    public int deleteSysOrderById(Long orderId) {
        return sysOrderMapper.deleteSysOrderById(orderId);
    }
}
