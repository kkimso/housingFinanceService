package com.kakaopay.housingfinance.repository;

import com.kakaopay.housingfinance.entity.HousingProvision;
import com.kakaopay.housingfinance.pojo.AvgApplyInfo;
import com.kakaopay.housingfinance.pojo.ApplyBankInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HousingProvisionRepository extends JpaRepository<HousingProvision, Long> {

    @Query("select new com.kakaopay.housingfinance.pojo.ApplyBankInfo(h.bank.name, h.year, sum(h.amount)) from HousingProvision h " +
            "group by h.bank.code, h.year order by h.year, h.bank.code")
    List<ApplyBankInfo> findHousingProvisionsSum();

    @Query("select new com.kakaopay.housingfinance.pojo.AvgApplyInfo(h.year, sum(h.amount)) from HousingProvision h " +
            "where h.bank.name = :name group by h.bank.code, h.year order by h.year, h.bank.code")
    List<AvgApplyInfo> findHousingProvisionAvg(@Param("name") String bankName);
}
