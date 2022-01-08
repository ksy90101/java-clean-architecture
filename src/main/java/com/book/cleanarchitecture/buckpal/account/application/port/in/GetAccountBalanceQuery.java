package com.book.cleanarchitecture.buckpal.account.application.port.in;

import com.book.cleanarchitecture.buckpal.account.domain.Money;
import com.book.cleanarchitecture.buckpal.account.domain.vo.AccountId;

public interface GetAccountBalanceQuery {

    Money getAccountBalance(AccountId accountId);

}
