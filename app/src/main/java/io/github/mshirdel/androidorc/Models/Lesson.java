package io.github.mshirdel.androidorc.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.ArrayList;
import java.util.List;

import io.github.mshirdel.androidorc.Models.DTO.LessonDTO;

@Table(name = "Lessons")
public class Lesson extends Model {

    @Column(name="lesson_id")
    private int lessonId;

    @Column(name="title")
    private String title;

    @Column(name="body")
    private String body;

    @Column(name="group_id")
    private int groupId;

    @Column(name="created_at")
    private String createdAt;

    @Column(name="updated_at")
    private String updatedAt;

    public Lesson(){
        super();
    }

    public void setLessonId(int lId){
        lessonId = lId;
    }

    public int getLessonId(){
        return lessonId;
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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
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

    public static List<LessonDTO> ConvertToDto(List<Lesson> list) {
        List<LessonDTO> result = new ArrayList<>();
        for(Lesson l : list){
            LessonDTO lessonDTO = new LessonDTO();
            lessonDTO.setId(l.getLessonId());
            lessonDTO.setTitle(l.getTitle());
            lessonDTO.setBody(l.getBody());
            lessonDTO.setGroupId(l.getGroupId());
            lessonDTO.setUpdatedAt(l.getUpdatedAt());
            lessonDTO.setCreatedAt(l.getCreatedAt());
            result.add(lessonDTO);
        }
        return result;
    }
}

