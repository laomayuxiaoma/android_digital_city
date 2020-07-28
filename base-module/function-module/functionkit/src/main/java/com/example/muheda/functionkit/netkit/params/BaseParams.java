package com.example.muheda.functionkit.netkit.params;

import com.zhouyou.http.EasyHttp;

import java.util.LinkedHashMap;

/**
 * Created by 13660 on 2018/10/22.
 */

public abstract class BaseParams {

    public ParamsCheck paramsCheck;

    public boolean getHttpNewParams(HttpNewParams httpNewParams) {
        paramsCheck = new ParamsCheck();

        return paramsCheck(httpNewParams.urlParamsMap);
    }

    public abstract boolean paramsCheck(LinkedHashMap<String, String> linkedHashMap);

    protected boolean key(LinkedHashMap<String, String> linkedHashMap, String[] paramsArray) {
        for (String keyString : paramsArray) {
            paramsCheck.with(String.valueOf(linkedHashMap.containsKey(keyString))).check(keyString + "参数匹配错误");
        }
        if (!paramsCheck.isPass(EasyHttp.getContext())) {
            return false;
        }
        return true;
    }

}
