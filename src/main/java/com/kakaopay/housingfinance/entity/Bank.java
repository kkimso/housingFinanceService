package com.kakaopay.housingfinance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="bank")
public class Bank{

    @JsonIgnore
    @Id
    @GeneratedValue
    @Column(name="bank_code")
    private Long code;

    @Column(name="bank_name")
    private String name;
}
