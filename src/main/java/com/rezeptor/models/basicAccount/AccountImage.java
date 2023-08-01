package com.rezeptor.models.basicAccount;

import java.sql.Timestamp;
import java.util.Arrays;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_accountImage")
public class AccountImage {
  
  @SequenceGenerator(
    name = "sequence_accountImageId",
    sequenceName = "sequence_accountImageId",
    allocationSize = 1)
  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "sequence_accountImageId")
  @Column(name = "id")
  private Long id;

  @Column(name = "fileName", length = 255)
  private String fileName;

  @Column(name = "fileType", length = 100)
  private String type;

  @Lob
  @Column(name = "data", length = 1000)
  private byte[] data;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "accountId")
  private Account account;

  @CreationTimestamp
  @Column(name = "createdAt", updatable = false)
  private Timestamp createdAt;

  @UpdateTimestamp
  @Column(name = "updatedAt")
  private Timestamp updatedAt;

  public void setId(Long id){
    this.id = id;
  }

  public Long getId(){
    return this.id;
  }

  public void setAccount(Account account){
    this.account = account;
  }

  public Account getAccount(){
    return this.account;
  }

  public void setName(String fileName){
    this.fileName = fileName;
  }

  public String getName(){
    return this.fileName;
  }

  public void setType(String type){
    this.type = type;
  }

  public String getType(){
    return this.type;
  }

  public void setImageDate(byte[] data){
    this.data = data;
  }

  public byte[] getImageData(){
    return this.data;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((account == null) ? 0 : account.hashCode());
    result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
    result = prime * result + ((type == null) ? 0 : type.hashCode());
    result = prime * result + Arrays.hashCode(data);
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
    AccountImage other = (AccountImage) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (account == null) {
      if (other.account != null)
        return false;
    } else if (!account.equals(other.account))
      return false;
    if (fileName == null) {
      if (other.fileName != null)
        return false;
    } else if (!fileName.equals(other.fileName))
      return false;
    if (type == null) {
      if (other.type != null)
        return false;
    } else if (!type.equals(other.type))
      return false;
    if (!Arrays.equals(data, other.data))
      return false;
    return true;
  }

  
  
}
