package io.github.mshirdel.androidorc.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Table(name = "Lessons")
public class Lesson extends Model {

    @SerializedName("id")
    @Expose
    @Column(name= "id", unique = true, onUniqueConflict = Column.ConflictAction.IGNORE)
    private Integer id;

    @SerializedName("title")
    @Expose
    @Column(name="title")
    private String title;

    @SerializedName("body")
    @Expose
    @Column(name="body")
    private String body;

    @SerializedName("group_id")
    @Expose
    @Column(name="group_id")
    private Integer groupId;

    @SerializedName("created_at")
    @Expose
    @Column(name="created_at")
    private String createdAt;

    @SerializedName("updated_at")
    @Expose
    @Column(name="updated_at")
    private String updatedAt;

    @Column(name="Group")
    private Group group;

    public Lesson(){
        super();
    }

    public Group getGroup(){
        return group;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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

