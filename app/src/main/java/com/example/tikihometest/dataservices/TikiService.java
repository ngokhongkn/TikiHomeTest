package com.example.tikihometest.dataservices;

import io.reactivex.Observable;

public interface TikiService {
    Observable<String> fetchDataFromServer();
}
