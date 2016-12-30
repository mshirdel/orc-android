package io.github.mshirdel.androidorc.Service;

import java.util.List;

import io.github.mshirdel.androidorc.Models.DTO.GroupDTO;
import io.github.mshirdel.androidorc.Models.DTO.LessonDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OrcService {
    @GET("groups")
    Call<List<GroupDTO>> listGroup();
    @GET("lessons/{id}")
    Call<List<LessonDTO>> getGroupLessons(@Path("id") Integer groupId);
}
