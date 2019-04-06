package com.file.sharing.core.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * created by: andrei
 * date: 16.02.2019
 **/
@Entity
@Table(name = "user_storage", schema = "public")
public class UserStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "size_used")
    private Long sizeUsed;

    private Integer files;

    private Integer folders;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Long getSizeUsed() {
        return sizeUsed;
    }

    public void setSizeUsed(Long sizeUsed) {
        this.sizeUsed = sizeUsed;
    }

    public Integer getFiles() {
        return files;
    }

    public void setFiles(Integer files) {
        this.files = files;
    }

    public Integer getFolders() {
        return folders;
    }

    public void setFolders(Integer folders) {
        this.folders = folders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStorage that = (UserStorage) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(sizeUsed, that.sizeUsed) &&
                Objects.equals(files, that.files) &&
                Objects.equals(folders, that.folders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sizeUsed, files, folders);
    }

    @Override
    public String toString() {
        return "UserStorage{" +
                "id=" + id +
                ", sizeUsed=" + sizeUsed +
                ", files=" + files +
                ", folders=" + folders +
                '}';
    }
}
