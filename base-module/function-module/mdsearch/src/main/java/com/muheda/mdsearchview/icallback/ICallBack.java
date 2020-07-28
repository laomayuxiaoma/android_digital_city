package com.muheda.mdsearchview.icallback;

import com.muheda.mdsearchview.model.MySearchDto;

/**
 * Created by Carson_Ho on 17/8/10.
 */

public interface ICallBack {
    //    void SearchAciton(String string,Boolean isItem,Boolean isFuzzy,int id);
    void SearchAciton(MySearchDto.SearchDto.DataBean dataBean);
}
