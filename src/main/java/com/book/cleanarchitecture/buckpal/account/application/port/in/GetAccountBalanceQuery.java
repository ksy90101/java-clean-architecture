package com.book.cleanarchitecture.buckpal.account.application.port.in;

import com.book.cleanarchitecture.buckpal.account.domain.vo.AccountId;
import com.book.cleanarchitecture.buckpal.account.domain.vo.Money;

public interface GetAccountBalanceQuery {

    Money getAccountBalance(AccountId accountId);
}
