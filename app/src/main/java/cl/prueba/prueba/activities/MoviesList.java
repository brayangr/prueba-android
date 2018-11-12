package cl.prueba.prueba.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import cl.prueba.prueba.R;
import cl.prueba.prueba.models.Movie;

public class MoviesList extends ArrayAdapter<Movie> {

    private static final String imagesUrl = "http://image.tmdb.org/t/p/w500/";

    private Activity context;
    private ArrayList<Movie> movies;

    public MoviesList(Activity context, ArrayList<Movie> movies) {
        super(context, R.layout.movie_row, movies);
        this.context = context;
        this.movies = movies;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.movie_row, null, true);
        TextView txtTitle = rowView.findViewById(R.id.movie_title);
        TextView txtDescription = rowView.findViewById(R.id.movie_description);
        ImageView imageView = rowView.findViewById(R.id.img);

        Movie movie = this.movies.get(position);
        txtTitle.setText(movie.getTitle());
        txtDescription.setText(movie.getOverview());

        Picasso.get().load(imagesUrl + movie.getPoster_path()).into(imageView);

        return rowView;
    }
}
