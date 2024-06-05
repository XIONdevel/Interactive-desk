package com.xidesk.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "desk")
public class DeskEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long user_id;
    private Date createdAt = new Date(System.currentTimeMillis());
    private Date updatedAt;

    private void Update() {
        this.updatedAt = new Date(System.currentTimeMillis());
    }



}








