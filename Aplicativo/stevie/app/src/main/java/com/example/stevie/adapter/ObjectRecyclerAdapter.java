package com.example.stevie.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.stevie.R;

import org.w3c.dom.Text;

public class ObjectRecyclerAdapter extends RecyclerView.Adapter<ObjectRecyclerAdapter.MyViewHolder> {

    private String[] objectList;
    private ChooseOptionInterface chooseOptionInterface;

    public ObjectRecyclerAdapter(String[] list, ChooseOptionInterface chooseOptionInterface) {
        objectList = list;
        this.chooseOptionInterface = chooseOptionInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.object_recycler_item, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String objectName = objectList[position];
        holder.bind(objectName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseOptionInterface.onOptionClicked(objectName);
            }
        });
    }

    @Override
    public int getItemCount() {
        return objectList.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView objectName;

        MyViewHolder(View v) {
            super(v);
            objectName = v.findViewById(R.id.objectName);
        }

        void bind(String name) {
            objectName.setText(name);
        }
    }

    public interface ChooseOptionInterface {
        void onOptionClicked(String name);
    }
}
