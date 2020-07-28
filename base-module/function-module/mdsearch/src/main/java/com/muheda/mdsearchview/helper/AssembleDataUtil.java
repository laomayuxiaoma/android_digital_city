package com.muheda.mdsearchview.helper;

import com.muheda.mdsearchview.model.SearchItem;
import com.muheda.mdsearchview.model.SearchModelDto;

import java.util.ArrayList;
import java.util.List;


/**
 * @author wangfei
 * @date 2019/8/2.
 * 数据拼装组合
 */
public class AssembleDataUtil {

    private static List<SearchItem> datas = new ArrayList<>();
    public static String HISTORY_RECORD = "0";

    public static List<SearchItem> getViews(List<SearchItem> list, SearchModelDto searchDataDto) {
        datas.clear();
        if (searchDataDto != null) {
            datas.add(new SearchItem(HISTORY_RECORD, searchDataDto));
        }
        if (list != null && 0 != list.size()) {
            datas.addAll(list);
        }
        return datas;
    }
}
