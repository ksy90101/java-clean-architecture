package com.book.cleanarchitecture.buckpal.account.application.service;

import com.book.cleanarchitecture.buckpal.account.domain.vo.Money;

public class MoneyTransferProperties {

    private Money maximumTransferThreshold = Money.of(1_000_000L);

    public MoneyTransferProperties(Money maximumTransferThreshold) {
        this.maximumTransferThreshold = maximumTransferThreshold;
    }

    public Money getMaximumTransferThreshold() {
        return maximumTransferThreshold;
    }
}
