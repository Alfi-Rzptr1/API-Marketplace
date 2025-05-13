package com.rezeptor.modules.itemReview;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.rezeptor.modules.accountData.AccountData;
import com.rezeptor.modules.item.Item;
import com.rezeptor.modules.itemVariant.ItemVariant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "itemReview")
public class ItemReview {
  
  @SequenceGenerator(
    name = "sequence_itemReviewId",
    sequenceName = "sequence_itemReviewId",
    allocationSize = 1)
  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "sequence_itemReviewId")
  @Column(name = "id")
  private Long id;

  @Column(name = "star", nullable = false)
  private double star;

  @Column(name = "message", length = 300, nullable = false)
  private String message;

  @ManyToOne
  @JoinColumn(name = "account_reviewer_id", referencedColumnName = "id")
  private AccountData accountData;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "review_item_id", referencedColumnName = "id")
  private Item item;

  @ManyToOne
  @JoinColumn(name = "review_item_variant_id", referencedColumnName = "id")
  private ItemVariant itemVariant;

  @CreationTimestamp
  @Column(name = "createdAt", updatable = false)
  private Timestamp createdAt;

  @UpdateTimestamp
  @Column(name = "updatedAt")
  private Timestamp updatedAt;

  public ItemReview() {
  }

  public ItemReview(double star, String message, AccountData accountData, Item item, ItemVariant itemVariant) {
    this.star = star;
    this.message = message;
    this.accountData = accountData;
    this.item = item;
    this.itemVariant = itemVariant;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public double getStar() {
    return star;
  }

  public void setStar(double star) {
    this.star = star;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public AccountData getAccount() {
    return accountData;
  }

  public void setAccount(AccountData accountData) {
    this.accountData = accountData;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public ItemVariant getItemVariant() {
    return itemVariant;
  }

  public void setItemVariant(ItemVariant itemVariant) {
    this.itemVariant = itemVariant;
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
