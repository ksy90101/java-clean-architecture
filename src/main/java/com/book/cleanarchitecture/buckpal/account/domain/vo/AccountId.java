package com.book.cleanarchitecture.buckpal.account.domain.vo;

public class AccountId {

    private final Long value;

    public AccountId(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}
