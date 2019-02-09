package com.file.sharing.core.entity;

import com.file.sharing.common.user.UserAccountType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author Andrei Aioanei
 * @created 02.02.2019
 */

@Entity
@Table(name = "subscription", schema="public")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type")
    @NotNull
    @Enumerated(EnumType.STRING)
    private UserAccountType type;

    @Column(name = "storage_size")
    @NotNull
    private Long storageSize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserAccountType getType() {
        return type;
    }

    public void setType(UserAccountType type) {
        this.type = type;
    }

    public Long getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(Long storageSize) {
        this.storageSize = storageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return Objects.equals(id, that.id) &&
                type == that.type &&
                Objects.equals(storageSize, that.storageSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, storageSize);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", type=" + type +
                ", storageSize=" + storageSize +
                '}';
    }
}
