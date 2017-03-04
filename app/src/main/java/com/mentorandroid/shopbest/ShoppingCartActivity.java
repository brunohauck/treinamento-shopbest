package com.mentorandroid.shopbest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.tonyvu.sc.model.Cart;
import com.android.tonyvu.sc.model.Saleable;
import com.android.tonyvu.sc.util.CartHelper;
import com.mentorandroid.shopbest.models.CartItem;
import com.mentorandroid.shopbest.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShoppingCartActivity extends AppCompatActivity {

    private static final String TAG = "ShoppingCartActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);


        final Cart cart = CartHelper.getCart();
        getCartItems(cart);
    }

    private List<CartItem> getCartItems(Cart cart) {
        List<CartItem> cartItems = new ArrayList<CartItem>();
        Log.d(TAG, "Current shopping cart: " + cart);

        Map<Saleable, Integer> itemMap = cart.getItemWithQuantity();
        for (Map.Entry<Saleable, Integer> entry : itemMap.entrySet()) {
            CartItem cartItem = new CartItem();
            Product p = new Product();
            p = (Product) entry.getKey();
            Toast.makeText(this, p.getName(), Toast.LENGTH_SHORT).show();
            cartItem.setProduct(p);
            cartItem.setQuantity(entry.getValue());
            cartItems.add(cartItem);
        }

        Log.d(TAG, "Cart item list: " + cartItems);
        return cartItems;
    }
}
