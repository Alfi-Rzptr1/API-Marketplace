package com.rezeptor.modules.merchantData;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.rezeptor.modules.account.Account;
import com.rezeptor.modules.item.Item;

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
@Table(name = "merchantData")
public class MerchantData {
  
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

  @Column(name = "shopName", length = 50, unique = true, nullable = false)
  private String shopName;

  @Column(name = "domain", length = 50, unique = true, nullable = false)
  private String domain;

  @Column(name = "description", length = 500, nullable = false)
  private String description;

  @Column(name = "averageReview", length = 10)
  private double averageReview = 0;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id")
  private Account account;
  
  @OneToMany(mappedBy = "merchantData", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Item> items;

  @CreationTimestamp
  @Column(name = "createdAt", updatable = false)
  private Timestamp createdAt;

  @UpdateTimestamp
  @Column(name = "updatedAt")
  private Timestamp updatedAt;

  public MerchantData() {
  }

  public MerchantData(String shopName, String domain, String description,Account account) {
    this.shopName = shopName;
    this.domain = domain;
    this.description = description;
    this.account = account;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
  }

  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getAverageReview() {
    return averageReview;
  }

  public void setAverageReview(double averageReview) {
    this.averageReview = averageReview;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public Timestamp getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Timestamp updatedAt) {
    this.updatedAt = updatedAt;
  }

  

}
