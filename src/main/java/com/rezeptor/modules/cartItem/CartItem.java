package com.rezeptor.modules.cartItem;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.rezeptor.modules.cart.Cart;
import com.rezeptor.modules.item.Item;
import com.rezeptor.modules.itemVariant.ItemVariant;
import com.rezeptor.modules.merchantData.MerchantData;

import jakarta.persistence.Column;
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
@Table(name = "cartItem")
public class CartItem {
  
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
  
  @ManyToOne
  @JoinColumn(name = "cart_id", referencedColumnName = "id")
  private Cart cart;

  @OneToOne( fetch = FetchType.EAGER)
  @JoinColumn(name = "cart_merchant_id", referencedColumnName = "id")
  private MerchantData merchant;

  @OneToOne( fetch = FetchType.EAGER)
  @JoinColumn(name = "cart_item_id", referencedColumnName = "id")
  private Item item;

  @OneToOne( fetch = FetchType.EAGER)
  @JoinColumn(name = "cart_item_variant_id", referencedColumnName = "id")
  private ItemVariant itemVariant;

  @Column(name = "quantity")
  private int quantity;
  
  @CreationTimestamp
  @Column(name = "createdAt", updatable = false)
  private Timestamp createdAt;

  @UpdateTimestamp
  @Column(name = "updatedAt")
  private Timestamp updatedAt;

  public CartItem() {
  }

  public CartItem(MerchantData merchant, Item item, ItemVariant itemVariant, Cart cart, int quantity) {
    this.merchant = merchant;
    this.item = item;
    this.itemVariant = itemVariant;
    this.cart = cart;
    this.quantity = quantity;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public MerchantData getMerchant() {
    return merchant;
  }

  public void setMerchant(MerchantData merchant) {
    this.merchant = merchant;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public ItemVariant getItemVariant() {
    return itemVariant;
  }

  public void setItemVariant(ItemVariant itemVariant) {
    this.itemVariant = itemVariant;
  }

  public Cart getCart() {
    return cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
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
