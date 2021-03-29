package com.example.mvpandretrofitwithbaseactivityclass.common;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.example.mvpandretrofitwithbaseactivityclass.R;
import com.example.mvpandretrofitwithbaseactivityclass.retrofit.ApiWebService;
import com.example.mvpandretrofitwithbaseactivityclass.retrofit.MVPView;
import com.example.mvpandretrofitwithbaseactivityclass.retrofit.WebService;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.HttpException;

public class BaseActivity extends AppCompatActivity implements MVPView {

    public static final String PAYLOAD_BUNDLE = "PAYLOAD BUNDLE";
    public static final String FADE_IN_FADE_OUT = "FADE IN FADE OUT";
    public static final String FADE_OUT_FADE_IN = "FADE OUT FADE IN";
    public static final String SLIDE_LEFT = "SLIDE IN RIGHT SLIDE OUT LEFT";
    public static final String SLIDE_RIGHT = "SLIDE IN LEFT SLIDE OUT RIGHT";
    public static final String SLIDE_DEFAULT = "DEFAULT ANIMATION";
    private static final int API_STATUS_CODE_LOCAL_ERROR =0 ;
    private ProgressDialog mProgressDialog;
    public ApiWebService apiWebService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiWebService =  WebService.createService(ApiWebService.class);
    }

    // set toolbar
    public void configureToolbar(String toolbarTitle) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(toolbarTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            finish();
        }
        return true;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void goToNextActivity(Activity context, Class<?> classActivity, Bundle bundle,
                                 String slideAnimation, boolean isFinish) {

        Intent intent = new Intent(context, classActivity);

        // ↓ put bundles with activity
        if (bundle != null)
            intent.putExtra(PAYLOAD_BUNDLE, bundle);

        // ↓ finish activity
        if (isFinish)
            context.finish();
        // ↓ start activity
        context.startActivity(intent);

        // ↓ set animation with change activity
        if (slideAnimation != null)
            switch (slideAnimation) {
                case SLIDE_LEFT:
                    context.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    break;
                case SLIDE_RIGHT:
                    context.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    break;
                case FADE_IN_FADE_OUT:
                    context.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    break;
                case FADE_OUT_FADE_IN:
                    context.overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                    break;
                default:
                    break;
            }
    }

    // on button press scale animation
    public void setScaleAnimation(View view) {
        Animation scaleAnim = AnimationUtils.loadAnimation(this, R.anim.scale_anim);
        view.startAnimation(scaleAnim);
    }

    //share
    public void shareAppIntent(String shareBody) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_subject));
        intent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(intent, getString(R.string.share_using)));
    }

    @Override
    public void showLoader() {
        mProgressDialog = DialogUtil.showLoadingDialog(BaseActivity.this, "Base Activity");
        mProgressDialog.setCancelable(false);
    }

    @Override
    public void dismissLoader() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void showMassage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(Throwable error) {
        if (error instanceof HttpException) {
            switch (((HttpException) error).code()) {
                case HttpsURLConnection.HTTP_UNAUTHORIZED:
                    showMassage("Unauthorised User ");
                    break;
                case HttpsURLConnection.HTTP_FORBIDDEN:
                    showMassage("Forbidden");
                    break;
                case HttpsURLConnection.HTTP_INTERNAL_ERROR:
                    showMassage("Internal Server Error");
                    break;
                case HttpsURLConnection.HTTP_BAD_REQUEST:
                    showMassage("Bad CompleteBookingRequest");
                    break;
                case API_STATUS_CODE_LOCAL_ERROR:
                    showMassage("No Internet Connection");
                    break;
                default:
                    showMassage(error.getLocalizedMessage());
            }
        }

    }
}
