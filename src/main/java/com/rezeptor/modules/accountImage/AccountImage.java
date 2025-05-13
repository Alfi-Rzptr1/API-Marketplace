package com.rezeptor.modules.accountImage;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "accountImage")
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

  @Column(name = "fileName", length = 255,nullable = false)
  private String fileName;

  @Column(name = "fileType", length = 100,nullable = false)
  private String type;

  @Lob
  @Column(name = "data",nullable = false)
  private byte[] data;

  @CreationTimestamp
  @Column(name = "createdAt", updatable = false)
  private Timestamp createdAt;

  @Nullable
  @UpdateTimestamp
  @Column(name = "updatedAt")
  private Timestamp updatedAt;

  public AccountImage() {
  }

  public AccountImage(String fileName, String type,byte[] data) {
    this.fileName = fileName;
    this.type = type;
    this.data = data;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
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
