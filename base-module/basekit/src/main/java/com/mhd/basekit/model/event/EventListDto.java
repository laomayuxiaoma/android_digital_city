package com.mhd.basekit.model.event;

import com.example.muheda.functionkit.netkit.model.ModelDto;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangfei
 * @date 2019/7/8.
 */
public class EventListDto extends ModelDto implements Serializable {
    @Override
    public ModelDto toJsonDto(String s) {
        return null;
    }

    private List<ItemBean> data;

    public List<ItemBean> getData() {
        return data;
    }

    public void setData(List<ItemBean> data) {
        this.data = data;
    }

    public class ItemBean implements Serializable{
        private String title;
        private List<DetailItemBean> itemList;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<DetailItemBean> getItemList() {
            return itemList;
        }

        public void setItemList(List<DetailItemBean> itemList) {
            this.itemList = itemList;
        }


        public class DetailItemBean implements Serializable{
            private String id;
            private boolean disabled;
            private String createTime;
            private String updateTime;
            private String userId;
            private String openStatus;
            private String eventName;
            private String eventType;
            private String typeName;
            private String sortNum;
            private String eventCode;

            public String getEventCode() {
                return eventCode;
            }

            public void setEventCode(String eventCode) {
                this.eventCode = eventCode;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public boolean isDisabled() {
                return disabled;
            }

            public void setDisabled(boolean disabled) {
                this.disabled = disabled;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getOpenStatus() {
                return openStatus;
            }

            public void setOpenStatus(String openStatus) {
                this.openStatus = openStatus;
            }

            public String getEventName() {
                return eventName;
            }

            public void setEventName(String eventName) {
                this.eventName = eventName;
            }

            public String getEventType() {
                return eventType;
            }

            public void setEventType(String eventType) {
                this.eventType = eventType;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public String getSortNum() {
                return sortNum;
            }

            public void setSortNum(String sortNum) {
                this.sortNum = sortNum;
            }
        }
    }
}
