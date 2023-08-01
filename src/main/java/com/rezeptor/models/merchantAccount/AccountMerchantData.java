package com.rezeptor.models.merchantAccount;

import java.sql.Timestamp;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.rezeptor.models.basicAccount.Account;
import com.rezeptor.models.basicAccount.Notification;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_accountMerchantData")
public class AccountMerchantData {
  
  @SequenceGenerator(
    name = "sequence_accountMerchantDataId",
    sequenceName = "sequence_accountMerchantDataId",
    allocationSize = 1)
  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "sequence_accountMerchantDataId")
  @Column(name = "id")
  private Long id;

  @Column(name = "shopName", length = 50)
  private String shopName;

  @Column(name = "domain", length = 50)
  private String domain;

  @Column(name = "description", length = 500)
  private String description;

  @Column(name = "averageReview", length = 10)
  private double averageReview;

  @Column(name = "city", length = 50)
  private String city;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "accountId")
  private Account account;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "merchantDataId")
  private Set<Item> items;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "merchantDataId")
  private Set<Notification> notifications;

  @CreationTimestamp
  @Column(name = "createdAt", updatable = false)
  private Timestamp createdAt;

  @UpdateTimestamp
  @Column(name = "updatedAt")
  private Timestamp updatedAt;

}
