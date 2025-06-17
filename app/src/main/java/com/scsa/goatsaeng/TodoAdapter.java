package com.scsa.goatsaeng;

import android.app.AlertDialog;
import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private List<TodoItem> list;
    private DBHelper dbHelper;

    public TodoAdapter(List<TodoItem> list, DBHelper dbHelper) {
        this.list = list;
        this.dbHelper = dbHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.todo_item, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TodoItem item = list.get(position);
        holder.textView.setText(item.getContent());
        holder.checkBox.setChecked(item.isDone());

        holder.checkBox.setOnClickListener(v -> {
            if (holder.checkBox.isChecked()) {
                dbHelper.deleteTodo(item.getId());
                list.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, list.size());
            }
        });

        holder.btnEdit.setOnClickListener(v -> {
            EditText editText = new EditText(holder.context);
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
            editText.setText(item.getContent());

            new AlertDialog.Builder(holder.context)
                    .setTitle("수정")
                    .setView(editText)
                    .setPositiveButton("저장", (dialog, which) -> {
                        String newContent = editText.getText().toString();
                        dbHelper.updateTodo(item.getId(), newContent);
                        item.setContent(newContent);
                        notifyItemChanged(position);
                    })
                    .setNegativeButton("취소", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView textView;
        Button btnEdit;
        Context context;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            checkBox = itemView.findViewById(R.id.checkBox);
            textView = itemView.findViewById(R.id.textView);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }
    }
}
