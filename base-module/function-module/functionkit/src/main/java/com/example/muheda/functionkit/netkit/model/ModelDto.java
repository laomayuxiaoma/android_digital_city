package com.example.muheda.functionkit.netkit.model;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by 13660 on 2018/10/19.
 */

public abstract class ModelDto {
    private String TAG="xinnengyuan";
    private String success;
    private String code;
    private String message;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public ModelDto toJson(String jsonString,boolean is){
        if (is){
            return  new Gson().fromJson(jsonString,this.getClass());
        }else{
            JSONObject jsonObject= null;
            try {
                jsonObject = new JSONObject(jsonString);
                ModelDto modelDto=toJsonDto(jsonObject.optString("data"));
                modelDto.setSuccess(jsonObject.getString("success"));
                modelDto.setMessage(jsonObject.getString("message"));
                modelDto.setCode(jsonObject.getString("code"));
                return modelDto;
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG,"数据Json解析异常"+e.toString());
            }

            return this;
        }

    }
    public abstract ModelDto toJsonDto(String jsonString);
}
