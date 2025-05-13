package com.rezeptor.modules.orderItem;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.rezeptor.modules.accountData.AccountData;
import com.rezeptor.modules.item.Item;
import com.rezeptor.modules.merchantData.MerchantData;
import com.rezeptor.modules.order.Order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "orderItem")
public class OrderItem {
  
  @SequenceGenerator(
    name = "sequence_OrderItemId",
    sequenceName = "sequence_OrderItemId",
    allocationSize = 1)
  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "sequence_basicAccountOrderId")
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "order_id", referencedColumnName = "id")
  private Order order;
  
  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "buyer_account_id", referencedColumnName = "id")
  private AccountData buyerAccount;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "merchant_account_id", referencedColumnName = "id")
  private MerchantData merchantAccount;
  
  @Enumerated(EnumType.STRING)
  @Column(name = "orderType", length = 50,nullable = false)
  private OrderType orderType;
  
  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "order_item_id", referencedColumnName = "id")
  private Item item;

  @Column(name = "quantity",nullable = false)
  private int quantity;

  @Column(name = "paymentMethod", length = 50,nullable = false)
  private String paymentMethod;

  @Enumerated(EnumType.STRING)
  @Column(name = "orderStatus", length = 50,nullable = false)
  private OrderStatus orderStatus;

  @CreationTimestamp
  @Column(name = "createdAt", updatable = false)
  private Timestamp createdAt;

  @UpdateTimestamp
  @Column(name = "updatedAt")
  private Timestamp updatedAt;

  public OrderItem() {
  }

  public OrderItem(AccountData buyerAccount, MerchantData merchantAccount,OrderType orderType, Item item, int quantity, String paymentMethod,
      OrderStatus orderStatus, Order order) {
    this.buyerAccount = buyerAccount;
    this.merchantAccount = merchantAccount;
    this.orderType = orderType;
    this.item = item;
    this.quantity = quantity;
    this.paymentMethod = paymentMethod;
    this.orderStatus = orderStatus;
    this.order = order;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public AccountData getBuyerAccount() {
    return buyerAccount;
  }

  public void setBuyerAccount(AccountData buyerAccount) {
    this.buyerAccount = buyerAccount;
  }

  public MerchantData getMerchantAccount() {
    return merchantAccount;
  }

  public void setMerchantAccount(MerchantData merchantAccount) {
    this.merchantAccount = merchantAccount;
  }
  
  public OrderType getOrderType() {
	  return orderType;
  }
  
  public void setOrderType(OrderType orderType) {
	  this.orderType = orderType;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
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
