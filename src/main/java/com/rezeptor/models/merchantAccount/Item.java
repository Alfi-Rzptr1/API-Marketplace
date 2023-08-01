package com.rezeptor.models.merchantAccount;

import java.sql.Timestamp;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_item")
public class Item {
  
  @SequenceGenerator(
    name = "sequence_itemId",
    sequenceName = "sequence_itemId",
    allocationSize = 1)
  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "sequence_itemId")
  @Column(name = "id")
  private Long id;

  @Column(name = "totalQuantity")
  private int totalQuantity;

  @Column(name = "selled")
  private int selled;

  @Column(name = "isCOD")
  private boolean isCOD;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "itemId")
  private Set<ItemVariant> itemVariants;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "itemId")
  private Set<ItemImage> itemImages;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "itemId")
  private Set<ItemReview> itemReviews;

  @CreationTimestamp
  @Column(name = "createdAt", updatable = false)
  private Timestamp createdAt;

  @UpdateTimestamp
  @Column(name = "updatedAt")
  private Timestamp updatedAt;

  

}
