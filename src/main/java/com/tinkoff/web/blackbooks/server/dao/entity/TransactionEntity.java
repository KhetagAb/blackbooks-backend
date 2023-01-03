package com.tinkoff.web.blackbooks.server.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "transaction", schema = "public", catalog = "postgres")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity implements com.tinkoff.web.blackbooks.server.dao.entity.Entity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private UUID id;
    @Basic
    @Column(name = "userId")
    private UUID userId;
    @Basic
    @Column(name = "depositoryId")
    private UUID depositoryId;
    @Basic
    @Column(name = "bookId")
    private UUID bookId;
    @Basic
    @Column(name = "timestamp")
    private Timestamp timestamp;
    @Basic
    @Column(name = "type")
    private String type;
    @Basic
    @Column(name = "status")
    private String status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
