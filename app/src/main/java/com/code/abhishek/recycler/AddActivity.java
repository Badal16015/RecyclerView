package com.code.abhishek.recycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class AddActivity extends AppCompatActivity {
    EditText name;
    EditText detail;
    Button add;
    Student stt;
    //StudentLab slab;
    //public static ArrayList<Student> data=new ArrayList<>();
    //RecyclerFragment.StudentAdapter adp;
   // public RecyclerFragment.StudentAdapter adpt;
    DatabaseHelper helper;

    //private RecyclerView recycle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
      //slab=new StudentLab(this);
        //recycle=(RecyclerView)findViewById(R.id.recycler_view);
        name=(EditText)findViewById(R.id.editText);
        detail=(EditText)findViewById(R.id.editText2);
        add=(Button)findViewById(R.id.button);
     //   adpt=new RecyclerFragment.StudentAdapter(this);
        stt=new Student();
        helper=new DatabaseHelper(this);
         add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String studentname=name.getText().toString();
            String studentdetail=detail.getText().toString();
            if(!studentname.equals("") && !studentdetail.equals("")){
                helper.insertIntoDB(studentname,studentdetail);
                /*stt.setTitle(studentname);
                stt.setDescribe(studentdetail);
                  ArrayList<Student> arr= (ArrayList<Student>) slab.getData();
                  arr.add(stt);*/
                Toast.makeText(getApplicationContext(),"Item added",Toast.LENGTH_LONG);

            }
        }
    });

    }
}
