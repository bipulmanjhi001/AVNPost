package avnpost.com.drawer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import avnpost.com.R;
import avnpost.com.model.TabAdapter;
import avnpost.com.newstabs.VideoNews;
import avnpost.com.newstabs.BussinessNews;
import avnpost.com.newstabs.EpaperNews;
import avnpost.com.newstabs.GamesNews;
import avnpost.com.newstabs.InternationalNews;
import avnpost.com.newstabs.LifeStyleNews;
import avnpost.com.newstabs.SpecialNews;
import avnpost.com.newstabs.StateNews;
import avnpost.com.newstabs.TechnologyNews;

public class Home extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TabAdapter adapter;
    TabLayout tabLayout;
    ViewPager viewPager;
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Home() {
    }

    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
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
        View view= inflater.inflate(R.layout.home, container, false);

        viewPager = (ViewPager)view.findViewById(R.id.viewPager);
        tabLayout = (TabLayout)view.findViewById(R.id.tabLayout);
        adapter = new TabAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new SpecialNews(), "आपके लिए");
        adapter.addFragment(new StateNews(), "राज्य");
        adapter.addFragment(new InternationalNews(), "अंतरराष्ट्रीय");
        adapter.addFragment(new GamesNews(), "खेल");
        adapter.addFragment(new TechnologyNews(), "टैकनोलजी");
        adapter.addFragment(new BussinessNews(),"बिजनेस");
        adapter.addFragment(new LifeStyleNews(),"लाइफ स्टाइल");
        adapter.addFragment(new VideoNews(),"मनोरंजन");
        adapter.addFragment(new EpaperNews(),"ई-पेपर");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

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
}