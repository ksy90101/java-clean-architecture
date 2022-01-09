package com.book.cleanarchitecture.buckpal.account.adapter.out.persistence;

import com.book.cleanarchitecture.buckpal.account.domain.Account;
import com.book.cleanarchitecture.buckpal.account.domain.Activity;
import com.book.cleanarchitecture.buckpal.account.domain.ActivityWindow;
import com.book.cleanarchitecture.buckpal.account.domain.vo.AccountId;
import com.book.cleanarchitecture.buckpal.account.domain.vo.ActivityId;
import com.book.cleanarchitecture.buckpal.account.domain.vo.Money;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
class AccountMapper {
    Account mapToDomainEntity(AccountJpaEntity account, List<ActivityJpaEntity> activities,
                              Long withdrawalBalance, Long depositBalance) {
        Money baselineBalance = Money.of(depositBalance).minus(Money.of(withdrawalBalance));

        return Account.withId(new AccountId(account.getId()), baselineBalance, mapToActivityWindow(activities));
    }

    ActivityWindow mapToActivityWindow(List<ActivityJpaEntity> activities) {
        List<Activity> mappedActivities = activities.stream()
                .map(activity -> new Activity(
                        new ActivityId(activity.getId()),
                        new AccountId(activity.getOwnerAccountId()),
                        new AccountId(activity.getSourceAccountId()),
                        new AccountId(activity.getTargetAccountId()),
                        activity.getTimestamp(),
                        Money.of(activity.getAmount()))
                ).collect(Collectors.toList());

        return new ActivityWindow(mappedActivities);
    }

    ActivityJpaEntity mapToJpaEntity(Activity activity) {
        return new ActivityJpaEntity(
                activity.getId() == null ? null : activity.getId().getValue(),
                activity.getTimestamp(),
                activity.getOwnerAccountId().getValue(),
                activity.getSourceAccountId().getValue(),
                activity.getTargetAccountId().getValue(),
                activity.getMoney().getAmount().longValue());
    }
}
