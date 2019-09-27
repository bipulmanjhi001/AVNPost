package avnpost.com.newstabs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

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

import avnpost.com.R;
import avnpost.com.adapter.BussinessNewsAdapter;
import avnpost.com.adapter.InternationalNewsAdapter;
import avnpost.com.api.URLs;
import avnpost.com.classmodel.BussinessNewsModel;
import avnpost.com.classmodel.InternationalNewsModel;
import avnpost.com.main.SinglePost;
import avnpost.com.model.VolleySingleton;

public class InternationalNews extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    String image,date,title,author,authorname,id;
    InternationalNewsAdapter bussinessNewsAdapter;
    ListView bussiness_news_list;
    ArrayList<InternationalNewsModel> bussinessNewsModels;
    ProgressBar progressBar;
    ImageView business_img;
    TextView business_text_title,business_date;
    String ids;

    public InternationalNews() {
    }

    public static InternationalNews newInstance(String param1, String param2) {
        InternationalNews fragment = new InternationalNews();
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
        View view= inflater.inflate(R.layout.international_news, container, false);

        bussinessNewsModels=new ArrayList<InternationalNewsModel>();
        bussiness_news_list=(ListView)view.findViewById(R.id.international_news_list);
        bussiness_news_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView c = (TextView)view.findViewById(R.id.international_id);
                ids = c.getText().toString();
                Intent intent = new Intent(getActivity(), SinglePost.class);
                intent.putExtra("ids",ids);
                startActivity(intent);
            }
        });
        progressBar=(ProgressBar)view.findViewById(R.id.international_pro);
        business_img=(ImageView)view.findViewById(R.id.international_img);
        business_text_title=(TextView)view.findViewById(R.id.international_text_title);
        business_date=(TextView)view.findViewById(R.id.international_date);

        CallList();
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

    public void CallList(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_POSTS+"/"+"8",
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

                                InternationalNewsModel bussinessNewsModel = new InternationalNewsModel(id,title,date,author,author_name,image);
                                bussinessNewsModels.add(bussinessNewsModel);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            progressBar.setVisibility(View.GONE);
                            bussinessNewsAdapter = new InternationalNewsAdapter(bussinessNewsModels, getActivity());
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
