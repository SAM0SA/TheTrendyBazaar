package com.thetrendybazaar.thetrendybazaar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemViewHolder> {

    LayoutInflater inflater;
    List<Item> items;
    TextView emptyCartText;
    boolean isDashboard;


    CartItemAdapter(Context context, TextView emptyCartText, boolean isDashboard){
        inflater = LayoutInflater.from(context);
        if(isDashboard){
            items = DatabaseManager.items.getItems(0);
        }else{
            items = DatabaseManager.contains
                    .getItemsForShoppingCart(ShoppingCart.currentShoppingCardId);
        }

        Log.d("WDW", "wd");
        this.emptyCartText = emptyCartText;
        this.isDashboard = isDashboard;
    }
    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cart_item_layout, parent, false);
        CartItemViewHolder cartItemViewHolder = new CartItemViewHolder(view);
        return cartItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        Item item = items.get(position);
        holder.itemName.setText(item.name);
        holder.itemPrice.setText("$"+item.price);
        holder.itemPicture.setImageResource(ImageAdapter.getImageResources(item.articleId)[0]);
        if(isDashboard){
            holder.quantity.setVisibility(View.VISIBLE);
            holder.updateBtn.setVisibility(View.VISIBLE);
            holder.updateBtn.setOnClickListener(e -> {
                int prevQuantity = item.quantity;
                item.quantity = Integer.parseInt(holder.quantity.getText().toString());
                DatabaseManager.items.update(item);
                holder.itemQuantity.setText("Current Quantity: " + item.quantity);
                DatabaseManager.updates.add(null, null, item,
                        DatabaseManager.employees.select(Employee.currentEmployeeId),
                        prevQuantity);
            });
            holder.itemQuantity.setText("Current Quantity: " + item.quantity);
            holder.itemQuantity.setVisibility(View.VISIBLE);
            holder.itemTotalPrice.setVisibility(View.INVISIBLE);
            holder.itemCross.setOnClickListener(e -> {
                items.remove(item);
                DatabaseManager.items.delete(item);
                DatabaseManager.supplies.delete(item);
                this.notifyDataSetChanged();
                //if(items.size() == 0) emptyCartText.setVisibility(View.VISIBLE);
            });

            if(position == items.size()-1)
                holder.divider.setVisibility(View.GONE);
        }else {
            int quantity = DatabaseManager
                    .contains.getQuantityInCart(ShoppingCart.currentShoppingCardId, item.articleId);
            holder.itemQuantity.setText("" + quantity);
            holder.itemTotalPrice.setText("$" + (quantity*item.price));
            holder.itemCross.setOnClickListener(e -> {
                items.remove(item);
                int quantityOfItem = DatabaseManager.contains.getQuantityInCart(ShoppingCart.currentShoppingCardId, item.articleId);
                DatabaseManager.items.alterQuantity(item.articleId, quantityOfItem);
                DatabaseManager.contains.delete(item.articleId, ShoppingCart.currentShoppingCardId);
                this.notifyDataSetChanged();
                if(items.size() == 0) emptyCartText.setVisibility(View.VISIBLE);
            });
            if(position == items.size()-1)
                holder.divider.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class CartItemViewHolder extends RecyclerView.ViewHolder{

    ImageView itemPicture;
    TextView itemName;
    TextView itemPrice;
    TextView itemTotalPrice;
    TextView itemQuantity;
    TextView itemCross;
    View divider;
    EditText quantity;
    Button updateBtn;


    public CartItemViewHolder(View itemView) {
        super(itemView);

        itemPicture = itemView.findViewById(R.id.cart_item_image);
        itemName = itemView.findViewById(R.id.cartItemName);
        itemPrice = itemView.findViewById(R.id.cart_item_price);
        itemQuantity = itemView.findViewById(R.id.cart_item_quantity);
        itemCross = itemView.findViewById(R.id.cart_item_x);
        divider = itemView.findViewById(R.id.cart_item_divider);
        itemTotalPrice = itemView.findViewById(R.id.cart_item_total);
        quantity = itemView.findViewById(R.id.item_dash_quanitiy);
        updateBtn = itemView.findViewById(R.id.dash_update_button);
    }
}
