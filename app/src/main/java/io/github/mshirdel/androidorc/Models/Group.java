package io.github.mshirdel.androidorc.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Groups")
public class Group extends Model {

    @Column(name= "id", unique = true, onUniqueConflict = Column.ConflictAction.IGNORE)
    private Integer id;

    @Column(name= "name")
    private String name;

    @Column(name= "created_at")
    private String createdAt;

    @Column(name="updated_at")
    private String updatedAt;

    public Group(){
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}