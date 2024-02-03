package com.wyt.wytlab.biz.yj;

/**
 * @desc 得分项，一些指标指示位枚举类
 */
public enum IndicatorDataEnum {

    /**
     * 心率连续7天小于50
     */
    HR_50(0),
    /**
     * 血氧连续7天小于50
     */
    OXY_LESS_50(1),
    ;

    /**
     * 二进制指示物位(bit值)   如  7  二进制标识  0000 0000 0000 0111
     * 即0 ，1 ，2 指示位满足
     */
    private final Integer num;


    IndicatorDataEnum(Integer num) {
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }

}
