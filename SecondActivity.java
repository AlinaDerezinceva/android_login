package com.example.app_login;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondActivity extends AppCompatActivity {

    private EditText patrEditText, nameEditText, surnameEditText, loginEditText, passwordEditText, buttonToVhod;
    private Button buttonOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        patrEditText = findViewById(R.id.editTextpatr);
        nameEditText = findViewById(R.id.editTextname);
        surnameEditText = findViewById(R.id.editTextsurname);
        loginEditText = findViewById(R.id.editTextLoginRegister);
        passwordEditText = findViewById(R.id.editTextPasswordRegister);
        buttonOk = findViewById(R.id.buttonOk);



        TextView textView = new TextView(this);
        textView.setTextSize(20);
        textView.setPadding(250, 1, 0, 0);
        textView.setText("Регистрация");

        LinearLayout layout = findViewById(R.id.linearLayout);
        layout.addView(textView);

        buttonOk.setOnClickListener(v -> {
            String surname = surnameEditText.getText().toString().trim();
            String name = nameEditText.getText().toString().trim();
            String patr = patrEditText.getText().toString().trim();
            String login = loginEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (surname.isEmpty() || name.isEmpty() || patr.isEmpty() || login.isEmpty() || password.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Пожалуйста, заполните все поля.", Toast.LENGTH_SHORT).show();
            } else {
                registerUser(surname, name, patr, login, password);
            }
        });
    }

    private void registerUser(String surname, String name, String patr, String login, String password) {
        User user = new User(surname, name, patr, login, password);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.17.8.247:5000/customers/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServerApi serverApi = retrofit.create(ServerApi.class);
        Call<User> call = serverApi.addUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User createdUser = response.body();
                    Toast.makeText(getApplicationContext(), "Регистрация успешна! Пользователь ID: " + createdUser.getID(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Ошибка регистрации: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Ошибка сети: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

