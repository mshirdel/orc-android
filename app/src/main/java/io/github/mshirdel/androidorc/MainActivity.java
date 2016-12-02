package io.github.mshirdel.androidorc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import io.github.mshirdel.androidorc.Models.Group;
import io.github.mshirdel.androidorc.Service.OrcService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGet = (Button)findViewById(R.id.btnGet);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getGroups();
            }
        });
    }

    private void getGroups() {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            OrcService service = retrofit.create(OrcService.class);
            Call<List<Group>> groups = service.listGroup();

            groups.enqueue(new Callback<List<Group>>() {
                @Override
                public void onResponse(Call<List<Group>> call, Response<List<Group>> response) {
                    String groupNames = "";
                    for (Group group : response.body()) {
                        groupNames += group.name + "\n";
                    }
                    TextView textView1 = (TextView) findViewById(R.id.textView1);
                    textView1.setText(groupNames);
                }

                @Override
                public void onFailure(Call<List<Group>> call, Throwable t) {
                    //Handle failure
                }
            });
        } catch (Exception ex) {
            TextView textView1 = (TextView) findViewById(R.id.textView1);
            textView1.setText(ex.getMessage());
        }
    }
}
