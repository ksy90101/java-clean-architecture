package com.book.cleanarchitecture.buckpal.account.domain;

import com.book.cleanarchitecture.buckpal.account.domain.vo.AccountId;
import com.book.cleanarchitecture.buckpal.account.domain.vo.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.book.cleanarchitecture.buckpal.common.AccountTestData.defaultAccount;
import static com.book.cleanarchitecture.buckpal.common.ActivityTestData.defaultActivity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class AccountTest {

    private final AccountId accountId = new AccountId(1L);
    private final Account account = defaultAccount()
            .withAccountId(accountId)
            .withBaselineBalance(Money.of(555L))
            .withActivityWindow(new ActivityWindow(
                    defaultActivity()
                            .withTargetAccount(accountId)
                            .withMoney(Money.of(999L)).build(),
                    defaultActivity()
                            .withTargetAccount(accountId)
                            .withMoney(Money.of(1L)).build()))
            .build();

    @Test
    @DisplayName("계좌의 금액 계산하는 메서드")
    void calculateBalance() {
        Money balance = account.calculateBalance();

        assertThat(balance).isEqualTo(Money.of(1555L));
    }

    @Test
    @DisplayName("계좌 송금 성공 테스트")
    void withdrawalSucceeds() {
        boolean success = account.withdraw(Money.of(555L), new AccountId(99L));

        assertAll(
                () -> assertThat(success).isTrue(),
                () -> assertThat(account.getActivities()).hasSize(3),
                () -> assertThat(account.calculateBalance()).isEqualTo(Money.of(1000L))
        );
    }

    @Test
    @DisplayName("계좌 송금 실패 테스트 (초과된 금액)")
    void withdrawalFailure() {
        boolean failure = account.withdraw(Money.of(1556L), new AccountId(99L));

        assertAll(
                () -> assertThat(failure).isFalse(),
                () -> assertThat(account.getActivities()).hasSize(2),
                () -> assertThat(account.calculateBalance()).isEqualTo(Money.of(1555L))
        );
    }

    @Test
    @DisplayName("계좌 예금 성공 테스트")
    void depositSuccess() {
        boolean success = account.deposit(Money.of(445L), new AccountId(99L));

        assertAll(
                () -> assertThat(success).isTrue(),
                () -> assertThat(account.getActivities()).hasSize(3),
                () -> assertThat(account.calculateBalance()).isEqualTo(Money.of(2000L))
        );
    }
}
