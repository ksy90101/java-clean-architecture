package com.book.cleanarchitecture.buckpal.account.adapter.out.persistence;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
class AccountJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected AccountJpaEntity() {
    }

    public Long getId() {
        return id;
    }
}
