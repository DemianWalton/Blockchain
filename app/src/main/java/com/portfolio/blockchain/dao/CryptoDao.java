package com.portfolio.blockchain.dao;

import androidx.annotation.NonNull;

import com.portfolio.blockchain.ResultListener;
import com.portfolio.blockchain.api.CoingeckoApiService;
import com.portfolio.blockchain.model.CryptocurrencyModel;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CryptoDao {

    private static final String BASE_URL = "https://api.coingecko.com/api/v3/";
    private CoingeckoApiService service;


    public CryptoDao() {

        // Creamos un interceptor y le indicamos el log level a usar
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        // Asociamos el interceptor a las peticiones
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        httpClient.connectTimeout(120, TimeUnit.SECONDS);
        httpClient.writeTimeout(120, TimeUnit.SECONDS);
        httpClient.readTimeout(120, TimeUnit.SECONDS);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        service = retrofit.create(CoingeckoApiService.class);
    }

    public void getAllCryptosDao(final ResultListener<CryptocurrencyModel> daoListener) {
        //TODO revisar las opciones de cada parametro y hacerlo parametrizable por el usuario
        Call<CryptocurrencyModel> call = service.getAllCryptos("USD", "market_cap_desc", 1250, false);

        call.enqueue(new Callback<CryptocurrencyModel>() {
            @Override
            public void onResponse(@NonNull Call<CryptocurrencyModel> call, @NonNull Response<CryptocurrencyModel> response) {
                CryptocurrencyModel results = response.body();
                daoListener.onFinish(results);
            }

            @Override
            public void onFailure(@NonNull Call<CryptocurrencyModel> call, @NonNull Throwable t) {
                daoListener.onFailure(t.getMessage());
            }
        });
    }
}
