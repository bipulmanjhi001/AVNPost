package avnpost.com.newstabs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import avnpost.com.R;
import avnpost.com.adapter.StateNewsAdapter;
import avnpost.com.api.URLs;
import avnpost.com.classmodel.StateCategoryModel;
import avnpost.com.classmodel.StateNewsModel;
import avnpost.com.classmodel.StateSubCategoryModel;
import avnpost.com.main.SinglePost;
import avnpost.com.model.VolleySingleton;

public class StateNews extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    StateNewsAdapter bussinessNewsAdapter;
    ListView bussiness_news_list;
    RecyclerView state_list,state_sub_list;
    ArrayList<StateNewsModel> bussinessNewsModels;
    ProgressBar progressBar;
    TextView business_text_title,business_date;
    String ids;
    ArrayList<StateCategoryModel> stateCategoryModels;
    ArrayList<StateSubCategoryModel> statesubCategoryModels;
    StateCategoryAdapter stateCategoryAdapter;
    String ids2,ids3;
    TextView p_text;
    LinearLayout c_show;
    CardView state_sublist;
    Dialog dialog;
    ListView listView;
    private ArrayList state_names = new ArrayList();
    private ArrayList state_ids = new ArrayList();

    public StateNews() {
    }

    public static StateNews newInstance(String param1, String param2) {
        StateNews fragment = new StateNews();
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
        View view= inflater.inflate(R.layout.state_news, container, false);

        bussinessNewsModels=new ArrayList<StateNewsModel>();
        bussiness_news_list=(ListView)view.findViewById(R.id.state_news_list);
        bussiness_news_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView c = (TextView)view.findViewById(R.id.state_id);
                ids = c.getText().toString();
                Intent intent = new Intent(getActivity(), SinglePost.class);
                intent.putExtra("ids",ids);
                startActivity(intent);
            }
        });

        progressBar=(ProgressBar)view.findViewById(R.id.state_pro);
        business_text_title=(TextView)view.findViewById(R.id.state_text_title);
        business_date=(TextView)view.findViewById(R.id.state_date);
        state_list=(RecyclerView)view.findViewById(R.id.state_list);
        stateCategoryModels=new ArrayList<StateCategoryModel>();
        statesubCategoryModels=new ArrayList<StateSubCategoryModel>();
        p_text = (TextView) view.findViewById(R.id.p_text);
        c_show=(LinearLayout)view.findViewById(R.id.c_show);
        state_sub_list=(RecyclerView)view.findViewById(R.id.state_sub_list);
        state_sublist=(CardView) view.findViewById(R.id.state_sublist);
        CallTitle();
        dialog=new Dialog(getActivity());

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
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_CATEGORY+"/"+"3",
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
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_POSTS+"/"+"3",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray obj = new JSONArray(response);
                            try {
                                bussinessNewsModels.clear();
                            }catch (NullPointerException e){
                                e.printStackTrace();
                            }
                            for(int j=0; j<obj.length(); j++) {
                                JSONObject itemslist2 = obj.getJSONObject(j);
                                String id = itemslist2.getString("id");
                                String title = itemslist2.getString("title");
                                String image = itemslist2.getString("image");
                                String date = itemslist2.getString("date");
                                String author=itemslist2.getString("author");
                                String author_name=itemslist2.getString("author_name");

                                StateNewsModel bussinessNewsModel = new StateNewsModel(id,title,date,author,author_name,image);
                                bussinessNewsModels.add(bussinessNewsModel);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            progressBar.setVisibility(View.GONE);
                            bussinessNewsAdapter = new StateNewsAdapter(bussinessNewsModels, getActivity());
                            bussiness_news_list.setAdapter(bussinessNewsAdapter);
                            bussinessNewsAdapter.notifyDataSetChanged();
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
        CallTitle2();
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
                if(ids2.equals("4")){
                    showState();
                }else {
                    CallList2();
                    c_show.setVisibility(View.VISIBLE);
                    p_text.setText(this.bakery.getName());
                    p_text.setTextColor(Color.parseColor("#FF0000"));
                }
            }
            else {
                this.bakeryName.setTextColor(Color.parseColor("#000000"));
            }
        }
    }

    public void CallList2(){
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_POSTS+"/"+ids2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray obj = new JSONArray(response);
                            try {
                                bussinessNewsModels.clear();
                            }catch (NullPointerException e){
                                e.printStackTrace();
                            }
                            for(int j=0; j<obj.length(); j++) {
                                JSONObject itemslist2 = obj.getJSONObject(j);
                                String id = itemslist2.getString("id");
                                String title = itemslist2.getString("title");
                                String image = itemslist2.getString("image");
                                String date = itemslist2.getString("date");
                                String author=itemslist2.getString("author");
                                String author_name=itemslist2.getString("author_name");

                                StateNewsModel bussinessNewsModel = new StateNewsModel(id,title,date,author,author_name,image);
                                bussinessNewsModels.add(bussinessNewsModel);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            progressBar.setVisibility(View.GONE);
                            bussinessNewsAdapter = new StateNewsAdapter(bussinessNewsModels, getActivity());
                            bussiness_news_list.setAdapter(bussinessNewsAdapter);
                            bussinessNewsAdapter.notifyDataSetChanged();
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

    public void CallTitle2(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_CATEGORY+"/"+"4",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray obj = new JSONArray(response);
                            try {
                                state_names.clear();
                                state_ids.clear();
                            }catch (NullPointerException e){
                                e.printStackTrace();
                            }
                            for(int j=0; j<obj.length(); j++) {
                                JSONObject itemslist2 = obj.getJSONObject(j);
                                String id = itemslist2.getString("id");
                                String name=itemslist2.getString("name");

                                state_names.add(name);
                                state_ids.add(id);

                            }
                        } catch (JSONException e) {
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
    private void showState() {
        dialog.setContentView(R.layout.state_list_dialog);
        listView= (ListView) dialog.findViewById(R.id.state_list);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, state_names){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.WHITE);

                return view;
            }
        };

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ids3=state_ids.get(position).toString();
                CallList3();
                c_show.setVisibility(View.VISIBLE);
                p_text.setText(state_names.get(position).toString());
                p_text.setTextColor(Color.parseColor("#FF0000"));
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void CallList3(){
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_POSTS+"/"+ids3,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray obj = new JSONArray(response);
                            try {
                                bussinessNewsModels.clear();
                            }catch (NullPointerException e){
                                e.printStackTrace();
                            }
                            for(int j=0; j<obj.length(); j++) {
                                JSONObject itemslist2 = obj.getJSONObject(j);
                                String id = itemslist2.getString("id");
                                String title = itemslist2.getString("title");
                                String image = itemslist2.getString("image");
                                String date = itemslist2.getString("date");
                                String author=itemslist2.getString("author");
                                String author_name=itemslist2.getString("author_name");

                                StateNewsModel bussinessNewsModel = new StateNewsModel(id,title,date,author,author_name,image);
                                bussinessNewsModels.add(bussinessNewsModel);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            progressBar.setVisibility(View.GONE);
                            bussinessNewsAdapter = new StateNewsAdapter(bussinessNewsModels, getActivity());
                            bussiness_news_list.setAdapter(bussinessNewsAdapter);
                            bussinessNewsAdapter.notifyDataSetChanged();
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
