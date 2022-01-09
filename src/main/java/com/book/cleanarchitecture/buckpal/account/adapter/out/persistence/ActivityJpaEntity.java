package com.book.cleanarchitecture.buckpal.account.adapter.out.persistence;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "activities")
public class ActivityJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime timestamp;

    @Column
    private Long ownerAccountId;

    @Column
    private Long sourceAccountId;

    @Column
    private Long targetAccountId;

    @Column
    private Long amount;

    protected ActivityJpaEntity() {
    }

    public ActivityJpaEntity(Long id, LocalDateTime timestamp, Long ownerAccountId, Long sourceAccountId, Long targetAccountId, Long amount) {
        this.id = id;
        this.timestamp = timestamp;
        this.ownerAccountId = ownerAccountId;
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Long getOwnerAccountId() {
        return ownerAccountId;
    }

    public Long getSourceAccountId() {
        return sourceAccountId;
    }

    public Long getTargetAccountId() {
        return targetAccountId;
    }

    public Long getAmount() {
        return amount;
    }
}
