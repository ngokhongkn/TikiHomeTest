package com.example.tikihometest.dataservices;

import com.example.tikihometest.api.TikiApi;

import javax.inject.Inject;

import io.reactivex.Observable;

public class TikiServiceImpl implements TikiService {
    private final TikiApi tikiApi;

    @Inject
    public TikiServiceImpl(TikiApi tikiApi) {
        this.tikiApi = tikiApi;
    }

    @Override
    public Observable<String> fetchDataFromServer() {
        return tikiApi.fetchDataFromServer();
    }
}
