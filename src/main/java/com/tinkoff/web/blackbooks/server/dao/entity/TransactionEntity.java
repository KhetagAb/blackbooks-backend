package com.tinkoff.web.blackbooks.server.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "transaction", schema = "public", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity implements com.tinkoff.web.blackbooks.server.dao.entity.Entity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserProfileEntity user;

    @ManyToOne
    @JoinColumn(name = "depository_id")
    private DepositoryEntity depository;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TransactionEntity that = (TransactionEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
