package com.mentorandroid.shopbest.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mentorandroid.shopbest.R;

import com.mentorandroid.shopbest.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private List<Product> produtosList;

    private Context mContext;
    private FragmentManager fmAdapter;
    private Fragment fAdapter;

    private OnCardViewClickListener onCardViewClickListener;

    public OnCardViewClickListener getOnCardViewClickListener() {
        return onCardViewClickListener;
    }

    public void setOnCardViewClickListener(OnCardViewClickListener onCardViewClickListener) {
        this.onCardViewClickListener = onCardViewClickListener;
    }

    public MyRecyclerAdapter(Context context, Fragment f, List<Product> productList) {
        this.produtosList = productList;
        this.mContext = context;
        //this.fmAdapter = fm;
        this.fAdapter = f;
    }

    @Override
    public MyRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
        ViewHolder mh = new ViewHolder(v);

        return mh;
    }
    // Inserir isso depois no
    class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView thumbnail;
        TextView title;
        TextView descricao;
        public ViewHolder(View itemView){
            super(itemView);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            title = (TextView) itemView.findViewById(R.id.title);
            descricao = (TextView) itemView.findViewById(R.id.descricao);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            cardView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                   // CallIntent(getAdapterPosition());
                }
            });
        }

    }

    @Override
    public void onBindViewHolder(ViewHolder feedListRowHolder, int i) {

        final Product produto = produtosList.get(i);

        Picasso.with(mContext).load(produto.getImgUrl())
                .error(R.drawable.placeholder)
                .resize(250, 250)
                .placeholder(R.drawable.placeholder)
                .into(feedListRowHolder.thumbnail);

        feedListRowHolder.title.setText(Html.fromHtml(produto.getName()));

        //String info = "Endereço: " + restaurant.getEndereco().toString() + "<br />Tipo: " + restaurant.getTipo().toString() + "<br />Telefone: " + restaurant.getTelefone().toString() + "<br />Celular: " + restaurant.getCelular().toString();
        //Endereco endereco = new Endereco();
        //endereco = restaurant.getEndereco();

        //String enderecoPrint = endereco.getLogradouro().toString() + "<br />" + endereco.getBairro().toString();

        String info = "<br /> Preço R$"+produto.getPrice();

        //String info = enderecoPrint + "<br />Tipo: " + restaurant.getTipo().toString() + "<br />Telefone: " + restaurant.getTelefone().toString() + "<br />Celular: " + restaurant.getCelular().toString();


        feedListRowHolder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(onCardViewClickListener!=null){
                    onCardViewClickListener.onClick(produto);
                }

            }
        });

        feedListRowHolder.descricao.setText(Html.fromHtml(info));
    }

    @Override
    public int getItemCount() {
        return (null != produtosList ? produtosList.size() : 0);
    }

    public interface OnCardViewClickListener {

        void onClick(
                Product produto
        );

    }
}
