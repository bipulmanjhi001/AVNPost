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
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import avnpost.com.R;
import avnpost.com.classmodel.StateNewsModel;

public class StateNewsAdapter extends BaseAdapter {
    private Context mContext;
    ArrayList<StateNewsModel> mylist = new ArrayList<StateNewsModel>();

    public StateNewsAdapter(ArrayList<StateNewsModel> itemArray, Context mContext) {
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

                convertView = inflator.inflate(R.layout.statenews_list, null);
                view.id = (TextView) convertView.findViewById(R.id.state_id);
                view.date = (TextView) convertView.findViewById(R.id.state_date);
                view.title = (TextView) convertView.findViewById(R.id.state_title);
                view.author = (TextView) convertView.findViewById(R.id.state_author);
                view.author_name = (TextView) convertView.findViewById(R.id.state_author_name);
                view.image = (ImageView) convertView.findViewById(R.id.state_image);

                convertView.setTag(view);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else {
            view = (ViewHolder) convertView.getTag();
        }
        try {
            view.id.setTag(position);
            view.id.setText(mylist.get(position).getState_id());
            view.date.setText(mylist.get(position).getState_date());
            view.title.setText(mylist.get(position).getState_title());
            view.author.setText(mylist.get(position).getState_author());
            view.author_name.setText(mylist.get(position).getState_author_name());

            try {
                Glide.with(mContext).load(mylist.get(position).getState_image())
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
