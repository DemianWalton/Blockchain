package com.portfolio.blockchain.api;

import com.portfolio.blockchain.model.CryptocurrencyModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CoingeckoApiService {

    /*https://www.coingecko.com/es/api/documentation*/

    @GET("coins/markets?vs_currency=USD&order=market_cap_desc&per_page=1&sparkline=false")
    Call<CryptocurrencyModel> getAllCryptos(@Query("vs_currency") String currency,
                                            @Query("order") String order,
                                            @Query("per_page") Integer page,
                                            @Query("sparkline") boolean sparkline);
}
