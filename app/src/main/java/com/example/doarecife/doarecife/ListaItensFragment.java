package com.example.doarecife.doarecife;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.doarecife.doarecife.model.Categoria;
import com.example.doarecife.doarecife.model.Doacao;
import com.example.doarecife.doarecife.model.Item;
import com.example.doarecife.doarecife.model.ItemDoacao;
import com.google.gson.Gson;
import com.unibratec.danilo.projetocds.model.Cd;
import com.unibratec.danilo.projetocds.model.Discoteca;
import com.unibratec.danilo.projetocds.model.Genero;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListaItensFragment extends Fragment {

    @Bind(R.id.list_item)
    ListView mListView;
    @Bind(R.id.swipe)
    SwipeRefreshLayout mSwipe;

    List<Item> mItens;

    ArrayAdapter<Item> mAdapter;
    ItensTask mTask;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setReenterTransition(true);
        mItens = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_lista, container, false);
        ButterKnife.bind(this, layout);

        mAdapter = new ItensAdapter(getContext(), mItens);
        mListView.setAdapter(mAdapter);

        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mTask = new ItensTask();
                mTask.execute();
            }
        });
        return  layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mItens.size() == 0 && mTask == null) {
            mTask = new ItensTask();
            mTask.execute();
        } else if (mTask != null && mTask.getStatus() == AsyncTask.Status.RUNNING){
            mSwipe.setRefreshing(true);
        }
    }

    private void showProgress(){
        mSwipe.post(new Runnable() {
            @Override
            public void run() {
                mSwipe.setRefreshing(true);
            }
        });
    }

    @OnItemClick(R.id.list_item)
        void onItemSelected(int position){
        Item item = mItens.get(position);
        if (getActivity() instanceof CliqueNoItemListener){
            CliqueNoItemListener listener = (CliqueNoItemListener)getActivity();
            listener.itemFoiClicado(item);
        }
    }

    public interface CliqueNoItemListener{
        void itemFoiClicado(Item item);
    }

    class ItensTask extends AsyncTask<Void, Void, Doacao> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            showProgress();
        }

        @Override
        protected Doacao doInBackground(Void... params) {

            OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://dl.dropboxusercontent.com/s/n5qbyvner44nhkr/Discoteca.json")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    String jsonString = response.body().string();
                    Log.d("NGVL", jsonString);
                    Gson gson = new Gson();
                    Discoteca discoteca = gson.fromJson(jsonString, Discoteca.class);
                    return discoteca;

                } catch (Exception e){
                    e.printStackTrace();
                }

            return null;
        }

        @Override
        protected void onPostExecute(Doacao doacao) {
            super.onPostExecute(doacao);

            if(doacao != null){
                mItens.clear();
                for (Categoria categoria : doacao.getCategoriaList()){
                    mItens.addAll(categoria.getItemList());
                }
                mAdapter.notifyDataSetChanged();
            }
            mSwipe.setRefreshing(false);
        }
    }
}
