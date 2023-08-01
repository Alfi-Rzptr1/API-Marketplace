package com.rezeptor.models.basicAccount;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

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
@Table(name = "tbl_accountBasicData")
public class AccountBasicData {
  
  @SequenceGenerator(
    name = "sequence_accountBasicDataId",
    sequenceName = "sequence_accountBasicDataId",
    allocationSize = 1)
  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "sequence_accountBasicDataId")
  @Column(name = "id")
  private Long id;
  
  @OneToOne(mappedBy = "accountBasicData",fetch = FetchType.LAZY)
  private Account account;

  @Nullable
  @Column(name = "birthDate", length = 30)
  private Date birthDate;

  @Nullable
  @Column(name = "gender", length = 30)
  private String gender;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "basicDataId")
  private Set<Address> address;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "basicDataId")
  private Set<Cart> carts;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "basicDataId")
  private Set<Order> orders;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "basicDataId")
  private Set<Notification> notifications;

  @CreationTimestamp
  @Column(name = "createdAt", updatable = false)
  private Timestamp createdAt;

  @UpdateTimestamp
  @Column(name = "updatedAt")
  private Timestamp updatedAt; 
  
}
