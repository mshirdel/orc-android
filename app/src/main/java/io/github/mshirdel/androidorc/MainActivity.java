package io.github.mshirdel.androidorc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.github.mshirdel.androidorc.Adapters.GroupsAdapter;
import io.github.mshirdel.androidorc.Models.Group;
import io.github.mshirdel.androidorc.Service.OrcService;
import io.github.mshirdel.androidorc.utils.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private GroupsAdapter mAdapter;
    private RecyclerView mRecycleView;
    private OrcService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mService = ApiUtils.getOrcService();
        mRecycleView = (RecyclerView)findViewById(R.id.rvGroup);
        mAdapter = new GroupsAdapter(this, new ArrayList<Group>(0), new GroupsAdapter.PostItemListener() {

            @Override
            public void onPostClick(long id) {
                Toast.makeText(MainActivity.this, "Post id is" + id, Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(layoutManager);
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecycleView.addItemDecoration(itemDecoration);

        loadGroups();
    }

    private void loadGroups() {
        mService.listGroup().enqueue(new Callback<List<Group>>() {
            @Override
            public void onResponse(Call<List<Group>> call, Response<List<Group>> response) {
                if(response.isSuccessful()){
                    mAdapter.updateGroups(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Group>> call, Throwable t) {

            }
        });
    }
}
