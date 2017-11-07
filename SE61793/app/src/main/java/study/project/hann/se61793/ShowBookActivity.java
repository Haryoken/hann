package study.project.hann.se61793;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class ShowBookActivity extends AppCompatActivity {
    DBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_book);
        helper = new DBHelper(this);
        List<Book> bookList = helper.getAllBook();


        ListView lvBookList = (ListView)findViewById(R.id.lvBookList);
        lvBookList.setAdapter(new BookItemAdapter(this, bookList));


    }
}
