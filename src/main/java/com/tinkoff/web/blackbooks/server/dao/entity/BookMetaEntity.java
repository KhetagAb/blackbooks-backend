package com.tinkoff.web.blackbooks.server.dao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "book-meta", schema = "public", catalog = "postgres")
@Data
public class BookMetaEntity implements com.tinkoff.web.blackbooks.server.dao.entity.Entity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private UUID id;
    @Basic
    @Column(name = "shortName")
    private String shortName;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "author")
    private String author;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
