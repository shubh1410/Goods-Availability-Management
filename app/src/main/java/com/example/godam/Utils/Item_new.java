package com.example.godam.Utils;

public class Item_new {
    private String product_id;
    private String product_name;
    private String product_brand;
    private String model_number;
    private String product_desc;
    private int product_quantity;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public Item_new(String product_id, String product_name, String product_brand, String model_number, String product_desc, int product_quantity) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_brand = product_brand;
        this.model_number = model_number;
        this.product_desc = product_desc;
        this.product_quantity = product_quantity;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_brand() {
        return product_brand;
    }

    public void setProduct_brand(String product_brand) {
        this.product_brand = product_brand;
    }

    public String getModel_number() {
        return model_number;
    }

    public void setModel_number(String model_number) {
        this.model_number = model_number;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }
}