package com.wyt.service;

import com.wyt.dao.AccountInfoDao;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.annotation.HmilyTCC;
import org.dromara.hmily.core.context.HmilyContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Slf4j
public class AccountInfoServiceImpl implements AccountInfoService {

    @Autowired
    AccountInfoDao accountInfoDao;

    @Override
    @HmilyTCC(confirmMethod = "confirmMethod", cancelMethod = "cancelMethod")
    public void updateAccountBalance(String accountNo, Double amount) {
        //获取全局事务id
        String transId = HmilyContextHolder.get().getTransId().toString();
        log.info("bank2 try begin 开始执行...xid:{}", transId);
    }

    /**
     * confirm方法
     * confirm幂等校验
     * 正式增加金额
     *
     * @param accountNo
     * @param amount
     */
    @Transactional
    public void confirmMethod(String accountNo, Double amount) {
        //获取全局事务id
        String transId = HmilyContextHolder.get().getTransId().toString();
        log.info("bank2 confirm begin 开始执行...xid:{}", transId);
        if (accountInfoDao.isExistConfirm(transId) > 0) {
            log.info("bank2 confirm 已经执行，无需重复执行...xid:{}", transId);
            return;
        }
        //增加金额
        accountInfoDao.addAccountBalance(accountNo, amount);
        //增加一条confirm日志，用于幂等
        accountInfoDao.addConfirm(transId);
        log.info("bank2 confirm end 结束执行...xid:{}", transId);
    }


    /**
     * @param accountNo
     * @param amount
     */
    public void cancelMethod(String accountNo, Double amount) {
        //获取全局事务id
        String transId = HmilyContextHolder.get().getTransId().toString();
        log.info("bank2 cancel begin 开始执行...xid:{}", transId);

    }
}