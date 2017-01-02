package io.github.mshirdel.androidorc.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.ArrayList;
import java.util.List;

import io.github.mshirdel.androidorc.Models.DTO.GroupDTO;

@Table(name = "Groups")
public class Group extends Model {

    @Column(name= "group_id")
    private long groupId;

    @Column(name= "name")
    private String name;

    @Column(name= "created_at")
    private String createdAt;

    @Column(name="updated_at")
    private String updatedAt;

    public Group(){
        super();
    }

    public long getGroupId(){
        return groupId;
    }

    public void setGroupId(long gId){
        groupId = gId;
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

    public static List<GroupDTO> ConvertToDto(List<Group> list){
        List<GroupDTO> result = new ArrayList<>();
        for(Group g : list){
            result.add(new GroupDTO(g.name, g.groupId));
        }
        return result;
    }
}