package com.rezeptor.models.basicAccount;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.rezeptor.models.enums.OrderStatus;
import com.rezeptor.models.enums.PaymentMethod;
import com.rezeptor.models.merchantAccount.Item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_basicAccountOrder")
public class Order {
  
  @SequenceGenerator(
    name = "sequence_basicAccountOrderId",
    sequenceName = "sequence_basicAccountOrderId",
    allocationSize = 1)
  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "sequence_basicAccountOrderId")
  @Column(name = "id")
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "buyerAccountId")
  private Account buyerAccount;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "merchantAccountId")
  private Account merchantAccount;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "itemId")
  private Item item;

  @Column(name = "quantity")
  private int quantity;

  @Enumerated(EnumType.STRING)
  @Column(name = "paymentMethod", length = 50)
  private PaymentMethod paymentMethod;

  @Enumerated(EnumType.STRING)
  @Column(name = "orderStatus", length = 50)
  private OrderStatus orderStatus;

  @CreationTimestamp
  @Column(name = "createdAt", updatable = false)
  private Timestamp createdAt;

  @UpdateTimestamp
  @Column(name = "updatedAt")
  private Timestamp updatedAt;

}
