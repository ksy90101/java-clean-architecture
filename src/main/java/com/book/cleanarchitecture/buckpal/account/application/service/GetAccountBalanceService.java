package com.book.cleanarchitecture.buckpal.account.application.service;

import com.book.cleanarchitecture.buckpal.account.application.port.in.GetAccountBalanceQuery;
import com.book.cleanarchitecture.buckpal.account.application.port.out.LoadAccountPort;
import com.book.cleanarchitecture.buckpal.account.domain.vo.AccountId;
import com.book.cleanarchitecture.buckpal.account.domain.vo.Money;

import java.time.LocalDateTime;

public class GetAccountBalanceService implements GetAccountBalanceQuery {
    private final LoadAccountPort loadAccountPort;

    public GetAccountBalanceService(LoadAccountPort loadAccountPort) {
        this.loadAccountPort = loadAccountPort;
    }

    @Override
    public Money getAccountBalance(AccountId accountId) {
        return loadAccountPort.loadAccount(accountId, LocalDateTime.now())
                .calculateBalance();
    }
}
