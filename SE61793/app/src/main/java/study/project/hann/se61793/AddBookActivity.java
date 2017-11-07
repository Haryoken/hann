package study.project.hann.se61793;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddBookActivity extends AppCompatActivity {
    EditText txtBookId;
    EditText txtTitle;
    EditText txtPrice;
    DBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        txtBookId = (EditText)findViewById(R.id.txtBookId);
        txtTitle = (EditText)findViewById(R.id.txtTitle);
        txtPrice = (EditText)findViewById(R.id.txtPrice);
        helper = new DBHelper(this);

    }
    public void clickAddBook(View view){
        if(validateInput()){
            int bookId = Integer.parseInt(txtBookId.getText().toString());
            String title = txtTitle.getText().toString();
            int price = Integer.parseInt(txtPrice.getText().toString());

            Book book = new Book(bookId,title,price);
            if(helper.insertBook(book)){
                Toast.makeText(this,"Add Successfully.",Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(this,"Add Failed.",Toast.LENGTH_SHORT).show();
            }
            Intent toMain = new Intent(this, MainActivity.class);
            startActivity(toMain);
        }
    }
    private boolean validateInput(){
        boolean result = true;

        return result;

    }
}
