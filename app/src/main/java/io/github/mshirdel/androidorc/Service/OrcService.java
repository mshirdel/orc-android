package io.github.mshirdel.androidorc.Service;

import java.util.List;

import io.github.mshirdel.androidorc.Models.Group;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by meysam on 12/2/16.
 */
public interface OrcService {
    @GET("api/v1/cms/groups")
    Call<List<Group>> listGroup();
}
