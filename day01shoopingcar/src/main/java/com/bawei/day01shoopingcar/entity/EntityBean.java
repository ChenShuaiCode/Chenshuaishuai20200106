package com.bawei.day01shoopingcar.entity;

import java.util.List;

/**
 * @author: asus
 * @date: 20/1/6
 */
public class EntityBean {
    public String message;
    public String status;

    public List<Cart> result;

    public static class Cart{
        public boolean isCartChecked;//标示，标志位
        public String categoryName;
        public List<Product> shoppingCartList;

        public static class Product{
            public boolean isProductChecked;
            public String commodityId;
            public String commodityName;
            public String count;
            public String pic;
            public double price;
        }


    }
}
