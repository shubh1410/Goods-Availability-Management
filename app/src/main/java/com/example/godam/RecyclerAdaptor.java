package com.example.godam;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.godam.R;
import com.example.godam.Utils.Item_new;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RecyclerAdaptor extends RecyclerView.Adapter<RecyclerAdaptor.MyViewHolder> {

    private List<Item_new> item_List;
    private int viewType;
    private static final String TAG = "RecyclerAdaptor";


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView product_name, description, quantity;
        public Button plus, minus, delete;
        public Item_new temp_item;

        public MyViewHolder(View view){
            super(view);


            product_name = (TextView) view.findViewById(R.id.product_name);
            description = (TextView) view.findViewById(R.id.description);
            quantity = (TextView) view.findViewById(R.id.quantity);
            plus = (Button) view.findViewById(R.id.plus);
            minus = (Button) view.findViewById(R.id.minus);
            delete=(Button) view.findViewById(R.id.delete_button);





        }
        public void onClick(View v) {

            if (v.getId() == plus.getId()){

                /*FirebaseDatabase database;
                DatabaseReference databaseReference;
                database = FirebaseDatabase.getInstance();
                databaseReference = database.getReference();
                databaseReference.keepSynced(true);
                int test_quantity = Integer.parseInt(temp_item.getProduct_quantity());
                test_quantity = test_quantity + 1;
                temp_item.setProduct_quantity(Integer.toString(test_quantity));
                databaseReference.child("Product_info").child(temp_item.getProduct_category()).child(temp_item.getFb_key()).setValue(temp_item);
                Log.d(TAG, "onClick: Plus" + temp_item.toString());
*/
                int test_quantity = Integer.parseInt(temp_item.getProduct_quantity());
                test_quantity = test_quantity + 1;
                temp_item.setProduct_quantity(Integer.toString(test_quantity));
                quantity.setText(Integer.toString(test_quantity));

                FirebaseDatabase database;
                DatabaseReference databaseReference;
                database = FirebaseDatabase.getInstance();
                databaseReference = database.getReference();
                databaseReference.keepSynced(true);
                //databaseReference.child("Product_info").child(temp_item.getProduct_category()).child(temp_item.getFb_key()).setValue(temp_item);

            } else if(v.getId() == minus.getId()) {

                View tempview = (View) minus.getTag(R.integer.btn_minus_view);
                FirebaseDatabase database;
                DatabaseReference databaseReference;
                database = FirebaseDatabase.getInstance();
                databaseReference = database.getReference();
                databaseReference.keepSynced(true);
                int test_quantity = Integer.parseInt(temp_item.getProduct_quantity());
                if (test_quantity == 0) {
                    //quantity=(TextView) tempview.findViewById(R.id.quantity)
                    quantity.setError("Quantity is already Zero..");
                   // Toast.makeText(RecyclerAdaptor.this,"fghj",Toast.LENGTH_SHORT).show();
                } else {
                    test_quantity = test_quantity - 1;
                    temp_item.setProduct_quantity(Integer.toString(test_quantity));
                    databaseReference.child("Product_info").child(temp_item.getProduct_category()).child(temp_item.getFb_key()).setValue(temp_item);
                    Log.d(TAG, "onClick: Plus" + temp_item.toString());
                }

            } else if(v.getId() == delete.getId()) {

                View tempview = (View) delete.getTag(R.integer.btn_delete_view);
                FirebaseDatabase database;
                DatabaseReference databaseReference;
                database = FirebaseDatabase.getInstance();
                databaseReference = database.getReference();
                databaseReference.keepSynced(true);
                int test_quantity = Integer.parseInt(temp_item.getProduct_quantity());
                if (test_quantity != 0) {
                    quantity.setError("Quantity Must Be Zero..");
                    Log.d(TAG, "onClick: Delete must not delete");

                } else {
                    //temp_item.setProduct_quantity(Integer.toString(test_quantity));
                    databaseReference.child("Product_info").child(temp_item.getProduct_category()).child(temp_item.getFb_key()).removeValue();
                    Log.d(TAG, "onClick:Delete must delete");

                }

            }
        }
    }

    public RecyclerAdaptor(List<Item_new> item_List, int viewType) {
        this.item_List = item_List;
        this.viewType = viewType;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_recycler_view, viewGroup, false);
        switch (viewType) {
            case 1:
                itemView = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.layout_recycler_view, viewGroup, false);
                Log.d(TAG, "onCreateViewHolder: layout_recycler_view");
                break;

            case 2:
                itemView = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.layout_recycler_upquantity, viewGroup, false);
                Log.d(TAG, "onCreateViewHolder: layout_recycler_upquantity");
                break;

            case 3:
                itemView = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.layout_recycler_delete, viewGroup, false);
                Log.d(TAG, "onCreateViewHolder: layout_recycler_delete");
                break;


        }
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int position) {
        myViewHolder.temp_item = item_List.get(position);

        switch (viewType) {
            case 1:
                myViewHolder.product_name.setText(myViewHolder.temp_item.getProduct_name());
                myViewHolder.description.setText(myViewHolder.temp_item.getProduct_desc());
                myViewHolder.quantity.setText(myViewHolder.temp_item.getProduct_quantity());

                break;
            case 2:
                myViewHolder.product_name.setText(myViewHolder.temp_item.getProduct_name());
                myViewHolder.description.setText(myViewHolder.temp_item.getProduct_desc());
                myViewHolder.quantity.setText(myViewHolder.temp_item.getProduct_quantity());
                myViewHolder.plus.setTag(R.integer.btn_plus_view, myViewHolder);
                myViewHolder.minus.setTag(R.integer.btn_minus_view, myViewHolder);
                myViewHolder.plus.setOnClickListener(myViewHolder);
                myViewHolder.minus.setOnClickListener(myViewHolder);
                break;

            case 3:
                myViewHolder.product_name.setText(myViewHolder.temp_item.getProduct_name());
                myViewHolder.description.setText(myViewHolder.temp_item.getProduct_desc());
                myViewHolder.quantity.setText(myViewHolder.temp_item.getProduct_quantity());
                myViewHolder.delete.setTag(R.integer.btn_delete_view, myViewHolder);
                myViewHolder.delete.setOnClickListener(myViewHolder);
                break;
        }

//                myViewHolder.product_name.setText(myViewHolder.temp_item.getProduct_name());
//                myViewHolder.description.setText(myViewHolder.temp_item.getProduct_desc());
//                myViewHolder.quantity.setText(myViewHolder.temp_item.getProduct_quantity());
//                myViewHolder.plus.setTag(R.integer.btn_plus_view, myViewHolder);
//                myViewHolder.minus.setTag(R.integer.btn_minus_view, myViewHolder);
//                myViewHolder.plus.setOnClickListener(myViewHolder);
//                myViewHolder.minus.setOnClickListener(myViewHolder);
//                myViewHolder.delete.setTag(R.integer.btn_delete_view, myViewHolder);
//                myViewHolder.delete.setOnClickListener(myViewHolder);


    }


    public int getItemCount() {
        return item_List.size();
    }

    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
 //       Log.w("dont know", item_List.get(position).getClass().toString());
//        switch (item_List.get(position).getClass().toString()) {
//            case 0:
//                return Model.TEXT_TYPE;
//            case 1:
//                return Model.IMAGE_TYPE;
//            case 2:
//                return Model.AUDIO_TYPE;
//            default:
//                return -1;
//        }
        if(position!= 0)
            return position;
        else
            return 2;
    }

}
