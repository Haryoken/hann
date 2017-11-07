package study.project.hann.se61793;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by admin on 11/6/2017.
 */

public class BookItemAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    List<Book> bookList;
    public BookItemAdapter(Context context,List<Book> bookList){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.bookList = bookList;
    }
    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return bookList.get(position).getBookId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.item_book_list,null);
            viewHolder.txtBookId = (TextView)convertView.findViewById(R.id.txtBookId);
            viewHolder.txtTitle = (TextView)convertView.findViewById(R.id.txtTitle);
            viewHolder.txtPrice = (TextView)convertView.findViewById(R.id.txtPrice);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
            final Book book = bookList.get(position);

            int bookID = book.getBookId();
            viewHolder.txtBookId.setText(bookID);
            viewHolder.txtTitle.setText("");
            viewHolder.txtPrice.setText("");

        return convertView;
    }
    static class ViewHolder{
        TextView txtBookId;
        TextView txtTitle;
        TextView txtPrice;
    }
}
