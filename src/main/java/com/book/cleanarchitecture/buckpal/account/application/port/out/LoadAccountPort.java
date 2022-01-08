package com.book.cleanarchitecture.buckpal.account.application.port.out;

import com.book.cleanarchitecture.buckpal.account.domain.Account;
import com.book.cleanarchitecture.buckpal.account.domain.vo.AccountId;

import java.time.LocalDateTime;

public interface LoadAccountPort {

    Account loadAccount(AccountId accountId, LocalDateTime baselineDate);
}
