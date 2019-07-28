package com.kakaopay.housingfinance.service;

import com.kakaopay.housingfinance.pojo.AvgApplyInfo;
import com.kakaopay.housingfinance.pojo.BankApplyAmountInfo;
import com.kakaopay.housingfinance.pojo.ApplyBankInfo;
import com.kakaopay.housingfinance.repository.HousingProvisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HousingProvisionService {

    private final HousingProvisionRepository housingProvisionRepository;

    @Autowired
    public HousingProvisionService(HousingProvisionRepository housingProvisionRepository) {
        this.housingProvisionRepository = housingProvisionRepository;
    }

    //----------------------------------------------
    // 년도별 각 금융기관 지원 금액 합계 조회 서비스
    //----------------------------------------------
    public List<BankApplyAmountInfo> getBankApplyAmountSum(){
        List<BankApplyAmountInfo> bankApplyAmountInfos = new ArrayList<>();

        List<ApplyBankInfo> result = housingProvisionRepository.findHousingProvisionsSum();

        BankApplyAmountInfo bankApplyAmountInfo = null;

        for (ApplyBankInfo detailInfo : result) {
            if(bankApplyAmountInfo == null) {
                bankApplyAmountInfo = BankApplyAmountInfo.builder().year(detailInfo.getYear()).build();
            } else {
                if(isChangedYear(bankApplyAmountInfo, detailInfo.getYear() + " 년")){
                    bankApplyAmountInfos.add(bankApplyAmountInfo);
                    bankApplyAmountInfo = BankApplyAmountInfo.builder().year(detailInfo.getYear()).build();
                }
            }
            bankApplyAmountInfo.setTotalAmount(bankApplyAmountInfo.getTotalAmount() + detailInfo.getAmount());
            bankApplyAmountInfo.setDetailAmount(detailInfo.getBank_name(), detailInfo.getAmount());
        }
        return bankApplyAmountInfos;
    }

    //---------------------------------------------------
    // 전체 지원 금액 중 가장 큰 기관명 조회
    //---------------------------------------------------
    public ApplyBankInfo getMaxBankApplyInfo(){
         List<ApplyBankInfo> result = housingProvisionRepository.findHousingProvisionsSum();
         return Collections.max(result, Comparator.comparing(ApplyBankInfo::getAmount));
    }

    //-------------------------------------------------------
    // 전체 연도에서 특정 은행의 지원 금액 평균 중 Max값과 Min 값 조회
    //-------------------------------------------------------
    public List<AvgApplyInfo> getMaxMinHousingProvisionAvg(String bankName) {
        List<AvgApplyInfo> avgApplyInfos = housingProvisionRepository.findHousingProvisionAvg(bankName);
        List<AvgApplyInfo> result = new ArrayList<>();

        result.add(Collections.min(avgApplyInfos, Comparator.comparing(AvgApplyInfo::getAmount)));
        result.add(Collections.max(avgApplyInfos, Comparator.comparing(AvgApplyInfo::getAmount)));

        return result;
    }

    //-------------------------------------------------------
    //  연도 변경 여부 조회
    //-------------------------------------------------------
    private Boolean isChangedYear(BankApplyAmountInfo bankApplyAmountInfo, String year){
        return !bankApplyAmountInfo.getYear().equals(year);
    }
}