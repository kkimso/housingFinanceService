package com.kakaopay.housingfinance.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class BankApplyAmountInfo {
    private String  year;

    private Long totalAmount;

    private Map<String, Long> detailAmount;

    @Builder
    public BankApplyAmountInfo (Integer year) {
        this.year         = year + " 년";
        this.totalAmount  = 0L;
        this.detailAmount = new HashMap<>();
    }

    public void setDetailAmount(String bankName, Long amount) {
        this.detailAmount.put(bankName, amount);
    }
}
