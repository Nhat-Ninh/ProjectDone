package com.javaweb.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name ="transaction")
public class TransactionEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "note")
    private String note;
    @Column(name = "staffid")
    private Long staffId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerid")
    private CustomerEntity customerEntity;


}
