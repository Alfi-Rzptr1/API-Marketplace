package com.rezeptor.modules.accountData;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import com.rezeptor.modules.account.Account;
import com.rezeptor.modules.accountImage.AccountImage;
import com.rezeptor.modules.address.Address;
import com.rezeptor.modules.cart.Cart;
import com.rezeptor.modules.itemReview.ItemReview;
import com.rezeptor.modules.notification.Notification;
import com.rezeptor.modules.order.Order;

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
@Table(name = "accountData")
public class AccountData {

	  @SequenceGenerator(
			  name = "sequence_accountDataId",
			  sequenceName = "sequence_accountDataId",
			  allocationSize = 1)
	  @Id
	  @GeneratedValue(
			  strategy = GenerationType.SEQUENCE,
			  generator = "sequence_accountDataId")
	  @Column(name = "id")
	  private Long id;
	  
	  @Column(name = "firstName", length = 50, nullable = false)
	  private String firstName;
	  
	  @Column(name = "lastName", length = 50, nullable = false)
	  private String lastName;

	  @Nullable
	  @Column(name = "birthDate", length = 30)
	  private LocalDate birthDate;

	  @Nullable
	  @Column(name = "gender", length = 30)
	  private String gender;
	  
	  @OneToMany(mappedBy = "accountData", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	  private List<Address> address;
	  
	  @OneToMany(mappedBy = "accountData", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	  private List<Notification> notifications;
	  
	  @OneToMany(mappedBy = "accountData", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	  private List<ItemReview> itemReviews;
	  
	  @OneToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "account_id")
	  private Account account;
	  
	  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	  @JoinColumn(name = "account_image_id", referencedColumnName = "id")
	  private AccountImage accountImage;

	  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	  @JoinColumn(name = "cart_account_id", referencedColumnName = "id")
	  private Cart cart;

	  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	  @JoinColumn(name = "order_account_id", referencedColumnName = "id")
	  private Order order;

	  @CreationTimestamp
	  @Column(name = "createdAt", updatable = false)
	  private Timestamp createdAt;

	  @UpdateTimestamp
	  @Column(name = "updatedAt")
	  private Timestamp updatedAt;
	  
	  
	public AccountData() {
		super();
	}

	public AccountData(String firstName, String lastName, LocalDate birthDate, String gender, List<Address> address,
			List<Notification> notifications, List<ItemReview> itemReviews, Account account, AccountImage accountImage,
			Cart cart, Order order) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.address = address;
		this.notifications = notifications;
		this.itemReviews = itemReviews;
		this.account = account;
		this.accountImage = accountImage;
		this.cart = cart;
		this.order = order;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public LocalDate getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public List<Address> getAddress() {
		return address;
	}


	public void setAddress(List<Address> address) {
		this.address = address;
	}


	public List<Notification> getNotifications() {
		return notifications;
	}


	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}


	public List<ItemReview> getItemReviews() {
		return itemReviews;
	}


	public void setItemReviews(List<ItemReview> itemReviews) {
		this.itemReviews = itemReviews;
	}


	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public AccountImage getAccountImage() {
		return accountImage;
	}


	public void setAccountImage(AccountImage accountImage) {
		this.accountImage = accountImage;
	}


	public Cart getCart() {
		return cart;
	}


	public void setCart(Cart cart) {
		this.cart = cart;
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
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
