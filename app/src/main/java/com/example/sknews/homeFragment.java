package com.example.sknews;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class homeFragment extends Fragment {

    String api="3d19b977f9c7444c95472a0a282a36a9";
    ArrayList<ModalClass> modalClassArrayList;
    Adapter adapter;
    String country="in";
    private RecyclerView recyclerViewofhome;

    public homeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
           View v=inflater.inflate(R.layout.fragment_home, container, false);
            recyclerViewofhome=v.findViewById(R.id.recyclerviewofhome);
            modalClassArrayList=new ArrayList<>();
            recyclerViewofhome.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter=new Adapter(getContext(),modalClassArrayList);
            recyclerViewofhome.setAdapter(adapter);
            findNews();
            return v;
    }
    private void findNews()
    {
        ApiUtilities.getApiInterface().getNews(country,100,api).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if (response.isSuccessful())
                {
                    modalClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {
                Toast.makeText(getActivity(), "Error....", Toast.LENGTH_SHORT).show();
            }
        });
    }

}