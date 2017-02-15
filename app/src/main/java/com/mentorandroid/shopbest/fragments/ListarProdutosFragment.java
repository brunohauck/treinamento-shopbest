package com.mentorandroid.shopbest.fragments;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mentorandroid.shopbest.MainActivity;
import com.mentorandroid.shopbest.ProdutoDetalheActivity;
import com.mentorandroid.shopbest.R;
import com.mentorandroid.shopbest.adapters.MyRecyclerAdapter;
import com.mentorandroid.shopbest.models.Produto;
import com.mentorandroid.shopbest.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListarProdutosFragment extends Fragment {

    private List<Produto> produtosList = new ArrayList<Produto>();

    private RecyclerView mRecyclerView;

    private MyRecyclerAdapter adapter;

    private ProgressDialog pDialog;

    private Context ctx;

    private View rootView;

    public Activity mainActivity;

    public ListarProdutosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_listar_produtos, container, false);

        ctx = getActivity();

         /* Initialize recyclerview */
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ctx));

        /*Downloading data from below url*/

        if (Util.isNetworkAvailable(ctx)) {

            //final String url = "http://dev.escondidim.com.br/index.php/page/get_restaurantes_json";

            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Carregando os restaurantes. Por favor aguarde...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(ctx);
            String url ="http://ub.idsgeo.com/mentor/page/get_all_products";

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            //mTextView.setText("Response is: "+ response.substring(0,500));
                            parseResult(response.toString());
                            pDialog.dismiss();
                            adapter = new MyRecyclerAdapter(ctx,ListarProdutosFragment.this , produtosList);
                            adapter.setOnCardViewClickListener(new MyRecyclerAdapter.OnCardViewClickListener() {
                                @Override
                                public void onClick(Produto produto) {

                                    Log.i("DEBUG","Entrou 02");
                                    Intent intent = new Intent(ctx, ProdutoDetalheActivity.class);
                                    //intent.putExtra()
                                    intent.putExtra("object", (Serializable) produto);
                                    startActivity(intent);

                                }

                            });
                            mRecyclerView.setAdapter(adapter);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //mTextView.setText("That didn't work!");

                }
            });
            // Add the request to the RequestQueue.
            queue.add(stringRequest);

            //new AsyncHttpTask().execute(url);

        }else{
            Log.d("DEBUG", "NETWORK UNVAILABLE");
        }
        // Inflate the layout for this fragment
        return rootView;
    }

    private void parseResult(String result) {
        try {
            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("produtos");

            /*Initialize array if null*/
            if (null == produtosList) {
                produtosList = new ArrayList<Produto>();
            }

            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);

                Produto item = new Produto();
                item.setProdutoId(Long.valueOf(post.optString("id")));
                item.setNome(post.optString("nome"));
                item.setTipo(post.optString("tipo"));
                item.setImgUrl(post.optString("imgurl"));
                /*
                logradouro: "Av. Brasília",
                        numero: "1313",
                        complemento: "Lj 04 - Sob Loja",
                        bairro: "São Benedito",
                        cidade: "Santa Luzia",
                        estado: "MG",
                        cep: ""


                */
//                Endereco endereco = new Endereco();
//                JSONObject enderecoPost = post.optJSONObject("endereco");
//                endereco.setLogradouro(enderecoPost.optString("logradouro"));
//                endereco.setNumero(enderecoPost.getString("numero"));
//                endereco.setBairro(enderecoPost.getString("bairro"));
//                endereco.setCidade(enderecoPost.getString("cidade"));
//                endereco.setEstado(enderecoPost.getString("estado"));
//                item.setEndereco(endereco);

//
//                //item.setEndereco(post.optString("endereco"));
//                item.setFlagPeidoCadastrado(post.optString("flag_pedido_cadastro"));
//                item.setLatitude(post.optString("latitude"));
//                item.setLongitude(post.optString("pedido_url"));
//                item.setUrlPedidoWeb(post.optString(""));
                produtosList.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ((MainActivity)getActivity()).setProdutosList(produtosList);
    }

}
