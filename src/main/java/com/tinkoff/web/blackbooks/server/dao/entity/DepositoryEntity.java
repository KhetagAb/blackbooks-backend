package com.tinkoff.web.blackbooks.server.dao.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "depository", schema = "public", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepositoryEntity implements com.tinkoff.web.blackbooks.server.dao.entity.Entity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private UUID id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "type")
    private String type;
    @Basic
    @Column(name = "latitude")
    private double latitude;
    @Basic
    @Column(name = "longitude")
    private double longitude;

    @OneToMany(mappedBy = "depository", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<BookEntity> books;

    @OneToMany(mappedBy = "depository", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private Set<BookEntity> transactions;

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
        DepositoryEntity that = (DepositoryEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
