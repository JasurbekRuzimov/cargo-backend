package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "attach")

@Getter
@Setter
public class AttachEntity {

  @Id
  @GenericGenerator(name = "attach_uuid", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  @Column(name = "original_name")
  private String originalName;

  @Column
  private String path;

  @Column
  private Long size;

  @Column
  private String extension;

  @Column(name = "created_date")
  private LocalDateTime createdDate;
}