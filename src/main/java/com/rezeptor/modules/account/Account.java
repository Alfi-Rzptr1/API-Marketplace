package com.rezeptor.modules.account;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.rezeptor.auth.token.ConfirmationToken;
import com.rezeptor.modules.accountData.AccountData;
import com.rezeptor.modules.merchantData.MerchantData;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "account")
public class Account implements UserDetails{
  
  @SequenceGenerator(
    name = "sequence_accountId",
    sequenceName = "sequence_accountId",
    allocationSize = 1)
  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "sequence_accountId")
  @Column(name = "id")
  private Long id;

  @Column(name = "username", length = 50, nullable = false)
  private String username;

  @Column(name = "email", length = 255, unique = true, nullable = false)
  private String email;

  @Nullable
  @Column(name = "emailVerifiedAt", nullable = true)
  private Timestamp emailVerifiedAt;

  @Column(name = "password", length = 255, nullable = false)
  private String password;
  
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "account_data_id", referencedColumnName = "id")
  private AccountData accountData;
  
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "merchant_data_id", referencedColumnName = "id")
  private MerchantData merchantData;

  @Enumerated(EnumType.STRING)
  @Column(name = "role", length = 50, nullable = false)
  private AccessRole role;
  
  @Column(name ="locked")
  private Boolean locked = false;
  
  @Column(name = "enabled")
  private Boolean enabled = false;
  
  @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<ConfirmationToken> tokens;

  @CreationTimestamp
  @Column(name = "createdAt", updatable = false)
  private Timestamp createdAt;

  @Nullable
  @UpdateTimestamp
  @Column(name = "updatedAt",nullable = true)
  private Timestamp updatedAt; 
  
  @Column(name = "deleted")
  private Boolean deleted = false;

  public Account() {
  }

  public Account(String username, String email, String password, AccountData accountData, MerchantData merchantData,
		AccessRole role, List<ConfirmationToken> tokens) {
	super();
	this.username = username;
	this.email = email;
	this.password = password;
	this.accountData = accountData;
	this.merchantData = merchantData;
	this.role = role;
	this.tokens = tokens;
  }

  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  
  
  public AccountData getAccountData() {
	return accountData;
  }

  public void setAccountData(AccountData accountData) {
	this.accountData = accountData;
  }	

  public MerchantData getMerchantData() {
	return merchantData;
  }

  public void setMerchantData(MerchantData merchantData) {
	this.merchantData = merchantData;
  }

  public List<ConfirmationToken> getTokens() {
	return tokens;
  }

  public void setTokens(List<ConfirmationToken> tokens) {
	this.tokens = tokens;
  }

  public Timestamp getEmailVerifiedAt() {
	return emailVerifiedAt;
  }

  public void setEmailVerifiedAt(Timestamp emailVerifiedAt) {
	this.emailVerifiedAt = emailVerifiedAt;
  }

  public AccessRole getRole() {
    return role;
  }

  public void setRole(AccessRole role) {
    this.role = role;
  }
  
  public Boolean getLocked() {
	return locked;
  }

  public void setLocked(Boolean locked) {
	this.locked = locked;
  }

  public Boolean getEnabled() {
	return enabled;
  }

  public void setEnabled(Boolean enabled) {
	this.enabled = enabled;
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
  
  public Boolean getDeleted() {
	return deleted;
  }

  public void setDeleted(Boolean deleted) {
	this.deleted = deleted;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
	  SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
	  return Collections.singletonList(authority);  
  }

  @Override
  public boolean isAccountNonExpired() {
	// TODO Auto-generated method stub
	return true;
  }

  @Override
  public boolean isAccountNonLocked() {
	// TODO Auto-generated method stub
	  return !locked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
	// TODO Auto-generated method stub
	return true;
  }

  @Override
  public boolean isEnabled() {
	// TODO Auto-generated method stub
	return enabled;
  }



}
