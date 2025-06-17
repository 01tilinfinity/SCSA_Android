package com.scsa.goatsaeng;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodoActivity extends AppCompatActivity {

    private EditText editText;
    private Button btnAdd;
    private RecyclerView recyclerView;
    private TodoAdapter adapter;
    private DBHelper dbHelper;
    private List<TodoItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        editText = findViewById(R.id.editText);
        btnAdd = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.recyclerView);

        dbHelper = new DBHelper(this);
        list = dbHelper.getAllTodos();

        adapter = new TodoAdapter(list, dbHelper);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editText.getText().toString();
                if (!content.isEmpty()) {
                    dbHelper.insertTodo(content);
                    list.clear();
                    list.addAll(dbHelper.getAllTodos());
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                }
            }
        });
    }
}
