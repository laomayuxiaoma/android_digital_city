package com.muheda.mhdsystemkit.systemUI.conView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13660 on 2019/4/22.
 */

public abstract class BaseModel {

    public List<TagDto> mList=new ArrayList<>();
    public abstract List<TagDto> getList();

}
