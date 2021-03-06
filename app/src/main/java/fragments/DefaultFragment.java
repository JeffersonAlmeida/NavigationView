package fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jeffersonalmeida.myappnewasversion.R;

import adapters.MyAdapter;
import okhttp3.ResponseBody;
import restapi.RestClient;
import restapi.RestInterface;
import restapi.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DefaultFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private RestInterface apiService;

    private Context context;

    public static DefaultFragment newInstance() {
        return new DefaultFragment();
    }

    public DefaultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    private Callback<Result> callback = new Callback<Result>() {
        @Override
        public void onResponse(Call<Result> call, Response<Result> response) {
            if (response.isSuccess()) {
                Result resul = response.body();
                Log.d("onResponse", "onResponse");
                showContent(resul);
            } else {
                int statusCode = response.code();
                // handle request errors yourself
                ResponseBody errorBody = response.errorBody();
            }
        }

        @Override
        public void onFailure(Call<Result> call, Throwable t) {
            Log.d("FAILURE", "Failure");
        }
    };

    private void showContent(Result resul) {
        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(context, resul);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RestClient restInterface = new RestClient();
        apiService = restInterface.getApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View screen = inflater.inflate(R.layout.fragment_default, container, false);
        mRecyclerView = (RecyclerView) screen.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this.context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Call<Result> result = apiService.getRecentPosts();

        result.enqueue(callback);

        return screen;
    }

}
