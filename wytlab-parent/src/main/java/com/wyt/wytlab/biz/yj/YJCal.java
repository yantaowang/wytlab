package com.wyt.wytlab.biz.yj;

import cn.hutool.core.util.ObjectUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.List;

public class YJCal {

    public static Integer getIndicator() {
        //从数据库读取数据
        List<AlertScoreDTO> alertScoreDTOList = generateRandomData(10);

        int indicatorData = 0;
        //1 心率异常 判定      2.2
        indicatorData |= checkHrAbnormal(alertScoreDTOList)
                ? 1 << IndicatorDataEnum.HR_50.getNum() : 0;
        boolean bloodOxygenAbnormal = checkOxyAbnormal(alertScoreDTOList);
        indicatorData |= bloodOxygenAbnormal ? 1 << IndicatorDataEnum.OXY_LESS_50.getNum() : 0;
        return indicatorData;
    }

    private static List<AlertScoreDTO> generateRandomData(int count) {
        List<AlertScoreDTO> alertScoreDTOList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            Long id = (long) i + 1;
            Integer hrScore = random.nextInt(101);  // 随机生成0到100之间的整数
            Integer bloodOxygenScore = random.nextInt(101);  // 随机生成0到100之间的整数
            Integer indicatorData = 3;

            AlertScoreDTO alertScoreDTO = new AlertScoreDTO(id, hrScore, bloodOxygenScore, indicatorData);
            alertScoreDTOList.add(alertScoreDTO);
        }

        return alertScoreDTOList;
    }

    public static boolean checkHrAbnormal(List<AlertScoreDTO> alertScoreDTOList) {
        int days = getConsecutiveCount(IndicatorDataEnum.HR_50, alertScoreDTOList);
        if(days > 3) {
            return true;
        }
        return false;
    }

    public static boolean checkOxyAbnormal(List<AlertScoreDTO> alertScoreDTOList) {
        //此处省略业务逻辑
       return true;
    }

    private static int getConsecutiveCount(IndicatorDataEnum dataEnum, List<AlertScoreDTO> alertScoreDTOList) {
        int days = 0;
        for (int i = 0; i < alertScoreDTOList.size(); i++) {
            AlertScoreDTO alertScoreDTO = alertScoreDTOList.get(i);
            if (((1 << dataEnum.getNum()) & alertScoreDTO.getIndicatorData()) == 0) {
                //不连续了直接结束
                break;
            }
            days++;
        }
        return days;
    }

    public static void main(String[] args) {
        System.out.println(getIndicator());
    }
}
