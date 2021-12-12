package com.example.myapplication;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAddReminders extends RecyclerView.Adapter<ListAddReminders.ViewHolder> {

    private List<List_element_reminders> list_data;
    private LayoutInflater inflater;
    private Context context;
    final ListAddReminders.OnItemLongClickListener listener;

    public interface OnItemLongClickListener {
        void onItemClick(List_element_reminders item);
    }

    public ListAddReminders(List<List_element_reminders> items_list, Context context,
                            ListAddReminders.OnItemLongClickListener listener) {

        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.list_data = items_list;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }


    public ListAddReminders.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_element, null);
        return new ListAddReminders.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAddReminders.ViewHolder holder, final int position) {
        holder.bindData(list_data.get(position));
    }

    public void setItems(List<List_element_reminders> items) {
        list_data = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iconImage;
        TextView name, description, status;

        ViewHolder(View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconTask);
            name = itemView.findViewById(R.id.name_task);
            description = itemView.findViewById(R.id.task_description);
            status = itemView.findViewById(R.id.task_status);

        }

        public void bindData(final List_element_reminders item) {

            name.setText(item.getName_reminder());
            description.setText(item.getDescription_reminder());

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Prueba");
                    builder.setMessage("Mensaje");
                    builder.setPositiveButton("Aceptar", null);

                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return false;
                }
            });

            //<<----------------------------- assign status ------------------------------>>

            if (item.getStatus_reminder()) {
                status.setText("Active");
            } else {
                status.setText("Inactive");
            }

            //<<----------------------------- assign image ------------------------------>>

            switch (item.getType_reminder()) {

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

        //<<-------------------------------- assign status text----------------------------->>

        public void change_status(final List_element_reminders item) {
            if (item.getStatus_reminder()) {
                status.setText("Active");
            } else {
                status.setText("Inactive");
            }
        }

    }


}
