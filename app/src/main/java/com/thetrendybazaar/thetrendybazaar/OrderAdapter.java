package com.thetrendybazaar.thetrendybazaar;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {

    LayoutInflater inflater;
    List<Order> orders;

    OrderAdapter(Context context ){
        inflater = LayoutInflater.from(context);
        orders = DatabaseManager.orders.getOrders();
        Log.d("EFE","SFE");
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.orders_item_layout, parent, false);
        OrderViewHolder orderViewHolder = new OrderViewHolder(view);
        return orderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.orderNum.setText("ORDER NUMBER : " + order.orderNumber);
        holder.customerId.setText("CUSTOMER ID : " + DatabaseManager
                .places.getCustomerId(order.orderNumber));
        int shipped = DatabaseManager.sentFor.checkShipped(order.orderNumber);
        if(shipped == 1){
            holder.shipBtn.setEnabled(false);
            holder.shipBtn.setText("SHIPPED");
            holder.shipBtn.setBackgroundColor(Color.parseColor("#61C23F"));
        }

        holder.shipBtn.setOnClickListener(e -> {
            DatabaseManager.sentFor.add(order, order.address);
            holder.shipBtn.setEnabled(false);
            holder.shipBtn.setText("SHIPPED");
            holder.shipBtn.setBackgroundColor(Color.parseColor("#61C23F"));
        });

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}

class OrderViewHolder extends RecyclerView.ViewHolder{

    TextView orderNum;
    TextView customerId;
    Button shipBtn;

    public OrderViewHolder(View itemView) {
        super(itemView);

        orderNum = itemView.findViewById(R.id.order_item_order_number);
        customerId = itemView.findViewById(R.id.order_item_customer_id);
        shipBtn = itemView.findViewById(R.id.ship_button);

    }
}