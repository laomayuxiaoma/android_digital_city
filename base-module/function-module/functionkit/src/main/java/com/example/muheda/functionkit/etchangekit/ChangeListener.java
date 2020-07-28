package com.example.muheda.functionkit.etchangekit;

/**
 * Created by 13660 on 2018/10/9.
 */

public interface ChangeListener<T> {
    //监听范围内
    void onSkip(T t);
    //非监听范围
    void onNoSkip(T t);
}
