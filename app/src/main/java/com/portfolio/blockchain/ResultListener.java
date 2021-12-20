package com.portfolio.blockchain;

public interface ResultListener<T> {

    void onFinish(T resultado);

    void onFailure(String failureMessage);

}
