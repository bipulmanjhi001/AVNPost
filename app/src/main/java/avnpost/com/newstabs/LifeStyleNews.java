package avnpost.com.newstabs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import avnpost.com.R;
import avnpost.com.adapter.LifeStyleAdapter;
import avnpost.com.adapter.StateNewsAdapter;
import avnpost.com.api.URLs;
import avnpost.com.classmodel.LifeStyleModel;
import avnpost.com.classmodel.StateCategoryModel;
import avnpost.com.classmodel.StateNewsModel;
import avnpost.com.main.SinglePost;
import avnpost.com.model.VolleySingleton;

public class LifeStyleNews extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    private String image,date,title,author,authorname,id;
    LifeStyleAdapter lifeStyleAdapter;
    ListView lifestyle_news_list;
    ArrayList<LifeStyleModel> lifeStyleModels;
    ProgressBar lifestyle_pro;
    ImageView life_img;
    TextView life_text_title,life_date;
    String ids;
    ArrayList<StateCategoryModel> stateCategoryModels;
    StateCategoryAdapter stateCategoryAdapter;
    String ids2;
    TextView p_text;
    LinearLayout c_show;
    RecyclerView state_list;
    public LifeStyleNews() {
    }

    public static LifeStyleNews newInstance(String param1, String param2) {
        LifeStyleNews fragment = new LifeStyleNews();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.lifestyle_news, container, false);

        lifeStyleModels=new ArrayList<LifeStyleModel>();
        lifestyle_news_list=(ListView)view.findViewById(R.id.lifestyle_news_list);
        lifestyle_news_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView c = (TextView)view.findViewById(R.id.life_id);
                ids = c.getText().toString();
                Intent intent = new Intent(getActivity(), SinglePost.class);
                intent.putExtra("ids",ids);
                startActivity(intent);
            }
        });
        lifestyle_pro=(ProgressBar)view.findViewById(R.id.lifestyle_pro);

        life_text_title=(TextView)view.findViewById(R.id.life_text_title);
        life_date=(TextView)view.findViewById(R.id.life_date);
        state_list=(RecyclerView)view.findViewById(R.id.state_list);
        stateCategoryModels=new ArrayList<StateCategoryModel>();
        p_text = (TextView) view.findViewById(R.id.p_text);
        c_show=(LinearLayout)view.findViewById(R.id.c_show);
        CallTitle();
        return view;
    }
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public void CallTitle(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_CATEGORY+"/"+"13",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray obj = new JSONArray(response);
                            try {
                                stateCategoryModels.clear();
                            }catch (NullPointerException e){
                                e.printStackTrace();
                            }
                            for(int j=0; j<obj.length(); j++) {
                                JSONObject itemslist2 = obj.getJSONObject(j);
                                String id = itemslist2.getString("id");
                                String name=itemslist2.getString("name");

                                StateCategoryModel stateCategoryModel = new StateCategoryModel(id,name);
                                stateCategoryModels.add(stateCategoryModel);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            stateCategoryAdapter =  new StateCategoryAdapter(getActivity(), R.layout.state_category_list, stateCategoryModels);
                            state_list.setAdapter(stateCategoryAdapter);
                            stateCategoryAdapter.notifyDataSetChanged();
                        }catch (NullPointerException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                })
        {
        };
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
        CallList();
    }

    public void CallList(){
        lifestyle_pro.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_POSTS+"/"+"13",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray obj = new JSONArray(response);
                                try {
                                    lifeStyleModels.clear();
                                }catch (NullPointerException e){
                                    e.printStackTrace();
                                }
                                for(int j=0; j<obj.length(); j++) {
                                    JSONObject itemslist2 = obj.getJSONObject(j);
                                    String id = itemslist2.getString("id");
                                    String title = itemslist2.getString("title");
                                    String image = itemslist2.getString("image");
                                    String date = itemslist2.getString("date");
                                    String author = itemslist2.getString("author");
                                    String author_name = itemslist2.getString("author_name");

                                    LifeStyleModel lifeStyleModel = new LifeStyleModel(id, title, date, author, author_name, image);
                                    lifeStyleModels.add(lifeStyleModel);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            lifestyle_pro.setVisibility(View.GONE);
                            lifeStyleAdapter = new LifeStyleAdapter(lifeStyleModels, getActivity());
                            lifestyle_news_list.setAdapter(lifeStyleAdapter);
                            lifeStyleAdapter.notifyDataSetChanged();
                        }catch (NullPointerException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                })
        {
        };
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    public class StateCategoryAdapter extends RecyclerView.Adapter<StateCategoryHolder> {

        private final List<StateCategoryModel> stateCategoryModels;
        private Context context;
        private int itemResource;

        public StateCategoryAdapter(Context context, int itemResource, ArrayList<StateCategoryModel> stateCategoryModels) {
            this.stateCategoryModels = stateCategoryModels;
            this.context = context;
            this.itemResource = itemResource;
        }

        @Override
        public StateCategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(this.itemResource, parent, false);
            return new StateCategoryHolder(this.context, view);
        }

        @Override
        public void onBindViewHolder(StateCategoryHolder holder, int position) {

            StateCategoryModel stateCategoryModels = this.stateCategoryModels.get(position);
            holder.bindstateCategoryModels(stateCategoryModels);
        }

        @Override
        public int getItemCount() {

            return this.stateCategoryModels.size();
        }
    }

    public class StateCategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView bakeryName;
        private final TextView profile_product_id1;
        private StateCategoryModel bakery;
        private Context context;

        public StateCategoryHolder(Context context, View itemView) {

            super(itemView);
            this.context = context;
            this.bakeryName = (TextView) itemView.findViewById(R.id.c_text);
            this.profile_product_id1 = (TextView) itemView.findViewById(R.id.c_id);
            itemView.setOnClickListener(this);

        }

        public void bindstateCategoryModels(StateCategoryModel stateCategoryModel) {
            this.bakery = stateCategoryModel;
            this.bakeryName.setText(bakery.getName());
            this.profile_product_id1.setText(bakery.getId());
        }

        @SuppressLint("NewApi")
        @Override
        public void onClick(View v) {
            if (this.bakery != null) {
                ids2=this.bakery.getId();
                CallList2();
                c_show.setVisibility(View.VISIBLE);
                p_text.setText(this.bakery.getName());
                p_text.setTextColor(Color.parseColor("#FF0000"));
            }
            else {
                this.bakeryName.setTextColor(Color.parseColor("#000000"));
            }
        }
    }

    public void CallList2(){
        lifestyle_pro.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_POSTS+"/"+ids2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray obj = new JSONArray(response);
                            try {
                                lifeStyleModels.clear();
                            }catch (NullPointerException e){
                                e.printStackTrace();
                            }
                            for(int j=0; j<obj.length(); j++) {
                                JSONObject itemslist2 = obj.getJSONObject(j);
                                String id = itemslist2.getString("id");
                                String title = itemslist2.getString("title");
                                String image = itemslist2.getString("image");
                                String date = itemslist2.getString("date");
                                String author = itemslist2.getString("author");
                                String author_name = itemslist2.getString("author_name");

                                LifeStyleModel lifeStyleModel = new LifeStyleModel(id, title, date, author, author_name, image);
                                lifeStyleModels.add(lifeStyleModel);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            lifestyle_pro.setVisibility(View.GONE);
                            lifeStyleAdapter = new LifeStyleAdapter(lifeStyleModels, getActivity());
                            lifestyle_news_list.setAdapter(lifeStyleAdapter);
                            lifeStyleAdapter.notifyDataSetChanged();
                        }catch (NullPointerException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                })
        {
        };
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }

}
