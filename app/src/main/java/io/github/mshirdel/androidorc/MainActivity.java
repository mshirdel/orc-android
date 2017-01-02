package io.github.mshirdel.androidorc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import io.github.mshirdel.androidorc.Activity.LessonListActivity;
import io.github.mshirdel.androidorc.Adapters.GroupsAdapter;
import io.github.mshirdel.androidorc.Models.DTO.GroupDTO;
import io.github.mshirdel.androidorc.Models.Group;
import io.github.mshirdel.androidorc.Service.OrcService;
import io.github.mshirdel.androidorc.utils.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        mAdapter = new GroupsAdapter(this, new ArrayList<GroupDTO>(0), new GroupsAdapter.PostItemListener() {

            @Override
            public void onPostClick(long id) {
                //Toast.makeText(MainActivity.this, "Post id is" + id, Toast.LENGTH_SHORT).show();
                loadLessons(id);
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

    private void loadLessons(long id){
        Intent intent = new Intent(this, LessonListActivity.class);
        intent.putExtra("groupId", (int)id);
        startActivity(intent);
    }

    private void loadGroups() {
        mService.listGroup().enqueue(new Callback<List<GroupDTO>>() {
            @Override
            public void onResponse(Call<List<GroupDTO>> call, Response<List<GroupDTO>> response) {
                if(response.isSuccessful()){
                    List<GroupDTO> groupsInServer = response.body();
                    try{
                        for(GroupDTO group : groupsInServer) {
                            TextView log = (TextView)findViewById(R.id.tvLog);
                            Group groupInDb = new Select().from(Group.class).where("group_id = ?", group.getId()).executeSingle();
                            if(groupInDb != null){
                                log.setText("found in db");
                            }else{
                                Group newGroup = new Group();
                                newGroup.setName(group.getName());
                                newGroup.setGroupId((group.getId()));
                                newGroup.save();
                            }

                        }
                    }
                    catch (Exception ex){
                        TextView log = (TextView)findViewById(R.id.tvLog);
                        log.setText(ex.getMessage());
                    }
                    mAdapter.updateGroups(groupsInServer);
                }
            }

            @Override
            public void onFailure(Call<List<GroupDTO>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(),Toast.LENGTH_SHORT ).show();
                TextView log = (TextView)findViewById(R.id.tvLog);
                log.setText(t.getMessage());

                List<Group> allGroups = new Select().from(Group.class).execute();
                mAdapter.updateGroups(Group.ConvertToDto(allGroups));
            }
        });
    }
}
