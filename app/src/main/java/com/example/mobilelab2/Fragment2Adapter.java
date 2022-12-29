package com.example.mobilelab2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Fragment2Adapter extends RecyclerView.Adapter<Fragment2Adapter.ViewHolder> {

    private List<Student> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    Fragment2Adapter(Context context, List<Student> data){
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Student student = mData.get(position);
        String student_name = student.name;
        String student_mark = student.mark;
        holder.nameTextView.setText(student_name);
        holder.markTextView.setText(student_mark);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTextView;
        TextView markTextView;

        ViewHolder(View itemView){
            super(itemView);
            nameTextView = itemView.findViewById(R.id.rv_item_name);
            markTextView = itemView.findViewById(R.id.rv_item_mark);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if(mClickListener != null) mClickListener.onItemClick(view, getAbsoluteAdapterPosition());
        }
    }
    void setmClickListener(ItemClickListener itemClickListener){
        this.mClickListener = itemClickListener;
    }

    public interface  ItemClickListener{
        void onItemClick(View view, int position);
    }
}