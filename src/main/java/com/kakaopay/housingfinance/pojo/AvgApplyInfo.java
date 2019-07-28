package com.kakaopay.housingfinance.pojo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AvgApplyInfo {
    private Integer year;
    private Long    amount;

    public AvgApplyInfo(Integer year, Long amount){
        this.year   = year;
        this.amount = Math.round((double)amount/12);
    }
}
