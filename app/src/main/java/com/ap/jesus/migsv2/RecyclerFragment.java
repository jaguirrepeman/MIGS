package com.ap.jesus.migsv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class RecyclerFragment extends Fragment{


    private RecyclerView mRecyclerView;
    private RecyclerAdapter mRecyclerviewAdapter;
    private LinearLayoutManager mLinearLayoutManager;
   private static final String ARG_SECTION_NUMBER = "section_number";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recycled, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
       // mRecyclerView.setAdapter(new RecyclerAdapter(POIs.getPOIsArray()));
        mRecyclerviewAdapter = new RecyclerAdapter(POIs.getPOIsArray());
        mRecyclerView.setAdapter(mRecyclerviewAdapter);
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), POIDetail.class);
                        intent.putExtra("id", position);
                        startActivityForResult(intent, 0);
                    }
                })
        );
        mRecyclerviewAdapter.SetOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), POIDetail.class);
                intent.putExtra("id", position);
                startActivityForResult(intent, 0);
            }
        });
        return rootView;
    }

    /**
     * Creación prefabricada de un {@link RecyclerFragment}
     */
    public static RecyclerFragment newInstance(int sectionNumber) {
        RecyclerFragment fragment = new RecyclerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public RecyclerFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int section = getArguments().getInt(ARG_SECTION_NUMBER);
        List<POI> pois = new ArrayList<POI>();
        if (section == 1)
            pois = POIs.getSamplePOIs();
        else { //section == 3
            SharedPreferences prefs = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
            String route = prefs.getString("route", "");
            StringTokenizer st = new StringTokenizer(route, ", ");
            while (st.hasMoreTokens()){
                String str = st.nextToken();
                int ind = Integer.parseInt(str);
                pois.add(POIs.getPOI(ind));

            }

        }
        mRecyclerviewAdapter = new RecyclerAdapter(pois);
        mRecyclerView.setAdapter(mRecyclerviewAdapter);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }
    @Override
    public void onResume(){
        int section = getArguments().getInt(ARG_SECTION_NUMBER);
        List<POI> pois = new ArrayList<POI>();
        if (section == 1)
            pois = POIs.getSamplePOIs();
        else { //section == 3
            SharedPreferences prefs = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
            String route = prefs.getString("route", "");
            StringTokenizer st = new StringTokenizer(route, ", ");
            while (st.hasMoreTokens()){
                String str = st.nextToken();
                int ind = Integer.parseInt(str);
                pois.add(POIs.getPOI(ind));

            }

        }
        mRecyclerviewAdapter = new RecyclerAdapter(pois);
        mRecyclerView.setAdapter(mRecyclerviewAdapter);
        super.onResume();
    }

}