package com.rezeptor.modules.itemVariant;

import java.sql.Timestamp;
import java.util.Map;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.rezeptor.modules.ItemParrentVariant.ItemParrentVariant;
import com.rezeptor.modules.item.Item;
import com.rezeptor.modules.itemImage.ItemImage;
import com.rezeptor.utils.JsonConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
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
@Table(name = "itemVariant")
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

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "item_parrent_variant_id",referencedColumnName = "id")
  private ItemParrentVariant itemParrentVariant;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "item_parrent_id",referencedColumnName = "id")
  private Item item;
  
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "item_image_id", referencedColumnName = "id")
  private ItemImage itemImage;

  @Column(name = "name", length = 100, nullable = false)
  private String name;
  
  @Column(name = "SKU", length = 100, nullable = false)
  private String SKU;
  
  @Column(name = "variant_combinations", columnDefinition = "jsonb")
  @Convert(converter = JsonConverter.class)
  private Map<String, Object> variantCombinations;

  @Column(name = "price", nullable = false)
  private double price;

  @Column(name = "itemDescription", length = 500, nullable = false)
  private String itemDescription;

  @Column(name = "quantity", nullable = false)
  private int quantity;

  @CreationTimestamp
  @Column(name = "createdAt", updatable = false)
  private Timestamp createdAt;

  @UpdateTimestamp
  @Column(name = "updatedAt")
  private Timestamp updatedAt;

  public ItemVariant() {
  }

  public ItemVariant(Item item, ItemImage itemImage,String name, String SKU, Map<String, Object> variantCombinations, double price,
		String itemDescription, int quantity) {
	this.item = item;
	this.itemImage = itemImage;
	this.name = name;
	this.SKU = SKU;
	this.variantCombinations = variantCombinations;
	this.price = price;
	this.itemDescription = itemDescription;
	this.quantity = quantity;
  }

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Item getItem() {
		return item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public ItemImage getItemImage() {
		return itemImage;
	}
	
	public void setItemImage(ItemImage itemImage) {
		this.itemImage = itemImage;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSKU() {
		return SKU;
	}
	
	public void setSKU(String sKU) {
		SKU = sKU;
	}
	
	public Map<String, Object> getVariantCombinations() {
		return variantCombinations;
	}
	
	public void setVariantCombinations(Map<String, Object> variantCombinations) {
		this.variantCombinations = variantCombinations;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getItemDescription() {
		return itemDescription;
	}
	
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
