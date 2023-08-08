package com.api.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
public class AuditableEntity {
    @Column (name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;

    @Column (name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Column (name = "created_by")
    @CreatedBy
    private String createdBy;

    @Column (name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;




}
