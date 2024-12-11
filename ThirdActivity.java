package com.example.app_login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




public class ThirdActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProducts;
    private Button buttonPlaceOrder;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);

        recyclerViewProducts = findViewById(R.id.recyclerViewProducts);
        buttonPlaceOrder = findViewById(R.id.buttonPlaceOrder);


        loadProducts("категория 1");



        buttonPlaceOrder.setOnClickListener(this::placeOrder);


        findViewById(R.id.buttonCategory1).setOnClickListener(v -> loadProducts("категория 1"));
        findViewById(R.id.buttonCategory2).setOnClickListener(v -> loadProducts("категория 2"));
        findViewById(R.id.buttonCategory3).setOnClickListener(v -> loadProducts("категория 3"));
    }



    private void loadProducts(String category) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://217.71.129.139:4937/product/category/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServerApi apiService = retrofit.create(ServerApi.class);
        Call<List<Product>> call = apiService.getProductsByCategory(category);

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productAdapter = new ProductAdapter(response.body());
                    recyclerViewProducts.setLayoutManager(new LinearLayoutManager(ThirdActivity.this));
                    recyclerViewProducts.setAdapter(productAdapter);
                } else {
                    Log.e("ThirdActivity", "Ошибка получения продуктов");
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("ThirdActivity", "Ошибка: " + t.getMessage());
            }
        });
    }

    private void placeOrder(View view) {

    }
}




