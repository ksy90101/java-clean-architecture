package com.book.cleanarchitecture.buckpal.account.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

interface ActivityRepository extends JpaRepository<ActivityJpaEntity, Long> {

    @Query("SELECT activites FROM ActivityJpaEntity activites " +
            "WHERE activites.ownerAccountId = :ownerAccountId " +
            "AND activites.timestamp >= :since")
    List<ActivityJpaEntity> findByOwnerSince(@Param("ownerAccountId") Long ownerAccountId, @Param("since") LocalDateTime since);

    @Query("SELECT SUM(activites.amount) FROM ActivityJpaEntity activites " +
            "WHERE activites.targetAccountId = :accountId " +
            "AND activites.ownerAccountId = :accountId " +
            "AND activites.timestamp < :until")
    Long getDepositBalanceUntil(@Param("accountId") Long accountId, @Param("until") LocalDateTime until);

    @Query("SELECT SUM(activites.amount) FROM ActivityJpaEntity activites " +
            "WHERE activites.sourceAccountId = :accountId " +
            "AND activites.ownerAccountId = :accountId " +
            "AND activites.timestamp < :until")
    Long getWithdrawalBalanceUntil(@Param("accountId") Long accountId, @Param("until") LocalDateTime until);
}
