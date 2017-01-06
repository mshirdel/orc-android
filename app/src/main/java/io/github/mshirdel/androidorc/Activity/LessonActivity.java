package io.github.mshirdel.androidorc.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.activeandroid.query.Select;

import org.w3c.dom.Text;

import io.github.mshirdel.androidorc.Models.Lesson;
import io.github.mshirdel.androidorc.R;

public class LessonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        TextView tvBody = (TextView) findViewById(R.id.tvLessonBody);
        TextView tvTitle = (TextView) findViewById(R.id.tvLessonTitle);
        Intent intent = getIntent();
        Integer lessonId = intent.getIntExtra("lessonId",-1);
        Lesson lessonInDb = new Select().from(Lesson.class).where("lesson_id = ?", lessonId).executeSingle();
        if(lessonInDb != null){
            tvTitle.setText(lessonInDb.getTitle());
            tvBody.setText(lessonInDb.getBody());
        }
    }
}
