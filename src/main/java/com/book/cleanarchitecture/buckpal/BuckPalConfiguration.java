package com.book.cleanarchitecture.buckpal;

import com.book.cleanarchitecture.buckpal.account.application.service.MoneyTransferProperties;
import com.book.cleanarchitecture.buckpal.account.domain.Money;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(BuckPalConfigurationProperties.class)
public class BuckPalConfiguration {

    @Bean
    public MoneyTransferProperties moneyTransferProperties(BuckPalConfigurationProperties buckPalConfigurationProperties) {
        return new MoneyTransferProperties(Money.of(buckPalConfigurationProperties.getTransferThreshold()));
    }
}
