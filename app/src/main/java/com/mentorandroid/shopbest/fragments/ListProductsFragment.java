package com.mentorandroid.shopbest.fragments;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mentorandroid.shopbest.R;
import com.mentorandroid.shopbest.adapters.MyRecyclerAdapter;
import com.mentorandroid.shopbest.models.Product;
import com.mentorandroid.shopbest.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListProductsFragment extends Fragment {

    private List<Product> produtosList = new ArrayList<Product>();

    private RecyclerView mRecyclerView;

    private MyRecyclerAdapter adapter;

    private ProgressDialog pDialog;

    private Context ctx;

    private View rootView;

    public Activity mainActivity;


    public ListProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_list_products, container, false);

        ctx = getActivity();

         /* Initialize recyclerview */
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ctx));


        if (Util.isNetworkAvailable(ctx)) {
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Carregando os restaurantes. Por favor aguarde...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        return rootView;
    }

}
