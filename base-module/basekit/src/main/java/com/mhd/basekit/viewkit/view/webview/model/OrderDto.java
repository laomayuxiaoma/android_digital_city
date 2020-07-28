package com.mhd.basekit.viewkit.view.webview.model;

import com.mhd.basekit.viewkit.util.Common;
import com.mhd.basekit.viewkit.view.webview.util.JsDto;

import java.io.Serializable;

/**
 * @author zhangming
 * @Date 2019/5/8 11:40
 * @Description: 立即购买订单
 */
public class OrderDto extends JsDto implements Serializable {

    //0 普通商品无积分
    //1 普通积分商品
    //2 普通数据商品
    //3 养修安行商品
    public static final int COMMON_GOODS_NO_INTEGRAL = 0;
    public static final int COMMON_GOODS_INTEGRAL = 1;
    public static final int DATA_GOODS = 2;
    public static final int REPAIR_GOODS = 3;

    private String setName;
    private String name;
    private String key;
    private int type;
    private OrderShopDto value;
    private String token;
    private String routerName;
    private OrderInforForCashierDeskBean orderInforForCashierDesk;

    public OrderInforForCashierDeskBean getOrderInforForCashierDesk() {
        return orderInforForCashierDesk;
    }

    public void setOrderInforForCashierDesk(OrderInforForCashierDeskBean orderInforForCashierDesk) {
        this.orderInforForCashierDesk = orderInforForCashierDesk;
    }


    public String getOrderIdForInvoice() {
        return orderIdForInvoice;
    }

    public void setOrderIdForInvoice(String orderIdForInvoice) {
        this.orderIdForInvoice = orderIdForInvoice;
    }

    private String orderIdForInvoice;

    public OrderShopDto getValue() {
        return value;
    }

    public void setValue(OrderShopDto value) {
        this.value = value;
    }

    public String getRouterName() {
        return routerName;
    }

    public void setRouterName(String routerName) {
        this.routerName = routerName;
    }


    @Override
    protected void initMethod() {
        this.method = "initVueApp";
    }

    public void init() {
        setName = "shopGoodsCartIdsFn";
        name = "shopGoodsCartIds";
        key = "shopData";
        type = COMMON_GOODS_NO_INTEGRAL;
        token = Common.getToken();
    }

    public static class OrderShopDto implements Serializable {
        private String id;
        private String count;
        private String gsp;
        private String hardwareTypeId;
        private String servicePackageId;
        private String t;

        public OrderShopDto(String id, String count, String gsp, String hardwareTypeId, String servicePackageId, String t) {
            this.id = id;
            this.count = count;
            this.gsp = gsp;
            this.hardwareTypeId = hardwareTypeId;
            this.servicePackageId = servicePackageId;
            this.t = t;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getGsp() {
            return gsp;
        }

        public void setGsp(String gsp) {
            this.gsp = gsp;
        }

        public String getHardwareTypeId() {
            return hardwareTypeId;
        }

        public void setHardwareTypeId(String hardwareTypeId) {
            this.hardwareTypeId = hardwareTypeId;
        }

        public String getServicePackageId() {
            return servicePackageId;
        }

        public void setServicePackageId(String servicePackageId) {
            this.servicePackageId = servicePackageId;
        }

        public String getT() {
            return t;
        }

        public void setT(String t) {
            this.t = t;
        }
    }

    public static class OrderInforForCashierDeskBean implements Serializable{
        private String orderNo;
        private String orderTotalPrice;

        public OrderInforForCashierDeskBean(String orderNo, String orderTotalPrice) {
            this.orderNo = orderNo;
            this.orderTotalPrice = orderTotalPrice;
        }
    }
}
