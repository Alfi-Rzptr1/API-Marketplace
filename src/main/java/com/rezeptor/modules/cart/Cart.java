package com.rezeptor.modules.cart;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.rezeptor.modules.cartItem.CartItem;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")
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

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<CartItem> cartItems;

  @CreationTimestamp
  @Column(name = "createdAt", updatable = false)
  private Timestamp createdAt;

  @UpdateTimestamp
  @Column(name = "updatedAt")
  private Timestamp updatedAt;

}
