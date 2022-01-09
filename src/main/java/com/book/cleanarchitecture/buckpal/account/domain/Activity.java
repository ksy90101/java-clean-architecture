package com.book.cleanarchitecture.buckpal.account.domain;

import com.book.cleanarchitecture.buckpal.account.domain.vo.AccountId;
import com.book.cleanarchitecture.buckpal.account.domain.vo.ActivityId;
import com.book.cleanarchitecture.buckpal.account.domain.vo.Money;

import java.time.LocalDateTime;

public class Activity {

    private final AccountId ownerAccountId;

    private final AccountId sourceAccountId;

    private final AccountId targetAccountId;

    private final LocalDateTime timestamp;

    private final Money money;

    private final ActivityId id;

    public Activity(ActivityId id, AccountId ownerAccountId, AccountId sourceAccountId, AccountId targetAccountId, LocalDateTime timestamp, Money money) {
        this.id = id;
        this.ownerAccountId = ownerAccountId;
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.timestamp = timestamp;
        this.money = money;
    }

    public Activity(AccountId ownerAccountId, AccountId sourceAccountId, AccountId targetAccountId, LocalDateTime timestamp, Money money) {
        this.id = null;
        this.ownerAccountId = ownerAccountId;
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.timestamp = timestamp;
        this.money = money;
    }

    public ActivityId getId() {
        return id;
    }

    public AccountId getOwnerAccountId() {
        return ownerAccountId;
    }

    public AccountId getSourceAccountId() {
        return sourceAccountId;
    }

    public AccountId getTargetAccountId() {
        return targetAccountId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Money getMoney() {
        return money;
    }
}
