package study.project.hann.se61793;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 11/6/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    SQLiteDatabase database;
    private static final String DB_NAME = "HaNN.db";
    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Book (bookId integer PRIMARY KEY, title text, price integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Book");
        onCreate(db);
    }

    public boolean insertBook(Book book){
        database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("bookId",book.getBookId());
        values.put("title",book.getTitle());
        values.put("price",book.getPrice());
        if(database.insert("Book",null,values) > -1){
            return true;
        }
        return false;
    }

    public List<Book> getAllBook(){
        database = getReadableDatabase();
        List<Book> bookList = new ArrayList<>();
        String query = "SELECT * FROM Book";
        Cursor cursor = database.rawQuery(query,null);
        if(cursor != null){
            while(cursor.moveToNext()){
                int bookId = cursor.getInt(cursor.getColumnIndex("bookId"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                int price = cursor.getInt(cursor.getColumnIndex("price"));
                Book book = new Book(bookId,title,price);
                bookList.add(book);
            }
        }
        return  bookList;
    }
}
