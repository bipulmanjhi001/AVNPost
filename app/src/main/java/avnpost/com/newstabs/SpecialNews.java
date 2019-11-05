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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import avnpost.com.R;
import avnpost.com.adapter.BussinessNewsAdapter;
import avnpost.com.adapter.SpecialNewsAdapter;
import avnpost.com.api.URLs;
import avnpost.com.classmodel.BussinessNewsModel;
import avnpost.com.classmodel.SpecialNewsModel;
import avnpost.com.main.SinglePost;
import avnpost.com.model.DownPageAdapter;
import avnpost.com.model.SliderUtils2;
import avnpost.com.model.VolleySingleton;

public class SpecialNews extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    SpecialNewsAdapter specialNewsAdapter;
    ListView special_news_list;
    ArrayList<SpecialNewsModel> specialNewsModels;
    ProgressBar progressBar;
    String ids,image;
    ViewPager viewPager;
    private List<SliderUtils2> sliderUtils2s;
    private SliderUtils2 sliderUtils2;
    private DownPageAdapter downPageAdapter;
    LinearLayout sliderDotspanel;
    private ImageView[] dots;
    private int dotscount;

    public SpecialNews() {
    }

    public static SpecialNews newInstance(String param1, String param2) {
        SpecialNews fragment = new SpecialNews();
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
        View view= inflater.inflate(R.layout.special_news, container, false);
        sliderUtils2s=new ArrayList<>();

        viewPager=(ViewPager)view.findViewById(R.id.viewPager);
        sliderDotspanel = (LinearLayout)view.findViewById(R.id.layoutDots);

        specialNewsModels=new ArrayList<SpecialNewsModel>();
        special_news_list=(ListView)view.findViewById(R.id.special_news_list);
        special_news_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView c = (TextView)view.findViewById(R.id.special_id);
                ids = c.getText().toString();
                Intent intent = new Intent(getActivity(), SinglePost.class);
                intent.putExtra("ids",ids);
                startActivity(intent);

            }
        });
        progressBar=(ProgressBar)view.findViewById(R.id.special_pro);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                try {
                    for (int i = 0; i < dotscount; i++) {
                        dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.non_active_dot));
                    }
                    dots[position].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.active_dot));

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Sliders();
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
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_HOME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);

                            JSONArray latest=obj.getJSONArray("latest");
                            for(int i=0; i<latest.length(); i++) {

                                JSONObject itemslist2 = latest.getJSONObject(i);
                                String id = itemslist2.getString("id");
                                String title = itemslist2.getString("title");
                                String image = itemslist2.getString("image");
                                String date = itemslist2.getString("date");
                                String author_name = itemslist2.getString("author_name");

                                SpecialNewsModel specialNewsModel = new SpecialNewsModel(id,title,date,"",author_name,image);
                                specialNewsModels.add(specialNewsModel);

                            }
                            JSONArray national=obj.getJSONArray("national");
                            for(int j=0; j<national.length(); j++) {

                                JSONObject itemslist2 = national.getJSONObject(j);
                                String id = itemslist2.getString("id");
                                String title = itemslist2.getString("title");
                                String image = itemslist2.getString("image");
                                String date = itemslist2.getString("date");
                                String author_name = itemslist2.getString("author_name");

                                SpecialNewsModel specialNewsModel = new SpecialNewsModel(id,title,date,"",author_name,image);
                                specialNewsModels.add(specialNewsModel);

                            }

                            JSONArray international=obj.getJSONArray("international");
                            for(int k=0; k<international.length(); k++) {

                                JSONObject itemslist2 = international.getJSONObject(k);
                                String id = itemslist2.getString("id");
                                String title = itemslist2.getString("title");
                                String image = itemslist2.getString("image");
                                String date = itemslist2.getString("date");
                                String author_name = itemslist2.getString("author_name");

                                SpecialNewsModel specialNewsModel = new SpecialNewsModel(id,title,date,"",author_name,image);
                                specialNewsModels.add(specialNewsModel);

                            }

                            JSONArray technology=obj.getJSONArray("technology");
                            for(int l=0; l<technology.length(); l++) {

                                JSONObject itemslist2 = technology.getJSONObject(l);
                                String id = itemslist2.getString("id");
                                String title = itemslist2.getString("title");
                                String image = itemslist2.getString("image");
                                String date = itemslist2.getString("date");
                                String author_name = itemslist2.getString("author_name");

                                SpecialNewsModel specialNewsModel = new SpecialNewsModel(id,title,date,"",author_name,image);
                                specialNewsModels.add(specialNewsModel);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            progressBar.setVisibility(View.GONE);
                            specialNewsAdapter = new SpecialNewsAdapter(specialNewsModels, getActivity());
                            special_news_list.setAdapter(specialNewsAdapter);
                            specialNewsAdapter.notifyDataSetChanged();
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

    private void Sliders(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_HOME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                                JSONArray toparr=obj.getJSONArray("banner");

                                for(int j = 0; j < toparr.length(); j++){
                                    try {

                                        sliderUtils2 = new SliderUtils2();
                                        JSONObject getslide = toparr.getJSONObject(j);
                                        sliderUtils2.setSliderImageUrl(getslide.getString("image"));
                                        sliderUtils2.setId(getslide.getString("id"));
                                        sliderUtils2.setName(getslide.getString("title"));
                                        sliderUtils2s.add(sliderUtils2);
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        downPageAdapter = new DownPageAdapter(sliderUtils2s, getActivity());
                        viewPager.setAdapter(downPageAdapter);
                        dotscount = downPageAdapter.getCount();
                        dots = new ImageView[dotscount];

                        for(int i = 0; i < dotscount; i++){
                            try {
                                dots[i] = new ImageView(getActivity());
                                dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.non_active_dot));
                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                params.setMargins(8, 0, 8, 0);
                                sliderDotspanel.addView(dots[i], params);

                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        try {
                            dots[0].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.active_dot));
                        }catch (NullPointerException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Refresh again..", Toast.LENGTH_SHORT).show();
                    }
                })
        {
        };
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
        CallList();
    }

}
