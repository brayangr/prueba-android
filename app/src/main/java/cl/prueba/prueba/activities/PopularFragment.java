package cl.prueba.prueba.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cl.prueba.prueba.R;
import cl.prueba.prueba.models.Movies;
import cl.prueba.prueba.services.TheMovieDbAPI;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PopularFragment extends Fragment implements Callback<Movies> {

    static final String BASE_URL = "https://api.themoviedb.org";

    public PopularFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.loadData();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            ((MainActivity) getActivity()).getSupportActionBar().setTitle("Popular");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void loadData() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        TheMovieDbAPI theMovieDbAPI = retrofit.create(TheMovieDbAPI.class);

        Call<Movies> call = theMovieDbAPI.getPopular("34738023d27013e6d1b995443764da44");
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<Movies> call, Response<Movies> response) {
        if(response.isSuccessful()) {

            MoviesList listAdapter = new MoviesList(this.getActivity(), response.body().getResults());
            ListView list = this.getView().findViewById(R.id.popular_list_view);
            list.setAdapter(listAdapter);

        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<Movies> call, Throwable t) {
        t.printStackTrace();
    }
}
