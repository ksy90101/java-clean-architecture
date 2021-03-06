package com.book.cleanarchitecture.buckpal.account.adapter.out.persistence;

import com.book.cleanarchitecture.buckpal.account.domain.Account;
import com.book.cleanarchitecture.buckpal.account.domain.ActivityWindow;
import com.book.cleanarchitecture.buckpal.account.domain.vo.AccountId;
import com.book.cleanarchitecture.buckpal.account.domain.vo.Money;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

import static com.book.cleanarchitecture.buckpal.common.AccountTestData.defaultAccount;
import static com.book.cleanarchitecture.buckpal.common.ActivityTestData.defaultActivity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@Import({AccountPersistenceAdapter.class, AccountMapper.class})
class AccountPersistenceAdapterTest {
    @Autowired
    private AccountPersistenceAdapter adapterUnderTest;

    @Autowired
    private ActivityRepository activityRepository;

    @Test
    @Sql("AccountPersistenceAdapterTest.sql")
    void loadsAccount() {
        Account account = adapterUnderTest.loadAccount(new AccountId(1L), LocalDateTime.of(2018, 8, 10, 0, 0));

        assertAll(
                () -> assertThat(account.getActivities()).hasSize(2),
                () -> assertThat(account.calculateBalance()).isEqualTo(Money.of(500))
        );
    }

    @Test
    void updatesActivities() {
        Account account = defaultAccount()
                .withBaselineBalance(Money.of(555L))
                .withActivityWindow(new ActivityWindow(
                        defaultActivity()
                                .withId(null)
                                .withMoney(Money.of(1L)).build()))
                .build();

        adapterUnderTest.updateActivities(account);

        ActivityJpaEntity savedActivity = activityRepository.findAll().get(0);

        assertAll(
                () -> assertThat(activityRepository.count()).isEqualTo(1),
                () -> assertThat(savedActivity.getAmount()).isEqualTo(1L)
        );
    }
}
