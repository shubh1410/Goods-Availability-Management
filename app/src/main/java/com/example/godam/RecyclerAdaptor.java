package com.example.godam;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.godam.R;
import com.example.godam.Utils.Item_new;

import java.util.List;

public class RecyclerAdaptor extends RecyclerView.Adapter<RecyclerAdaptor.MyViewHolder> {

    private List<Item_new> item_List;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView product_name, description, quantity;

        public MyViewHolder(View view) {
            super(view);
            product_name = (TextView) view.findViewById(R.id.product_name);
            description = (TextView) view.findViewById(R.id.description);
            quantity = (TextView) view.findViewById(R.id.quantity);
        }
    }

    public RecyclerAdaptor(List<Item_new> item_List) {
        this.item_List = item_List;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_recycler_view, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        Item_new temp_item = item_List.get(position);
        myViewHolder.product_name.setText(temp_item.getProduct_name());
        myViewHolder.description.setText(temp_item.getProduct_desc());
        myViewHolder.quantity.setText(temp_item.getProduct_quantity());

    }


    public int getItemCount() {
        return item_List.size();
    }
}
