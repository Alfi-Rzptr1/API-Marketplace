package com.rezeptor.models.basicAccount;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.rezeptor.models.merchantAccount.Item;
import com.rezeptor.models.merchantAccount.ItemVariant;

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
@Table(name = "tbl_cart")
public class Cart {
  
  @SequenceGenerator(
    name = "sequence_cartId",
    sequenceName = "sequence_cartId",
    allocationSize = 1)
  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "sequence_cartId")
  @Column(name = "id")
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "accountMerchantId")
  private Account merchant;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "itemId")
  private Item item;

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
