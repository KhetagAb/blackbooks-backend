package com.tinkoff.web.blackbooks.server.dao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "book", schema = "public", catalog = "postgres")
@Data
public class BookEntity implements com.tinkoff.web.blackbooks.server.dao.entity.Entity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private UUID id;
    @Basic
    @Column(name = "bookMetaId")
    private UUID bookMetaId;
    @Basic
    @Column(name = "userId")
    private UUID userId;
    @Basic
    @Column(name = "depositoryId")
    private UUID depositoryId;
    @Basic
    @Column(name = "transactionId")
    private UUID transactionId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
