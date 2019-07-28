package com.kakaopay.housingfinance.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Setter
@Getter
public class ApplyBankInfo {
    private Integer year;

    @JsonProperty("bank")
    private String bank_name;

    @JsonIgnore
    private Long amount;

    public ApplyBankInfo(String bank_name, Integer year, Long amount) {
        this.bank_name = bank_name;
        this.year      = year;
        this.amount    = amount;
    }
}