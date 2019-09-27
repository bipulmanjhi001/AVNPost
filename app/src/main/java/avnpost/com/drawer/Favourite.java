package avnpost.com.drawer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import avnpost.com.R;
import avnpost.com.adapter.FavouriteNewsAdapter;
import avnpost.com.api.URLs;
import avnpost.com.classmodel.BussinessNewsModel;
import avnpost.com.classmodel.FavouriteNewsModel;
import avnpost.com.database.Contact;
import avnpost.com.database.DatabaseHandler;
import avnpost.com.main.SinglePost;
import avnpost.com.model.VolleySingleton;

public class Favourite extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    ListView listView;
    ArrayList<FavouriteNewsModel> favouriteNewsModels;
    ProgressBar progressBar;
    FavouriteNewsAdapter favouriteNewsAdapter;
    String ids;
    DatabaseHandler db;
    Button Clear_click;
    public Favourite() {
    }

    public static Favourite newInstance(String param1, String param2) {
        Favourite fragment = new Favourite();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favourite, container, false);

        listView=(ListView)view.findViewById(R.id.lv_name);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView c = (TextView)view.findViewById(R.id.favourite_id);
                String ids = c.getText().toString();
                Intent intent = new Intent(getActivity(), SinglePost.class);
                intent.putExtra("ids",ids);
                startActivity(intent);
            }
        });
        favouriteNewsModels=new ArrayList<FavouriteNewsModel>();
        progressBar=(ProgressBar)view.findViewById(R.id.bussiness_new_list);
        db = new DatabaseHandler(getActivity());
        CallList();

        Clear_click=(Button)view.findViewById(R.id.Clear_click);
        Clear_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              db.deleteAll();
                CallList();
                progressBar.setVisibility(View.GONE);
            }
        });
        return view;
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

    public void CallList() {
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            ids = cn.getName();

            StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.SINGLE_ROOT_URL + ids,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONObject obj = new JSONObject(response);
                                String id = obj.getString("id");
                                String title = obj.getString("title");
                                String date = obj.getString("date");
                                String author_name = obj.getString("author_name");
                                String author = obj.getString("author");
                                String content = obj.getString("content");
                                String image = obj.getString("image");

                                FavouriteNewsModel favouriteNewsModel = new FavouriteNewsModel(id, title, date, author, author_name, image);
                                favouriteNewsModels.add(favouriteNewsModel);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                progressBar.setVisibility(View.GONE);
                                favouriteNewsAdapter = new FavouriteNewsAdapter(favouriteNewsModels, getActivity());
                                listView.setAdapter(favouriteNewsAdapter);
                                favouriteNewsAdapter.notifyDataSetChanged();
                            } catch (NullPointerException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    }) {
            };
            VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
        }
        progressBar.setVisibility(View.GONE);
    }
}