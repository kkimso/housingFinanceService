package com.kakaopay.housingfinance.service;

import com.kakaopay.common.util.CSVParser;
import com.kakaopay.common.util.MappingHelper;
import com.kakaopay.housingfinance.entity.Bank;
import com.kakaopay.housingfinance.entity.HousingProvision;
import com.kakaopay.housingfinance.repository.BankRepository;
import com.kakaopay.housingfinance.repository.HousingProvisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BankService {
    private final BankRepository             bankRepository;
    private final HousingProvisionRepository housingProvisionRepository;
    private final CSVParser                  csvParser;
    private final MappingHelper              mappingHelper;

    @Value("${resource.file.name}")
    private String fileName;

    @Autowired
    public BankService(BankRepository bankRepository, HousingProvisionRepository housingProvisionRepository, CSVParser csvParser, MappingHelper mappingHelper) {
        this.bankRepository             = bankRepository;
        this.housingProvisionRepository = housingProvisionRepository;
        this.csvParser                  = csvParser;
        this.mappingHelper              = mappingHelper;
    }

    //---------------------------------------
    // Row Data DB에 저장
    //---------------------------------------
    @Transactional(rollbackOn = Exception.class)
    public void saveRowData() throws Exception {
        // CSV 파일 파싱
        Optional<List<String[]>> maybeContents = csvParser.parseFile(fileName);
        if(!maybeContents.isPresent()) {
           throw new RuntimeException();
        }

        // Bank List 객체로 매핑
        List<Bank> bankList = mappingHelper.bankMapping(maybeContents.get());
        //은행 정보 DB에 저장
        bankRepository.saveAll(bankList);

        //주택금융 공급 현황 객체로 맵핑
        List<HousingProvision> housingProvisionList = mappingHelper.housingProvisionMapping(bankList, maybeContents.get());
        //매핑 객체 DB에 저장
        housingProvisionRepository.saveAll(housingProvisionList);
    }

    //----------------------------------------
    // 은행 목록 조회 서비스
    //----------------------------------------
    public List<Bank> getBanks() {
        return bankRepository.findAll();
    }
}
