package com.example.myapplication;

import android.content.Context;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdd extends RecyclerView.Adapter<ListAdd.ViewHolder> {

    private List<List_element> list_data;
    private LayoutInflater inflater;
    private Context context;
    final ListAdd.OnItemLongClickListener listener;

    public interface OnItemLongClickListener{
        void onLongClick(List_element item);
    }

    public ListAdd(List<List_element> items_list, Context context, ListAdd.OnItemLongClickListener listener){

        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.list_data = items_list;
        this.listener = listener;
    }

    @Override
    public int getItemCount() { return list_data.size(); }


    public  ListAdd.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.list_element, null);
        return new ListAdd.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdd.ViewHolder holder, final int position){
        holder.bindData(list_data.get(position));
    }

    public void setItems(List<List_element> items) {
        list_data = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iconImage;
        TextView name, description, status;

        ViewHolder(View itemView){
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconTask);
            name = itemView.findViewById(R.id.name_task);
            description = itemView.findViewById(R.id.task_description);
            status = itemView.findViewById(R.id.task_status);

        }

        public void bindData (final List_element item){

            name.setText(item.getName());
            description.setText(item.getDescription());

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    Context context = v.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID",list_data.get(getBindingAdapterPosition()).getId());
                    context.startActivity(intent);

                    return true;
                }
            });




            //<<----------------------------- assign the status ------------------------------>>

            if(item.getStatus()){
                status.setText("Active");
            }else{
                status.setText("Inactive");
            }

            //<<----------------------------- assign the image ------------------------------>>

            switch (item.getType_task()){

                case "Urgente":
                    iconImage.setImageResource(R.drawable.urgente);
                    break;
                case "Deseable":
                    iconImage.setImageResource(R.drawable.deseable);
                    break;
                case "Prorrogable":
                    iconImage.setImageResource(R.drawable.prorrogable);
                    break;
            }

        }

        public void change_status(final List_element item){
            if(item.getStatus()){
                status.setText("Active");
            }else{
                status.setText("Inactive");
            }
        }

    }


}
