package avnpost.com.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.ArrayList;
import avnpost.com.R;
import avnpost.com.classmodel.BussinessNewsModel;
import avnpost.com.classmodel.FavouriteNewsModel;

public class FavouriteNewsAdapter extends BaseAdapter {
    private Context mContext;
    ArrayList<FavouriteNewsModel> mylist = new ArrayList<FavouriteNewsModel>();

    public FavouriteNewsAdapter(ArrayList<FavouriteNewsModel> itemArray, Context mContext) {
        super();
        this.mContext = mContext;
        mylist = itemArray;
    }

    @Override
    public int getCount() {
        return mylist.size();
    }

    @Override
    public String getItem(int position) {
        return mylist.get(position).toString();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        private TextView id, title, date, author, author_name;
        private ImageView image;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final ViewHolder view;
        LayoutInflater inflator = null;
        if (convertView == null) {
            view = new ViewHolder();
            try {
                inflator = ((Activity) mContext).getLayoutInflater();
                convertView = inflator.inflate(R.layout.favourite_list, null);
                view.id = (TextView) convertView.findViewById(R.id.favourite_id);
                view.date = (TextView) convertView.findViewById(R.id.favourite_date);
                view.title = (TextView) convertView.findViewById(R.id.favourite_title);
                view.author = (TextView) convertView.findViewById(R.id.favourite_author);
                view.author_name = (TextView) convertView.findViewById(R.id.favourite_author_name);
                view.image = (ImageView) convertView.findViewById(R.id.favourite_image);

                convertView.setTag(view);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else {
            view = (ViewHolder) convertView.getTag();
        }
        try {
            view.id.setTag(position);
            view.id.setText(mylist.get(position).getFavourite_id());
            view.date.setText(mylist.get(position).getFavourite_date());
            view.title.setText(mylist.get(position).getFavourite_title());
            view.author.setText(mylist.get(position).getFavourite_author());
            view.author_name.setText(mylist.get(position).getFavourite_author_name());

            try {
                Glide.with(mContext).load(mylist.get(position).getFavourite_image())
                        .error(R.drawable.no_image_available)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(view.image);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return convertView;
    }
}
