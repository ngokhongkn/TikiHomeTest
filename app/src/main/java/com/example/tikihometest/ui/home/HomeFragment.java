package com.example.tikihometest.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.tikihometest.R;
import com.example.tikihometest.model.HotKeywords;
import com.example.tikihometest.ui.adapter.HotKeyAdapter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceFragment;
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration;

public class HomeFragment extends MvpLceFragment<FrameLayout, HotKeywords, HomeView, HomePresenter> implements HomeView {

    private HotKeyAdapter mAdapter;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView mRecyclerView = view.findViewById(R.id.home_recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        mRecyclerView.addItemDecoration(new LayoutMarginDecoration( 1, 16) );
        mAdapter = new HotKeyAdapter();
        mRecyclerView.setAdapter(mAdapter);
        loadData(false);
    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }


    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }


    @Override
    public void setData(HotKeywords data) {
        mAdapter.setItems(data.getKeywords());
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getPresenter().fetchDataFromServerAndEdit();
    }
}
