package com.book.cleanarchitecture.buckpal.account.domain;

import com.book.cleanarchitecture.buckpal.account.domain.vo.AccountId;
import com.book.cleanarchitecture.buckpal.account.domain.vo.Money;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ActivityWindow {

    private final List<Activity> activities;

    public ActivityWindow(List<Activity> activities) {
        this.activities = activities;
    }

    public ActivityWindow(Activity... activities) {
        this.activities = Arrays.stream(activities)
                .collect(Collectors.toList());
    }

    public LocalDateTime getStartTimestamp() {
        return activities.stream()
                .min(Comparator.comparing(Activity::getTimestamp))
                .orElseThrow(IllegalStateException::new)
                .getTimestamp();
    }

    public LocalDateTime getEndTimestamp() {
        return activities.stream()
                .max(Comparator.comparing(Activity::getTimestamp))
                .orElseThrow(IllegalStateException::new)
                .getTimestamp();
    }

    public Money calculateBalance(AccountId accountId) {
        Money depositBalance = activities.stream()
                .filter(activity -> activity.getTargetAccountId().equals(accountId))
                .map(Activity::getMoney)
                .reduce(Money.ZERO, Money::plus);

        Money withdrawalBalance = activities.stream()
                .filter(activity -> activity.getSourceAccountId().equals(accountId))
                .map(Activity::getMoney)
                .reduce(Money.ZERO, Money::plus);

        return depositBalance.plus(withdrawalBalance.negate());
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }

    public List<Activity> getActivities() {
        return Collections.unmodifiableList(this.activities);
    }
}
