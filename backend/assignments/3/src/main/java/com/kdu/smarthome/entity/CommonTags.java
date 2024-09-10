package com.kdu.smarthome.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class CommonTags {

    private Timestamp createdDate;

    private Timestamp modifiedDate;

    private Timestamp deletedDate;

    @Column(nullable = false)
    private boolean isDeleted;

    @PrePersist
    protected void onCreate() { createdDate = new Timestamp(System.currentTimeMillis()); }
    @PreUpdate
    protected void onUpdate() {
        modifiedDate = new Timestamp(System.currentTimeMillis());
    }

    @PreRemove
    protected void onDelete() {
        deletedDate = new Timestamp(System.currentTimeMillis());
    }
}
