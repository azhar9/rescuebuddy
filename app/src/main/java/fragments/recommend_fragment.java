package fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astudios.rescuebuddy.R;

import adapters.BagRecyclerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class recommend_fragment extends Fragment {


    public recommend_fragment() {
        // Required empty public constructor
    }

    RecyclerView recommendedRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recommend_fragment, container, false);
        recommendedRecycler = (RecyclerView) view.findViewById(R.id.gobag_recommended_recycler);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recommendedRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        BagRecyclerAdapter adapter = new BagRecyclerAdapter(getContext(), null);
        recommendedRecycler.setAdapter(adapter);
    }
}
