/*
 *  Copyright 2011 Yuri Kanivets
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.lljjcoder.citypickerview.widget.wheel.adapters;

import android.content.Context;

import com.lljjcoder.bean.NewCityBean;
import com.lljjcoder.bean.NewDistrictBean;
import com.lljjcoder.bean.NewProvinceBean;

/**
 * The simple Array wheel adapter
 *
 * @param <T> the element type
 */
public class ArrayWheelAdapter<T> extends AbstractWheelTextAdapter {

    // items
    private T items[];

    /**
     * Constructor
     *
     * @param context the current context
     * @param items   the items
     */
    public ArrayWheelAdapter(Context context, T items[]) {
        super(context);

        //setEmptyItemResource(TEXT_VIEW_ITEM_RESOURCE);
        this.items = items;
    }

    @Override
    public CharSequence getItemText(int index) {
        if (index >= 0 && index < items.length) {
            T item = items[index];
            if (item == null) {
                return "";
            }
            if (item instanceof CharSequence) {
                return (CharSequence) item;
            }
//            if (item instanceof NewDistrictBean) {
//                return ((NewDistrictBean) item).getName();
//            }
//            if (item instanceof NewCityBean) {
//                return ((NewCityBean) item).getName();
//            }
//            if (item instanceof NewProvinceBean) {
//                return ((NewProvinceBean) item).getName();
//            }
            return item.toString();
        }
        return null;
    }

    @Override
    public int getItemsCount() {
        return items.length;
    }
}
