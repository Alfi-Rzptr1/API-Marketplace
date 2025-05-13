package com.rezeptor.modules.item;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.rezeptor.modules.itemCategory.ItemCategory;
import com.rezeptor.modules.itemImage.ItemImage;
import com.rezeptor.modules.itemReview.ItemReview;
import com.rezeptor.modules.itemVariant.ItemVariant;
import com.rezeptor.modules.merchantData.MerchantData;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "item")
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
  
  @Column(name = "name", length = 100, nullable = false)
  private String name;

  @Column(name = "totalQuantity",nullable = false)
  private int totalQuantity;

  @Column(name = "selled")
  private int selled = 0;

  @Column(name = "isCOD")
  private boolean isCOD;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "merchant_data_id",referencedColumnName = "id")
  private MerchantData merchantData;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "category_id",referencedColumnName = "id")
  private ItemCategory category;

  @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<ItemVariant> itemVariants;
  
  @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<ItemImage> itemImages;
  
  @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<ItemReview> itemReviews;

  @CreationTimestamp
  @Column(name = "createdAt", updatable = false)
  private Timestamp createdAt;

  @UpdateTimestamp
  @Column(name = "updatedAt")
  private Timestamp updatedAt;

  public Item() {
  }
	
  public Item(String name, int totalQuantity, int selled, boolean isCOD, MerchantData merchantData, ItemCategory category,
			List<ItemVariant> itemVariants, List<ItemImage> itemImages) {
		this.name = name;
		this.totalQuantity = totalQuantity;
		this.selled = selled;
		this.isCOD = isCOD;
		this.merchantData = merchantData;
		this.category = category;
		this.itemVariants = itemVariants;
		this.itemImages = itemImages;
  }

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getTotalQuantity() {
	return totalQuantity;
}

public void setTotalQuantity(int totalQuantity) {
	this.totalQuantity = totalQuantity;
}

public int getSelled() {
	return selled;
}

public void setSelled(int selled) {
	this.selled = selled;
}

public boolean isCOD() {
	return isCOD;
}

public void setCOD(boolean isCOD) {
	this.isCOD = isCOD;
}

public MerchantData getMerchantData() {
	return merchantData;
}

public void setMerchantData(MerchantData merchantData) {
	this.merchantData = merchantData;
}

public ItemCategory getCategory() {
	return category;
}

public void setCategory(ItemCategory category) {
	this.category = category;
}

public List<ItemVariant> getItemVariants() {
	return itemVariants;
}

public void setItemVariants(List<ItemVariant> itemVariants) {
	this.itemVariants = itemVariants;
}

public List<ItemImage> getItemImages() {
	return itemImages;
}

public void setItemImages(List<ItemImage> itemImages) {
	this.itemImages = itemImages;
}

public List<ItemReview> getItemReviews() {
	return itemReviews;
}

public void setItemReviews(List<ItemReview> itemReviews) {
	this.itemReviews = itemReviews;
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
