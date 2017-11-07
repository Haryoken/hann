package study.project.hann.se61793;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void clickAddBook(View view){
        Intent toAddBookIntent = new Intent(this, AddBookActivity.class);
        startActivity(toAddBookIntent);
    }
    public void clickShowBook(View view){
        Intent toShowBookIntent = new Intent(this, ShowBookActivity.class);
        startActivity(toShowBookIntent);
    }
}
