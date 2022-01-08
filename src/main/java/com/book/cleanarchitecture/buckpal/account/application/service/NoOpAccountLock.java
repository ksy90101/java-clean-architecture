package com.book.cleanarchitecture.buckpal.account.application.service;

import com.book.cleanarchitecture.buckpal.account.application.port.out.AccountLock;
import com.book.cleanarchitecture.buckpal.account.domain.vo.AccountId;
import org.springframework.stereotype.Component;

@Component
class NoOpAccountLock implements AccountLock {

    @Override
    public void lockAccount(AccountId accountId) {
        // do nothing
    }

    @Override
    public void releaseAccount(AccountId accountId) {
        // do nothing
    }

}
