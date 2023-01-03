package com.tinkoff.web.blackbooks.server.dao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Book", schema = "public", catalog = "postgres")
@Data
public class BookEntity implements com.tinkoff.web.blackbooks.server.dao.entity.Entity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private UUID id;
    @Basic
    @Column(name = "bookMetaId")
    private Object bookMetaId;
    @Basic
    @Column(name = "userId")
    private Object userId;
    @Basic
    @Column(name = "depositoryId")
    private Object depositoryId;
    @Basic
    @Column(name = "transactionId")
    private Object transactionId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
