package com.portfolio.blockchain.controller;

import com.portfolio.blockchain.ResultListener;
import com.portfolio.blockchain.dao.CryptoDao;
import com.portfolio.blockchain.model.CryptocurrencyModel;

public class CryptoController {

    private CryptoDao cryptoDao;

    public CryptoController() {
        cryptoDao = new CryptoDao();
    }

    public void getAllCryptosController(final ResultListener<CryptocurrencyModel> controllerListener) {
        cryptoDao.getAllCryptosDao(new ResultListener<CryptocurrencyModel>() {
            @Override
            public void onFinish(CryptocurrencyModel results) {
                controllerListener.onFinish(results);
            }

            @Override
            public void onFailure(String errorMessage) {
                controllerListener.onFailure(errorMessage);
            }
        });
    }
}
