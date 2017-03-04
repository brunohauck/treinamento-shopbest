package com.mentorandroid.shopbest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tonyvu.sc.model.Cart;
import com.android.tonyvu.sc.util.CartHelper;
import com.mentorandroid.shopbest.models.Product;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;

public class ProdutoDetalheActivity extends AppCompatActivity {

    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_detalhe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Cart cart = CartHelper.getCart();

                cart.add(product, 10);
                Intent intent = new Intent(ProdutoDetalheActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });

        ImageView thumbnail = (ImageView) findViewById(R.id.thumbnail);
        TextView textViewProdutoNome = (TextView) findViewById(R.id.textViewProdutoNome);

        Intent i = getIntent();
        product = new Product();
        product = ((Product)i.getSerializableExtra("object"));
        product.setPrice(BigDecimal.valueOf(10));

        textViewProdutoNome.setText(product.getName().toString());

        Picasso.with(this).load(product.getImgUrl())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(thumbnail);
    }

}
