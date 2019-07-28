package com.kakaopay.common.util;

import com.kakaopay.housingfinance.entity.Bank;
import com.kakaopay.housingfinance.entity.HousingProvision;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MappingHelper {

    private final String[] ignoreColumn = {"연도","월"};
    private final String   removeWords  = "(억원)";
    //--------------------------------------------------
    // 은행 리스트 매핑
    //--------------------------------------------------
    public List<Bank> bankMapping(List<String[]> param) {
        List<Bank> bankList   = new ArrayList<>();
        String[]  firstRecord = param.get(0);

        for (int index = ignoreColumn.length ; index < firstRecord.length ; index++) {
            if(firstRecord[index].length() > 0){
                String bankName = firstRecord[index].replace(removeWords,"");
                bankList.add(Bank.builder().name(bankName).build());
            }
        }
        return bankList;
    }

    //--------------------------------------------------
    // 주택 공급 현황 리스트 매핑
    //--------------------------------------------------
    public List<HousingProvision> housingProvisionMapping(List<Bank> banks, List<String[]> param) {
        List<HousingProvision> housingProvisionList = new ArrayList<>();

        param.remove(0);

        for(String[] row : param) {

            Integer year  = Integer.valueOf(row[0]);
            Integer month = Integer.valueOf(row[1]);

            for(int index = ignoreColumn.length; index < row.length; index++) {
                if (row[index].length() > 0){
                    housingProvisionList.add(
                            HousingProvision.builder()
                                    .year(year)
                                    .month(month)
                                    .bank(banks.get(index - ignoreColumn.length))
                                    .amount(Integer.valueOf(row[index].replace(",",""))).build()
                    );
                }
            }
        }
        return housingProvisionList;
    }
}