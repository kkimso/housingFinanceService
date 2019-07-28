package com.kakaopay.housingfinance.common.util;

import com.kakaopay.common.util.MappingHelper;
import com.kakaopay.housingfinance.entity.Bank;
import com.kakaopay.housingfinance.entity.HousingProvision;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MappingHelperTests {

    @Spy
    MappingHelper mappingHelper;

    private List<String[]> fileDate;

    @Before
    public void init(){
        fileDate = new ArrayList<>();
        fileDate.add(new String[]{"연도","월","국민은행","신한은행","하나은행","카카오뱅크", "", ""});
        fileDate.add(new String[]{"2005","1","1,233","930","440","123", "", ""});
        fileDate.add(new String[]{"2005","2","1,245","820","233","233", "", ""});
        fileDate.add(new String[]{"2005","3","2,333","800","244","233", "", ""});
    }

    @Test
    public void bankMapping_Normal_Success(){
        List<Bank> banks = mappingHelper.bankMapping(fileDate);

        assertThat(banks.isEmpty()).isEqualTo(false);

        assertThat(banks.get(0).getName()).isEqualTo("국민은행");

        assertThat(banks.size()).isEqualTo(4);
    }

    @Test
    public void  housingProvisionMapping_Normal_Success(){
        List<Bank> banks = new ArrayList<>();
        banks.add(Bank.builder().name("국민은행").code(1L).build());
        banks.add(Bank.builder().name("신한은행").code(2L).build());
        banks.add(Bank.builder().name("하나은행").code(3L).build());
        banks.add(Bank.builder().name("카카오뱅크").code(4L).build());

        List<HousingProvision> housingProvisions = mappingHelper.housingProvisionMapping(banks, fileDate);

        assertThat(housingProvisions.isEmpty()).isEqualTo(false);
        assertThat(housingProvisions.get(1).getAmount()).isSameAs(1233);
    }

}
