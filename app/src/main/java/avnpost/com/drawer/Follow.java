package avnpost.com.drawer;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import avnpost.com.R;

public class Follow extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    LinearLayout rate_us,share;
    private CardView fb,twit,utube;

    public Follow() {
    }

    public static Follow newInstance(String param1, String param2) {
        Follow fragment = new Follow();
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
        View view = inflater.inflate(R.layout.follow, container, false);
        rate_us=(LinearLayout)view.findViewById(R.id.rate_us);
        rate_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("market://details?id=" + getActivity().getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getActivity(), "Check again..", Toast.LENGTH_LONG).show();
                }
          }
        });

        fb=(CardView)view.findViewById(R.id.fb);
        fb.setOnClickListener(this);
        twit=(CardView)view.findViewById(R.id.twit);
        twit.setOnClickListener(this);
        utube=(CardView)view.findViewById(R.id.utube);
        utube.setOnClickListener(this);

        share=(LinearLayout)view.findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "AVNPost");
                intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=avnpost.com");
                startActivity(Intent.createChooser(intent, "एवीएन पोस्ट हिंदी भाषी पाठकों के लिए पेश करता है बेहद सटीक, ताजा एवं विश्वसनीय खबरें, हम वास्तविक और प्रामाणिक खबरों में विश्वास करते हैं। हमारे संवाददाता प्रामाणिक खबरों के लिए 24×7 कठिन काम करते हैं, ताकि आपतक सही खबरों को पंहुचा सकें।"));
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

    @Override
    public void onClick(View v) {
        if(v == fb){
            String url = "https://www.facebook.com/avnpostnews";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
        if(v == utube){
            String url = "https://www.youtube.com/channel/UCyUJTV1aAuT_Gk9f0i4-Zfw";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
        if(v == twit){
            String url = "https://twitter.com/AVNpost";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}