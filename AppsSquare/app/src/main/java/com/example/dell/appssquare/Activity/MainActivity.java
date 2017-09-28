package com.example.dell.appssquare.Activity;

import android.support.v4.util.TimeUtils;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dell.appssquare.Adapter.ReposAdapter;
import com.example.dell.appssquare.BuildConfig;
import com.example.dell.appssquare.Model.Owner;
import com.example.dell.appssquare.Model.Repository;
import com.example.dell.appssquare.R;
import com.example.dell.appssquare.Rest.ApiClient;
import com.example.dell.appssquare.Rest.ApiInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private ReposAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Repository> mRepositryList;
    private ApiInterface apiInterface;
    ProgressBar progressBar;
   SwipeRefreshLayout refresh;
    TextView not_found;
    RealmConfiguration realmConfiguration;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refresh = (SwipeRefreshLayout)findViewById(R.id.refresh);

        mRecyclerView=(RecyclerView)findViewById(R.id.Rv);
        layoutManager= new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        not_found=(TextView)findViewById(R.id.not_found);


////////////////realm
    /*    Realm.init(this);
        realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(realmConfiguration);
        progressBar.setVisibility(View.GONE);*/
        //assignUIEvent();
  /*      RealmResults<Repository> reposR = realm.where(Repository.class).findAll();
        if (reposR.size()<= 0){
            loaddata();

        }else {
            mAdapter= new ReposAdapter(reposR,MainActivity.this);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setVisibility(View.VISIBLE);
            not_found.setVisibility(View.GONE);

        }*/

   loaddata();

    }
    /*
    private void assignUIEvent(){
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loaddata();
            }
        });

        not_found.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loaddata();
            }
        });

    }

*/
    public void loaddata(){

    /* not_found.setVisibility(View.GONE);
     mRecyclerView.setVisibility(View.GONE);*/
        showProgress();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Repository>> call=apiInterface.getRepose();
        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {

                mRepositryList=response.body();
                mAdapter= new ReposAdapter(mRepositryList,getApplicationContext());
                mRecyclerView.setAdapter(mAdapter);
                hideProgress();

/*
                // save data local
                for (int i =0; i<= response.body().size();i++){

                    realm.beginTransaction();
                    Repository repository= realm.createObject(Repository.class);
                    Owner owner= realm.createObject(Owner.class);

                    repository.setName(response.body().get(i).getName());
                    repository.setDescription(response.body().get(i).getDescription());
                    repository.setFork(response.body().get(i).getFork());
                    repository.setOwner(owner);


                    realm.commitTransaction();
                }
                mRecyclerView.setVisibility(View.VISIBLE);

*/


            }
            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {


            /*  not_found.setVisibility(View.VISIBLE);
                hideProgress();*/
            }
        });

    }

    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
/*
        if (!refresh.isRefreshing()) {
        }*/
    }


    private void hideProgress(){
        progressBar.setVisibility(View.GONE);
       // refresh.setRefreshing(false);
    }

}
