package com.wyt.wytlab.biz.yj;

import lombok.Data;

@Data
public class AlertScoreDTO {


    private Long id;
    /**
     * 心率异常得分
     */
    private Integer hrScore;

    /**
     * 血氧异常得分
     */
    private Integer bloodOxygenScore;

    /**
     * 二进制存储各指标标识字段 详情见IndicatorDataEnum类
     * <p>
     */
    private Integer indicatorData;

    public AlertScoreDTO(Long id, Integer hrScore, Integer bloodOxygenScore, Integer indicatorData) {
        this.id = id;
        this.hrScore = hrScore;
        this.bloodOxygenScore = bloodOxygenScore;
        this.indicatorData = indicatorData;
    }
}
