package com.rezeptor.models.merchantAccount;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_itemImage")
public class ItemImage {
  
  
  @SequenceGenerator(
    name = "sequence_itemImageId",
    sequenceName = "sequence_itemImageId",
    allocationSize = 1)
  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "sequence_itemImageId")
  @Column(name = "id")
  private Long id;

  @Column(name = "fileName", length = 255)
  private String filename;

  @Column(name = "fileType", length = 100)
  private String type;

  @Lob
  @Column(name = "data", length = 1000)
  private byte[] data;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "itemId")
  private Item item;

  @OneToOne(mappedBy = "itemImage", fetch = FetchType.LAZY)
  //@JoinColumn(name = "itemVariantId")
  private ItemVariant itemVariant;

  @CreationTimestamp
  @Column(name = "createdAt", updatable = false)
  private Timestamp createdAt;

  @UpdateTimestamp
  @Column(name = "updatedAt")
  private Timestamp updatedAt;
}
