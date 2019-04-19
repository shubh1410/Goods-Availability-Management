package com.example.godam.Utils;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;

public class Item_new implements Parcelable {
    private String product_category;
    private String product_name;
    private String product_brand;
    private String model_number;
    private String product_desc;
    private String product_quantity;
    private String fb_key;

    public Item_new(String product_category, String product_name, String product_brand, String model_number, String product_desc, String product_quantity, String fb_key) {
        this.product_category = product_category;
        this.product_name = product_name;
        this.product_brand = product_brand;
        this.model_number = model_number;
        this.product_desc = product_desc;
        this.product_quantity = product_quantity;
        this.fb_key = fb_key;
    }
    public Item_new(){}

    public Item_new(Parcel in){
        product_category = in.readString();
         product_name = in.readString();
         product_brand = in.readString();
         model_number = in.readString();
         product_desc = in.readString();
         product_quantity = in.readString();
         fb_key = in.readString();
    }

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
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

    public String getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(String product_quantity) {
        this.product_quantity = product_quantity;
    }

    public String getFb_key() {
        return fb_key;
    }

    public void setFb_key(String fb_key) {
        this.fb_key = fb_key;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        dest.writeString(product_category);
        dest.writeString(product_name);
        dest.writeString(product_brand);
        dest.writeString(model_number);
        dest.writeString(product_desc);
        dest.writeString(product_quantity);
        dest.writeString(fb_key);

    }

    public static final Parcelable.Creator<Item_new> CREATOR = new Parcelable.Creator<Item_new>(){
        public Item_new createFromParcel(Parcel in) {
            return new Item_new(in);
        }

        public Item_new[] newArray(int size) {
            return new Item_new[size];
        }
    };
    //    public Item_new(String product_category, String product_name, String product_brand, String model_number, String product_desc, String product_quantity) {
//        this.product_category = product_category;
//        this.product_name = product_name;
//        this.product_brand = product_brand;
//        this.model_number = model_number;
//        this.product_desc = product_desc;
//        this.product_quantity = product_quantity;
//    }
//    public Item_new(){}
//
//    @Override
//    public String toString() {
//        return "Item_new{" +
//                "product_category='" + product_category + '\'' +
//                ", product_name='" + product_name + '\'' +
//                ", product_brand='" + product_brand + '\'' +
//                ", model_number='" + model_number + '\'' +
//                ", product_desc='" + product_desc + '\'' +
//                ", product_quantity='" + product_quantity + '\'' +
//                '}';
//    }
//
//    public String getProduct_category() {
//        return product_category;
//    }
//
//    public void setProduct_category(String product_category) {
//        this.product_category = product_category;
//    }
//
//    public String getProduct_name() {
//        return product_name;
//    }
//
//    public void setProduct_name(String product_name) {
//        this.product_name = product_name;
//    }
//
//    public String getProduct_brand() {
//        return product_brand;
//    }
//
//    public void setProduct_brand(String product_brand) {
//        this.product_brand = product_brand;
//    }
//
//    public String getModel_number() {
//        return model_number;
//    }
//
//    public void setModel_number(String model_number) {
//        this.model_number = model_number;
//    }
//
//    public String getProduct_desc() {
//        return product_desc;
//    }
//
//    public void setProduct_desc(String product_desc) {
//        this.product_desc = product_desc;
//    }
//
//    public String getProduct_quantity() {
//        return product_quantity;
//    }
//
//    public void setProduct_quantity(String product_quantity) {
//        this.product_quantity = product_quantity;
//    }
}
