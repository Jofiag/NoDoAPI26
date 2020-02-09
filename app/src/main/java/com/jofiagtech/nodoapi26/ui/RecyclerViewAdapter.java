package com.jofiagtech.nodoapi26.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jofiagtech.nodoapi26.R;
import com.jofiagtech.nodoapi26.model.NoDo;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<NoDo> mNoDoList;
    private LayoutInflater mLayoutInflater;

    public RecyclerViewAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        if (mNoDoList != null){
            NoDo noDo = mNoDoList.get(position);
            holder.noDoText.setText(noDo.getNoDo());
        }
        else
            holder.noDoText.setText(R.string.empty_text);
    }

    public void setNoDoList(List<NoDo> noDos) {
        mNoDoList = noDos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mNoDoList != null)
            return mNoDoList.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView noDoText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noDoText = itemView.findViewById(R.id.textView);
        }
    }
}
