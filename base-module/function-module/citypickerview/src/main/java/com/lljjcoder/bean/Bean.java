package com.lljjcoder.bean;

import java.util.List;

/**
 * Created by liguoying on 2017/11/27.
 */

public class Bean {

    /**
     * id : 4521984
     * name : 北京市
     * children : [{"id":4521985,"name":"北京市","parentId":4521984,"children":[{"id":4521986,"name":"东城区","parentId":4521985},{"id":4521987,"name":"西城区","parentId":4521985},{"id":4521988,"name":"朝阳区","parentId":4521985},{"id":4521989,"name":"丰台区","parentId":4521985},{"id":4521990,"name":"石景山区","parentId":4521985},{"id":4521991,"name":"海淀区","parentId":4521985},{"id":4521992,"name":"门头沟区","parentId":4521985},{"id":4521993,"name":"房山区","parentId":4521985},{"id":4521994,"name":"通州区","parentId":4521985},{"id":4521995,"name":"顺义区","parentId":4521985},{"id":4521996,"name":"昌平区","parentId":4521985},{"id":4521997,"name":"大兴区","parentId":4521985},{"id":4521998,"name":"怀柔区","parentId":4521985},{"id":4521999,"name":"平谷区","parentId":4521985},{"id":4522001,"name":"密云县","parentId":4521985},{"id":4522002,"name":"延庆县","parentId":4521985}]}]
     */

    private int id;
    private String name;
    private List<ChildrenBeanX> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChildrenBeanX> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBeanX> children) {
        this.children = children;
    }

    public static class ChildrenBeanX {
        /**
         * id : 4521985
         * name : 北京市
         * parentId : 4521984
         * children : [{"id":4521986,"name":"东城区","parentId":4521985},{"id":4521987,"name":"西城区","parentId":4521985},{"id":4521988,"name":"朝阳区","parentId":4521985},{"id":4521989,"name":"丰台区","parentId":4521985},{"id":4521990,"name":"石景山区","parentId":4521985},{"id":4521991,"name":"海淀区","parentId":4521985},{"id":4521992,"name":"门头沟区","parentId":4521985},{"id":4521993,"name":"房山区","parentId":4521985},{"id":4521994,"name":"通州区","parentId":4521985},{"id":4521995,"name":"顺义区","parentId":4521985},{"id":4521996,"name":"昌平区","parentId":4521985},{"id":4521997,"name":"大兴区","parentId":4521985},{"id":4521998,"name":"怀柔区","parentId":4521985},{"id":4521999,"name":"平谷区","parentId":4521985},{"id":4522001,"name":"密云县","parentId":4521985},{"id":4522002,"name":"延庆县","parentId":4521985}]
         */

        private int id;
        private String name;
        private int parentId;
        private List<ChildrenBean> children;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean {
            /**
             * id : 4521986
             * name : 东城区
             * parentId : 4521985
             */

            private int id;
            private String name;
            private int parentId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }
        }
    }
}
