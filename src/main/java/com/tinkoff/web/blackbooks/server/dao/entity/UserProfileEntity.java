package com.tinkoff.web.blackbooks.server.dao.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user_profile", schema = "public", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileEntity implements com.tinkoff.web.blackbooks.server.dao.entity.Entity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private UUID id;
    @Basic
    @Column(name = "nick")
    private String nick;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "age")
    private int age;
    @Basic
    @Column(name = "gender")
    private String gender;
    @Basic
    @Column(name = "latitude")
    private double latitude;
    @Basic
    @Column(name = "longitude")
    private double longitude;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<BookEntity> books;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<TransactionEntity> transactions;

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
        UserProfileEntity that = (UserProfileEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
