package com.example.muheda.functionkit.netkit.params;

import com.example.muheda.functionkit.netkit.model.ModelDto;
import com.zhouyou.http.model.HttpParams;

/**
 * Created by 13660 on 2018/10/19.
 */

public class HttpNewParams extends HttpParams {
    private Class<? extends ModelDto> modleClass;

    public Class<? extends ModelDto> getModleClass() {
        return modleClass;
    }

    public void setModleClass(Class<? extends ModelDto> modleClass) {
        this.modleClass = modleClass;
    }
}
