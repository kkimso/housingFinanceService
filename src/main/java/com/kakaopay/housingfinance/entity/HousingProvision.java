package com.kakaopay.housingfinance.entity;

import lombok.Builder;
import lombok.Getter;
import javax.persistence.*;

@Getter
@Entity
@Table(name = "housing_provision")
public class HousingProvision {
    @Id
    @GeneratedValue
    private Long   id;

    @ManyToOne
    @JoinColumn(name="bank_code")
    private Bank bank;

    private Integer year;   //년도
    private Integer month;  //월
    private Integer amount; //지원 금액

    @Builder
    public HousingProvision(Bank bank, Integer year, Integer month, Integer amount){
        this.bank   = bank;
        this.year   = year;
        this.month  = month;
        this.amount = amount;
    }

    public HousingProvision(Bank bank, Integer year, Integer amount){
        this.bank   = bank;
        this.year   = year;
        this.amount = amount;
    }
}
