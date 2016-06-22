package com.octavio.ruber.tipcalc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.octavio.ruber.tipcalc.R;
import com.octavio.ruber.tipcalc.models.TipRecord;

import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipHistoryListFragment extends Fragment implements TipHistoryListFragmentListener {


    public TipHistoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tip_history_list, container, false);
        // Inflate the layout for this fragment
        ButterKnife.bind(this,view)
        return view;
    }


    @Override
    public void addToList(TipRecord record) {

    }

    @Override
    public void clearList() {

    }
}
