package com.rezeptor.modules.ItemParrentVariant;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.rezeptor.modules.item.Item;
import com.rezeptor.modules.itemVariant.ItemVariant;

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
@Table(name = "itemParrentVariant")
public class ItemParrentVariant {
	
	  @SequenceGenerator(
			    name = "sequence_itemParrentVariantId",
			    sequenceName = "sequence_itemParrentVariantId",
			    allocationSize = 1)
	  @Id
	  @GeneratedValue(
			    strategy = GenerationType.SEQUENCE,
			    generator = "sequence_itemParrentVariantId")
	  @Column(name = "id")
	  private Long id;
	  
	  @ManyToOne(fetch = FetchType.EAGER, optional = false)
	  @JoinColumn(name = "item_id",referencedColumnName = "id")
	  private Item item;
	  
	  @OneToMany(mappedBy = "itemParrentVariant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	  private List<ItemVariant> itemVariants;
	  
	  @Column(name = "itemName", length = 100, nullable = false)
	  private String name;
	  
	  @CreationTimestamp
	  @Column(name = "createdAt", updatable = false)
	  private Timestamp createdAt;

	  @UpdateTimestamp
	  @Column(name = "updatedAt")
	  private Timestamp updatedAt;
	  	
	  public ItemParrentVariant() {
	  }

	public ItemParrentVariant(Item item, String name) {
		this.item = item;
		this.name = name;
	  }

	public ItemParrentVariant(Item item, List<ItemVariant> itemVariants, String name) {
		this.item = item;
		this.itemVariants = itemVariants;
		this.name = name;
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

	public List<ItemVariant> getItemVariants() {
		return itemVariants;
	}

	public void setItemVariants(List<ItemVariant> itemVariants) {
		this.itemVariants = itemVariants;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
