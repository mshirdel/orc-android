package io.github.mshirdel.androidorc.Service;

import java.util.List;

import io.github.mshirdel.androidorc.Models.Group;
import io.github.mshirdel.androidorc.Models.Lesson;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OrcService {
    @GET("groups")
    Call<List<Group>> listGroup();
    @GET("lessons/{id}")
    Call<List<Lesson>> getGroupLessons(@Path("id") Integer groupId);
}
