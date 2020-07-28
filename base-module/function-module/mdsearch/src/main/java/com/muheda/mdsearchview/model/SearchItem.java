package com.muheda.mdsearchview.model;

/**
 * @author wangfei
 * @date 2019/7/29.
 */
public class SearchItem {

    private SearchModelDto searchDataDto;
    private String mViewTag;


    public String getmViewTag() {
        return mViewTag;
    }

    public void setmViewTag(String mViewTag) {
        this.mViewTag = mViewTag;
    }

    public SearchItem(String mViewTag, SearchModelDto searchDataDto) {
        this.searchDataDto = searchDataDto;
        this.mViewTag = mViewTag;
    }

    public SearchModelDto getSearchDataDto() {
        return searchDataDto;
    }

    public void setSearchDataDto(SearchModelDto searchDataDto) {
        this.searchDataDto = searchDataDto;
    }
}
