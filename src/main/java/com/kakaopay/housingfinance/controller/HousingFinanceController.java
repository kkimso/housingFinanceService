package com.kakaopay.housingfinance.controller;

import com.kakaopay.housingfinance.entity.Bank;
import com.kakaopay.housingfinance.pojo.AvgApplyInfo;
import com.kakaopay.housingfinance.pojo.BankApplyAmountInfo;
import com.kakaopay.housingfinance.pojo.ApplyBankInfo;
import com.kakaopay.housingfinance.service.BankService;
import com.kakaopay.housingfinance.service.HousingProvisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//*-------------------------------------------------
// 주택금융 공급 현황 관련 API
//-------------------------------------------------*/
@RestController
@RequestMapping(value="housingFinance")
public class HousingFinanceController {

    private final BankService             bankService;
    private final HousingProvisionService housingProvisionService;

    @Autowired
    public HousingFinanceController(BankService bankService, HousingProvisionService housingProvisionService) {
        this.bankService             = bankService;
        this.housingProvisionService = housingProvisionService;
    }

    //-----------------------------------------------------------
    // 데이터 파일에서 각 레코드를 데이터베이스에 저장
    //-----------------------------------------------------------
    @PostMapping("/rowData")
    public ResponseEntity createRowData()  {
        try {
            bankService.saveRowData();
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    //------------------------------------------------------------
    // 주택 금융 공급 기관(은행) 목록 조회
    //------------------------------------------------------------
    @GetMapping("/banks")
    public ResponseEntity getBanks() {
        List<Bank> result = bankService.getBanks();

        if (result.size() > 0) {
            Map<String,Object> mapResult = new HashMap<>();
            mapResult.put("data", result);
            return new ResponseEntity<>(mapResult, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    //------------------------------------------------------------
    // 년도별 각 금융기관 지원 금액 합계 조회
    //------------------------------------------------------------
    @GetMapping("/bankApply/sum")
    public ResponseEntity getBankApplyAmountSum(){
        List<BankApplyAmountInfo> result = housingProvisionService.getBankApplyAmountSum();

        if (result.size() > 0) {
            Map<String,Object> mapResult = new HashMap<>();
            mapResult.put("name","주택금융 공급현황");
            mapResult.put("data", result);

            return new ResponseEntity<>(mapResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    //------------------------------------------------------------
    // 각 년도별 각 기관의 전체 지원금액 중에서 가장 큰 금액의 기관명 조회
    //------------------------------------------------------------
    @GetMapping("/bankApply/max")
    public ResponseEntity getMaxBankApplyInfoByYear(){
        ApplyBankInfo result = housingProvisionService.getMaxBankApplyInfo();
        if(result != null) {
           return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    //------------------------------------------------------------
    // 전체 년도(2005~2016)에서 외환은행의 지원금액 중
    // 평균에서 가장 작은 금액과 큰 금액 조회
    //------------------------------------------------------------
    @GetMapping("/bankApply/keb")
    public ResponseEntity getKEBBankApplyInfo(){
        final String KEB = "외환은행";

        List<AvgApplyInfo> result = housingProvisionService.getMaxMinHousingProvisionAvg(KEB);
        Map<String, Object> mapResult = new HashMap<>();
        mapResult.put("bank", KEB);
        mapResult.put("support_amount", result);
        return new ResponseEntity<>(mapResult, HttpStatus.OK);
    }
}