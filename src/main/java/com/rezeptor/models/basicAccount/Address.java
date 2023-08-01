package com.rezeptor.models.basicAccount;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_address")
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

  @Column(name = "roadName", length = 50)
  private String roadName;

  @Column(name = "village", length = 50)
  private String village;

  @Column(name = "subdistrict", length = 50)
  private String subdistrict;

  @Column(name = "regency", length = 50)
  private String regency;

  @Column(name = "province", length = 50)
  private String province;

  @Column(name = "country", length = 50)
  private String country;

  @Column(name = "detail", length = 100)
  private String detail;

  @CreationTimestamp
  @Column(name = "createdAt", updatable = false)
  private Timestamp createdAt;

  @UpdateTimestamp
  @Column(name = "updatedAt")
  private Timestamp updatedAt;

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

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  @Override
  public String toString() {
    return roadName + ", " + village + ", " + subdistrict + ", "
        + regency + ", " + province + ", " + country + ", " + detail;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((roadName == null) ? 0 : roadName.hashCode());
    result = prime * result + ((village == null) ? 0 : village.hashCode());
    result = prime * result + ((subdistrict == null) ? 0 : subdistrict.hashCode());
    result = prime * result + ((regency == null) ? 0 : regency.hashCode());
    result = prime * result + ((province == null) ? 0 : province.hashCode());
    result = prime * result + ((country == null) ? 0 : country.hashCode());
    result = prime * result + ((detail == null) ? 0 : detail.hashCode());
    return result;
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
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (roadName == null) {
      if (other.roadName != null)
        return false;
    } else if (!roadName.equals(other.roadName))
      return false;
    if (village == null) {
      if (other.village != null)
        return false;
    } else if (!village.equals(other.village))
      return false;
    if (subdistrict == null) {
      if (other.subdistrict != null)
        return false;
    } else if (!subdistrict.equals(other.subdistrict))
      return false;
    if (regency == null) {
      if (other.regency != null)
        return false;
    } else if (!regency.equals(other.regency))
      return false;
    if (province == null) {
      if (other.province != null)
        return false;
    } else if (!province.equals(other.province))
      return false;
    if (country == null) {
      if (other.country != null)
        return false;
    } else if (!country.equals(other.country))
      return false;
    if (detail == null) {
      if (other.detail != null)
        return false;
    } else if (!detail.equals(other.detail))
      return false;
    return true;
  }

  

}
