package com.rezeptor.auth.token;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import com.rezeptor.modules.account.Account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "confirmationToken")
public class ConfirmationToken {

	@SequenceGenerator(
		    name = "sequence_tokenId",
		    sequenceName = "sequence_tokenId",
		    allocationSize = 1)
	@Id
	@GeneratedValue(
		    strategy = GenerationType.SEQUENCE,
		    generator = "sequence_tokenId")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "token")
	private String token;
	
	@CreationTimestamp
	@Column(name = "createdAt",nullable = false)
	private Date createdAt;
	
	@Column(name = "expiresAt",nullable = false)
	private LocalDateTime expiresAt;
	
	@Column(name = "confirmedAt")
	private LocalDateTime confirmedAt;
	
	@ManyToOne
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Account account;
	
	public ConfirmationToken(String token, LocalDateTime expiresAt, Account account) {
		this.token = token;
		this.expiresAt = expiresAt;
		this.account = account;
	}

	public ConfirmationToken() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}

	public LocalDateTime getConfirmedAt() {
		return confirmedAt;
	}

	public void setConfirmedAt(LocalDateTime confirmedAt) {
		this.confirmedAt = confirmedAt;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public int hashCode() {
		return Objects.hash(account, confirmedAt, createdAt, expiresAt, id, token);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConfirmationToken other = (ConfirmationToken) obj;
		return Objects.equals(account, other.account) && Objects.equals(confirmedAt, other.confirmedAt)
				&& Objects.equals(createdAt, other.createdAt) && Objects.equals(expiresAt, other.expiresAt)
				&& Objects.equals(id, other.id) && Objects.equals(token, other.token);
	}

	@Override
	public String toString() {
		return "ConfirmationToken [id=" + id + ", token=" + token + ", createdAt=" + createdAt + ", expiresAt="
				+ expiresAt + ", confirmedAt=" + confirmedAt + ", account=" + account + "]";
	}
	
	
	
}
