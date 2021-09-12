package com.wyt.wytlab.redis;

public interface IStockCallback {
    /**
     * 获取库存
     * @return
     */
    int getStock(String key);
}
