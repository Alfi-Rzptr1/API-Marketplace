package com.rezeptor.models.merchantAccount;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.rezeptor.models.basicAccount.Account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_itemReview")
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

  @Column(name = "star")
  private double star;

  @Column(name = "message", length = 300)
  private String message;

  @OneToOne()
  @JoinColumn(name = "accountId")
  private Account account;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "itemVariantId")
  private ItemVariant itemVariant;

  @CreationTimestamp
  @Column(name = "createdAt", updatable = false)
  private Timestamp createdAt;

  @UpdateTimestamp
  @Column(name = "updatedAt")
  private Timestamp updatedAt;
}
