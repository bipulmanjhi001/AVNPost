package avnpost.com.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import avnpost.com.R;
import avnpost.com.classmodel.StateCategoryModel;

public class StateCategoryAdapter extends BaseAdapter {
    private Context mContext;
    ArrayList<StateCategoryModel> mylist = new ArrayList<StateCategoryModel>();

    public StateCategoryAdapter(ArrayList<StateCategoryModel> itemArray, Context mContext) {
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
        private TextView id,name;
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
                convertView = inflator.inflate(R.layout.state_category_list, null);
                view.id = (TextView) convertView.findViewById(R.id.favourite_id);
                view.name = (TextView) convertView.findViewById(R.id.favourite_date);

                convertView.setTag(view);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else {
            view = (ViewHolder) convertView.getTag();
        }
        try {
            view.id.setTag(position);
            view.id.setText(mylist.get(position).getId());
            view.name.setText(mylist.get(position).getName());

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return convertView;
    }
}
