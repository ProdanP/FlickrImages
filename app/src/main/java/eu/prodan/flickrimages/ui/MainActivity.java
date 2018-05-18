package eu.prodan.flickrimages.ui;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import eu.prodan.flickrimages.R;
import eu.prodan.flickrimages.adapters.PicassoCache;
import eu.prodan.flickrimages.datamodel.FlickrResponse;
import eu.prodan.flickrimages.ui.viewmodels.FlickrFeedViewModel;
import eu.prodan.flickrimages.ui.views.CustomGridLayoutManager;
import eu.prodan.flickrimages.ui.views.DynamicToolbar;
import eu.prodan.flickrimages.ui.views.RecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DynamicToolbar toolbar;
    FlickrFeedViewModel viewModel;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(FlickrFeedViewModel.class);

        recyclerView = findViewById(R.id.images_recycler_view);
        recyclerView.setLayoutManager(new CustomGridLayoutManager(this, 500));

        toolbar = findViewById(R.id.dynamic_toolbar);
        toolbar.setOnSearchIconClickListner(onSearchClickListener);
        toolbar.setOnEditTextKeyListener(onEditorActionListener);
        setSupportActionBar(toolbar);

        progressDialog = new ProgressDialog(this);

        final RecyclerViewAdapter adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        viewModel.getItemsFeedListObservable().observe(this, new Observer<FlickrResponse>() {
            @Override
            public void onChanged(@Nullable FlickrResponse flickrResponse) {
                if (flickrResponse != null)
                    adapter.setRepos(flickrResponse.getPhotosList().getPhotoInfoList());
                hideLoading();
            }
        });

    }

    public void callForFlickr() {
        showLoading();
        viewModel.searchByTag(toolbar.getTextFromInput());
    }

    public void showLoading(){
        if(progressDialog != null){
            progressDialog.setMessage("Please wait while loading...");
            progressDialog.show();
        }
    }

    public void hideLoading(){
        if(progressDialog!=null && progressDialog.isShowing())
            progressDialog.hide();
    }

    View.OnClickListener onSearchClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            callForFlickr();
            hideKeyboard();
        }
    };

    EditText.OnEditorActionListener onEditorActionListener = new EditText.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                callForFlickr();
               hideKeyboard();
                return true;
            }
            return false;
        }
    };


    private void hideKeyboard(){
        View view = this.getCurrentFocus();
        if(view != null && view instanceof EditText){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PicassoCache.getInstance().deleteCache();
    }
}
