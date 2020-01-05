package com.machamasisuraj.foodland.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.machamasisuraj.foodland.Model.Item;
import com.machamasisuraj.foodland.R;

import java.util.List;

public class ItemRecyclerAdapter extends  RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder> {

    private Context mContext;
    private List<Item> itemList;

    public ItemRecyclerAdapter(Context mContext, List<Item> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_itemview,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.textView.setText(item.getItemName());
        holder.textView2.setText("Rs. "+item.getPrice());
        holder.textView4.setText(item.getDetail());
        holder.textView5.setText(item.getResturant());


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class  ItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView, textView2,textView4,textView5;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.imageView);
            textView=itemView.findViewById(R.id.textView);
            textView2=itemView.findViewById(R.id.textView2);
            textView4=itemView.findViewById(R.id.textView4);
            textView5=itemView.findViewById(R.id.textView5);



        }
    }
}
