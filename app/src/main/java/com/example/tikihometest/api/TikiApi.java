package com.example.tikihometest.api;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface TikiApi {
    @GET("keywords.json")
    Observable<String> fetchDataFromServer();
}
