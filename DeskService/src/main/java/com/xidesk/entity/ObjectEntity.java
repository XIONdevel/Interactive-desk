package com.xidesk.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

/*
yeah, ik that`s worst name possible, so... change if u wanna use it :D
*/

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "object")
public class ObjectEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "desk_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private DeskEntity deskId;
    private String name;
    private int position_x;
    private int position_y;
    private int width;
    private int height;
    @Column(name = "additional")
    private String additionalJson;
    private Date createdAt;
    private Date updatedAt;

    private void Update() {
        this.updatedAt = new Date(System.currentTimeMillis());
    }


}



