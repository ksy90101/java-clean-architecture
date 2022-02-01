package com.book.cleanarchitecture.buckpal.account.application.port.in;

import com.book.cleanarchitecture.buckpal.account.domain.vo.AccountId;
import com.book.cleanarchitecture.buckpal.account.domain.vo.Money;
import com.book.cleanarchitecture.buckpal.shared.SelfValidating;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class SendMoneyCommand extends SelfValidating<SendMoneyCommand> {

    @NotNull
    private final AccountId sourceAccountId;

    @NotNull
    private final AccountId targetAccountId;

    @NotNull
    private final Money money;

    public SendMoneyCommand(AccountId sourceAccountId, AccountId targetAccountId, Money money) {
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.money = money;
        this.validateSelf();
    }

    public AccountId getSourceAccountId() {
        return sourceAccountId;
    }

    public AccountId getTargetAccountId() {
        return targetAccountId;
    }

    public Money getMoney() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SendMoneyCommand that = (SendMoneyCommand) o;
        return Objects.equals(sourceAccountId, that.sourceAccountId) && Objects.equals(targetAccountId, that.targetAccountId) && Objects.equals(money, that.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceAccountId, targetAccountId, money);
    }
}
