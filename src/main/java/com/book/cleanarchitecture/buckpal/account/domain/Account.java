package com.book.cleanarchitecture.buckpal.account.domain;

import com.book.cleanarchitecture.buckpal.account.domain.vo.AccountId;
import com.book.cleanarchitecture.buckpal.account.domain.vo.Money;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Account {

    private final AccountId id;

    private final Money baselineBalance;

    private final ActivityWindow activityWindow;

    private Account(AccountId id, Money baselineBalance, ActivityWindow activityWindow) {
        this.id = id;
        this.baselineBalance = baselineBalance;
        this.activityWindow = activityWindow;
    }

    public static Account withId(
            AccountId accountId,
            Money baselineBalance,
            ActivityWindow activityWindow) {
        return new Account(accountId, baselineBalance, activityWindow);
    }

    public Money calculateBalance() {
        return this.baselineBalance.plus(this.activityWindow.calculateBalance(this.id));
    }

    public boolean withdraw(Money money, AccountId targetAccountId) {
        if (!mayWithdraw(money)) {
            return false;
        }

        Activity withdrawal = new Activity(this.id, this.id, targetAccountId, LocalDateTime.now(), money);
        this.activityWindow.addActivity(withdrawal);

        return true;
    }

    public boolean deposit(Money money, AccountId sourceAccountId) {
        Activity deposit = new Activity(this.id, sourceAccountId, this.id, LocalDateTime.now(), money);
        this.activityWindow.addActivity(deposit);

        return true;
    }

    public Optional<AccountId> getId() {
        return Optional.ofNullable(this.id);
    }

    public List<Activity> getActivities() {
        return activityWindow.getActivities();
    }

    private boolean mayWithdraw(Money money) {
        return this.calculateBalance().plus(money.negate()).isPositiveOrZero();
    }
}
