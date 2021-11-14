package com.projectz.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView listView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        fab = findViewById(R.id.floatingActionButton);
        listView = findViewById(R.id.list);
        imageView = findViewById(R.id.imgInfo);
        imageView.setOnClickListener(v -> Toast.makeText(getApplicationContext(), "Hold the todo you want to remove!!", Toast.LENGTH_SHORT).show());

        fab.setOnClickListener(v -> AddTask());
        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this,R.layout.list,R.id.txtTask,items);
        listView.setAdapter(itemsAdapter);
        listView.setDivider(null);
        DeleteTask();
    }

    private void DeleteTask() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Todo Removed", Toast.LENGTH_LONG).show();
                items.remove(position);
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    private void AddTask() {

        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(R.layout.input_file, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(this).create();
        deleteDialog.setView(deleteDialogView);

        deleteDialogView.findViewById(R.id.saveBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = deleteDialogView.findViewById(R.id.task);
                String itemText = input.getText().toString();
                if(!(itemText.equals(""))){
                    itemsAdapter.add(itemText);
                    input.setText("");
                    deleteDialog.dismiss();
                   // Toast.makeText(getApplicationContext(), "Long press on todo to remove", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Please enter your task", Toast.LENGTH_LONG).show();
                }

            }
        });
        deleteDialogView.findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog.dismiss();
            }
        });

        deleteDialog.show();
    }

}