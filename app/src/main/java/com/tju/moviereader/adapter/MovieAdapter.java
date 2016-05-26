package com.tju.moviereader.adapter;

import android.content.Context;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.tju.moviereader.dto.MovieDto;
import com.tju.moviereader.R;
import com.tju.moviereader.databinding.ItemMovieListBinding;


/**
 * Created by amardeep.kumar on 5/24/2016.
 */
public class MovieAdapter extends CursorAdapter {

    private final LayoutInflater mLayoutInflater;

    public MovieAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);

        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        ItemMovieListBinding itemMovieListBinding = DataBindingUtil.inflate(mLayoutInflater, R.layout.item_movie_list, parent, false);
        View view = itemMovieListBinding.getRoot();
        final ViewHolder viewHolder = new ViewHolder(itemMovieListBinding);
        view.setTag(viewHolder);
        viewHolder.bindItem(getMovieDto(cursor));
        return view;
    }

    private MovieDto getMovieDto(Cursor cursor) {
        MovieDto movie = new MovieDto();
        movie.mMovieName = cursor.getString(1);
        movie.mMovieDescription = cursor.getString(2);
        return movie;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.bindItem(getMovieDto(cursor));
    }

    private static class ViewHolder {

        private final ItemMovieListBinding mBinding;

        public ViewHolder(ItemMovieListBinding binding) {
            mBinding = binding;
        }

        public void bindItem(MovieDto movie) {
            mBinding.setMovie(movie);
        }
    }
}
