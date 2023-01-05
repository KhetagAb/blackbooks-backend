package com.tinkoff.web.blackbooks.server.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "book_meta", schema = "public", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookMetaEntity implements com.tinkoff.web.blackbooks.server.dao.entity.Entity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private UUID id;
    @Basic
    @Column(name = "short_name")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BookMetaEntity that = (BookMetaEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
