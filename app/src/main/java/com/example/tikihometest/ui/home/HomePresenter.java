package com.example.tikihometest.ui.home;

import com.example.tikihometest.App;
import com.example.tikihometest.datamanager.DataManager;
import com.example.tikihometest.dataservices.TikiService;
import com.example.tikihometest.model.HotKeywords;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends MvpBasePresenter<HomeView> {
    @Inject
    CompositeDisposable compositeSubscription;
    @Inject
    TikiService tikiService;
    @Inject
    DataManager dataManager;

    HomePresenter() {
        App.getAppComponent().inject(this);
    }

    void fetchDataFromServerAndEdit() {
        showLoading();
        final Disposable disposable = tikiService.fetchDataFromServer()
                .flatMap((Function<String, Observable<HotKeywords>>) this::parseStringJsonToObjectAndEditObservable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::fetchDataSuccess, this::fetchDataError);
        compositeSubscription.add(disposable);
    }

    private Observable<HotKeywords> parseStringJsonToObjectAndEditObservable(String jsonString) {
        return Observable.create(observableEmitter -> {
            observableEmitter.onNext(dataManager.parseStringJsonToObjectAndEdit(jsonString));
            observableEmitter.onComplete();
        });
    }

    private void fetchDataSuccess(HotKeywords data) {
        if (getView() != null) {
            showContent();
            getView().setData(data);
        }

    }

    private void fetchDataError(Throwable throwable) {
        System.out.println("error");
        showError(throwable);

    }

    private void showLoading() {
        if (getView() != null) {
            getView().showLoading(false);
        }
    }

    private void showContent() {
        if (getView() != null) {
            getView().showContent();
        }
    }

    private void showError(Throwable throwable) {
        if (getView() != null) {
            getView().showError(throwable, false);
        }
    }
}
