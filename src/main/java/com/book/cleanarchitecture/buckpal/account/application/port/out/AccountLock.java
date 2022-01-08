package com.book.cleanarchitecture.buckpal.account.application.port.out;

import com.book.cleanarchitecture.buckpal.account.domain.vo.AccountId;

public interface AccountLock {

    void lockAccount(AccountId accountId);

    void releaseAccount(AccountId accountId);
}
