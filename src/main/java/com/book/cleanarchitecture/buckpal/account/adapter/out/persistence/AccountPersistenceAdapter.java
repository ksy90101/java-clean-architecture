package com.book.cleanarchitecture.buckpal.account.adapter.out.persistence;

import com.book.cleanarchitecture.buckpal.account.application.port.out.LoadAccountPort;
import com.book.cleanarchitecture.buckpal.account.application.port.out.UpdateAccountStatePort;
import com.book.cleanarchitecture.buckpal.account.domain.Account;
import com.book.cleanarchitecture.buckpal.account.domain.Activity;
import com.book.cleanarchitecture.buckpal.account.domain.vo.AccountId;
import com.book.cleanarchitecture.buckpal.shared.PersistenceAdapter;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@PersistenceAdapter
public class AccountPersistenceAdapter implements LoadAccountPort, UpdateAccountStatePort {

    private final SpringDataAccountRepository accountRepository;
    private final ActivityRepository activityRepository;
    private final AccountMapper accountMapper;

    public AccountPersistenceAdapter(SpringDataAccountRepository accountRepository, ActivityRepository activityRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.activityRepository = activityRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public Account loadAccount(
            AccountId accountId,
            LocalDateTime baselineDate) {

        AccountJpaEntity account =
                accountRepository.findById(accountId.getValue())
                        .orElseThrow(EntityNotFoundException::new);

        List<ActivityJpaEntity> activities =
                activityRepository.findByOwnerSince(
                        accountId.getValue(),
                        baselineDate);

        Long withdrawalBalance = orZero(activityRepository
                .getWithdrawalBalanceUntil(
                        accountId.getValue(),
                        baselineDate));

        Long depositBalance = orZero(activityRepository
                .getDepositBalanceUntil(
                        accountId.getValue(),
                        baselineDate));

        return accountMapper.mapToDomainEntity(
                account,
                activities,
                withdrawalBalance,
                depositBalance);

    }

    private Long orZero(Long value) {
        return value == null ? 0L : value;
    }


    @Override
    public void updateActivities(Account account) {
        for (Activity activity : account.getActivityWindow().getActivities()) {
            if (activity.getId() == null) {
                activityRepository.save(accountMapper.mapToJpaEntity(activity));
            }
        }
    }

}
