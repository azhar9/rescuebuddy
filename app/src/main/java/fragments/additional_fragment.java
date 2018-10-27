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
public class additional_fragment extends Fragment {


    public additional_fragment() {
        // Required empty public constructor
    }


    RecyclerView additionalRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_additional_fragment, container, false);
        additionalRecycler = (RecyclerView) view.findViewById(R.id.gobag_additional_recycler);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        additionalRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        BagRecyclerAdapter adapter = new BagRecyclerAdapter(getContext(), null);
        additionalRecycler.setAdapter(adapter);
    }

}
