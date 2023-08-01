package com.rezeptor.models.merchantAccount;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_itemVariant")
public class ItemVariant {

  @SequenceGenerator(
    name = "sequence_itemVariantId",
    sequenceName = "sequence_itemVariantId",
    allocationSize = 1)
  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "sequence_itemVariantId")
  @Column(name = "id")
  private Long id;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "itemImageId", referencedColumnName = "id")
  private ItemImage itemImage;

  @Column(name = "itemName", length = 100)
  private String itemName;

  @Column(name = "price")
  private double price;

  @Column(name = "itemDescription", length = 500)
  private String itemDescription;

  @Column(name = "quantity")
  private int quantity;

  @CreationTimestamp
  @Column(name = "createdAt", updatable = false)
  private Timestamp createdAt;

  @UpdateTimestamp
  @Column(name = "updatedAt")
  private Timestamp updatedAt;


}
