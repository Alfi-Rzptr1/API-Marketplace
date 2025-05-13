package com.rezeptor.modules.address;

import java.sql.Timestamp;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.rezeptor.modules.accountData.AccountData;

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
@Table(name = "Address")
public class Address {
  
  @SequenceGenerator(
    name = "sequence_addressId",
    sequenceName = "sequence_addressId",
    allocationSize = 1)
  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "sequence_addressId")
  @Column(name = "id")
  private Long id;

  @Column(name = "roadName", length = 50,nullable = false)
  private String roadName;

  @Column(name = "village", length = 50,nullable = false)
  private String village;

  @Column(name = "subdistrict", length = 50,nullable = false)
  private String subdistrict;

  @Column(name = "regency", length = 50,nullable = false)
  private String regency;

  @Column(name = "province", length = 50,nullable = false)
  private String province;

  @Column(name = "detail", length = 100,nullable = false)
  private String detail;

  @ManyToOne
  @JoinColumn(name = "account_data_id", referencedColumnName = "id")
  private AccountData accountData;

  @CreationTimestamp
  @Column(name = "createdAt", updatable = false)
  private Timestamp createdAt;

  @UpdateTimestamp
  @Column(name = "updatedAt")
  private Timestamp updatedAt;

  public Address() {
  }

  public Address(String roadName, String village, String subdistrict, String regency, String province,
      String detail, AccountData accountData) {
    this.roadName = roadName;
    this.village = village;
    this.subdistrict = subdistrict;
    this.regency = regency;
    this.province = province;
    this.detail = detail;
    this.accountData = accountData;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRoadName() {
    return roadName;
  }

  public void setRoadName(String roadName) {
    this.roadName = roadName;
  }

  public String getVillage() {
    return village;
  }

  public void setVillage(String village) {
    this.village = village;
  }

  public String getSubdistrict() {
    return subdistrict;
  }

  public void setSubdistrict(String subdistrict) {
    this.subdistrict = subdistrict;
  }

  public String getRegency() {
    return regency;
  }

  public void setRegency(String regency) {
    this.regency = regency;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }
  
  public AccountData getAccount() {
    return accountData;
  }

  public void setAccount(AccountData accountData) {
    this.accountData = accountData;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(createdAt, detail, id, province, regency, roadName, subdistrict, updatedAt, village);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(detail, other.detail)
				&& Objects.equals(id, other.id) && Objects.equals(province, other.province)
				&& Objects.equals(regency, other.regency) && Objects.equals(roadName, other.roadName)
				&& Objects.equals(subdistrict, other.subdistrict) && Objects.equals(updatedAt, other.updatedAt)
				&& Objects.equals(village, other.village);
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", roadName=" + roadName + ", village=" + village + ", subdistrict=" + subdistrict
				+ ", regency=" + regency + ", province=" + province + ", detail=" + detail + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

}
