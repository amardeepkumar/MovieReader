package com.tju.moviereader.activity;

import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CursorAdapter;

import com.tju.moviereader.CustomAsyncQueryHandler;
import com.tju.moviereader.R;
import com.tju.moviereader.adapter.MovieAdapter;
import com.tju.moviereader.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static final String CONTENT_AUTHORITY = "com.tju.contentprovidertutorial";
    public static final String PATH_MOVIE = "movie";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final Uri CONTENT_URI =
            BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIE).build();
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.movieList.setAdapter(new MovieAdapter(this, null, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER));
        queryForData();
    }

    private void queryForData() {
        CustomAsyncQueryHandler customAsyncQueryHandler = new CustomAsyncQueryHandler(getContentResolver());
        customAsyncQueryHandler.setQueryListener(new CustomAsyncQueryHandler.AsyncQueryListener() {
            @Override
            public void onQueryComplete(int token, Object cookie, Cursor cursor) {
                ((MovieAdapter) mBinding.movieList.getAdapter()).swapCursor(cursor);
            }
        });
        customAsyncQueryHandler.startQuery(0, null, CONTENT_URI, null, null, null, null);
    }
}
