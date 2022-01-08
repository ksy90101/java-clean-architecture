package com.book.cleanarchitecture.buckpal.account.application.port.out;

import com.book.cleanarchitecture.buckpal.account.domain.Account;

public interface UpdateAccountStatePort {

    void updateActivities(Account account);
}
