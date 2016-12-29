package io.github.mshirdel.androidorc.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.mshirdel.androidorc.Adapters.LessonsAdapter;
import io.github.mshirdel.androidorc.Models.Lesson;
import io.github.mshirdel.androidorc.R;
import io.github.mshirdel.androidorc.Service.OrcService;
import io.github.mshirdel.androidorc.utils.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LessonListActivity extends AppCompatActivity {

    private LessonsAdapter mAdapter;
    private RecyclerView mRecycleView;
    private OrcService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_list);
        Intent intent = getIntent();
        Integer groupId = intent.getIntExtra("groupId",999);

        mService = ApiUtils.getOrcService();
        mRecycleView = (RecyclerView)findViewById(R.id.rvLesson);

        mAdapter = new LessonsAdapter(this, new ArrayList<Lesson>(0), new LessonsAdapter.PostItemListener() {

            @Override
            public void onPostClick(Integer id) {
                Toast.makeText(LessonListActivity.this, "Lesson id is" + id, Toast.LENGTH_SHORT).show();

            }
        });
        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(layoutManager);
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecycleView.addItemDecoration(itemDecoration);
        loadLessons(groupId);
    }

    private void loadLessons(Integer groupId) {
        mService.getGroupLessons(groupId).enqueue(new Callback<List<Lesson>>() {
            @Override
            public void onResponse(Call<List<Lesson>> call, Response<List<Lesson>> response) {
                if (response.isSuccessful()) {
                    mAdapter.updateLessons(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Lesson>> call, Throwable t) {

            }
        });
    }
}
