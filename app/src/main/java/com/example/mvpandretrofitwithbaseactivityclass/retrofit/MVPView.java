package com.example.mvpandretrofitwithbaseactivityclass.retrofit;

public interface MVPView {

    void showLoader();
    void dismissLoader();
    void showMassage(String msg);
    void showError(Throwable throwable);

}
