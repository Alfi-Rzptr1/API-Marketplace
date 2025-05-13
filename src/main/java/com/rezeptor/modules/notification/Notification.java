package com.rezeptor.modules.notification;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.rezeptor.modules.accountData.AccountData;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "notification")
public class Notification {
  
  @SequenceGenerator(
    name = "sequence_notificationId",
    sequenceName = "sequence_notificationId",
    allocationSize = 1)
  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "sequence_notificationId")
  @Column(name = "id")
  private Long id;

  @Column(name = "title", length = 50,nullable = false)
  private String title;

  @Column(name = "message", length = 300,nullable = false)
  private String message;

  @ManyToOne
  @JoinColumn(name = "account_data_id", referencedColumnName = "id")
  private AccountData accountData;

  @CreationTimestamp
  @Column(name = "createdAt", updatable = false)
  private Timestamp createdAt;

  @UpdateTimestamp
  @Column(name = "updatedAt")
  private Timestamp updatedAt;

  public Notification() {
  }

  public Notification(String title, String message, AccountData accountData) {
    this.title = title;
    this.message = message;
    this.accountData = accountData;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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
