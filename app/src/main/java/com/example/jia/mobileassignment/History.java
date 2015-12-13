package com.example.jia.mobileassignment;

/**
 * Created by Ladious on 13/12/2015.
 */
public class History {
    private String order_no;
    private String date;
    private String product_name;
    private String product_qty;
    private String id;

    public History(){};

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_qty() {
        return product_qty;
    }

    public void setProduct_qty(String product_qty) {
        this.product_qty = product_qty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public History(String order_no, String date, String product_name, String product_qty, String id){
        this.order_no = order_no;
        this.date = date;
        this.product_name = product_name;
        this.product_qty = product_qty;
        this.id = id;
    }

}
