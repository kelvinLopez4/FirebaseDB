package com.example.owner.firebasedb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.LocaleDisplayNames;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuAdapter;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.android.gms.flags.impl.FlagProviderImpl;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class inicio extends AppCompatActivity {

    TextView depto;


    DatabaseHelper myDb;

    private static String Aqui;

    private DatabaseReference mDatabase;

    //private ListView mUserList;

    private final ArrayList<String> mUsernames = new ArrayList<>();

    private SwipeMenuListView mUserList;
   // private ListView mUserList;

  //  public  static final ArrayList<String> Valores = new ArrayList<String>();




    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);




        myDb = new DatabaseHelper(this);


        mDatabase = FirebaseDatabase.getInstance().getReference("Notification");
//        mUserList = findViewById(R.id.user_list);
        mUserList = findViewById(R.id.user_list);

        final  ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mUsernames);

        mUserList.setAdapter(arrayAdapter);






        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        mUserList.setMenuCreator(creator);

        mUserList.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                String item = mUsernames.get(position);
                switch (index) {
                    case 0:
                        // open
                        Log.d("delete", "onMenuItemClick: clicked item " + index +position);
                        String v =  mUsernames.get(position);

                        Cursor cur = myDb.getAllData();
                        while (cur.moveToNext()){

                            String j = cur.getString(0);
                            Log.d("BB: ", j);
                            if (v.contains(j)){

                                if(myDb.active(j))
                                {


                                    arrayAdapter.remove(mUsernames.remove(position));
                                    mUserList.setAdapter(arrayAdapter);
                                    arrayAdapter.notifyDataSetChanged();

                                    delete(item);


                                    Log.d("Aqui: ", j);

                                    Log.d("Desactivado: ", j);
                                }

                            }
                        }
                        break;

                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });






        mDatabase.addChildEventListener(new ChildEventListener() {


            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Object f = dataSnapshot.getKey();

                String si = String.valueOf(f);
                Log.d("Departamento: " ,  si);


                if (si.equals("BIBLI")){
                    Object depto;
                    Object title;
                    Object message;
                    Object key;

                    for (DataSnapshot ds : dataSnapshot.getChildren()){



                        key   =  ds.getKey();
                        depto = ds.child("depto").getValue();
                        title = ds.child("title").getValue();
                        message = ds.child("message").getValue();

                        String sKey = String.valueOf(key);
                        String sDepto = String.valueOf(depto);
                        String sTitle = String.valueOf(title);
                        String sMessage = String.valueOf(message);
                        String sActive = "activar";
                        Log.d("SNAPDB: ", sKey);
                        Log.d("SNAPDB: ", sDepto);
                        Log.d("SNAPDB: ", sMessage);
                        Log.d("SNAPDB: ", sTitle);




                        if (myDb.verificarDatos(sKey) != true)
                         {
                            myDb.insertData(sKey,sDepto,sMessage,sTitle,sActive);
                         }else
                             Log.d("La Data: ", "Es la misma");




                    }
                    Cursor res = myDb.getAllData();
                    while (res.moveToNext()) {

                        String rKey = res.getString(0);
                        String rDepto = res.getString(1);
                        String rMessage = res.getString(2);
                        String rTitle = res.getString(3);
                        String rActive = res.getString(4);
                        Log.d("mamame: " , rActive );
                        if (rActive.equals("activar")){
                            mUsernames.add("ID: " + (rKey) + "\n" + "Departamento: " + (rDepto)  + "\n" + "Title: " + (rTitle) + "\n" + "Message: " + (rMessage) + "\n" + "Activo" + rActive  );
                            arrayAdapter.notifyDataSetChanged();}

//                        Log.d("key : ", rKey);
//                        Log.d("depto : ", rDepto);
//                        Log.d("title : ", rMessage);
//                        Log.d("message : ", rTitle);
//                        mUsernames.add("ID: " + (rKey) + "\n" + "Departamento: " + (rDepto)  + "\n" + "Title: " + (rTitle) + "\n" + "Message: " + (rMessage));
//                        arrayAdapter.notifyDataSetChanged();
                    }

                }
                else
                    Log.d("SINO: ", si);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    private void delete(String item) {
        // delete app
        try {
            Intent intent = new Intent(Intent.ACTION_DELETE);
            intent.setData(Uri.fromParts("package", inicio.Aqui, null));
            //onSaveInstanceState(this);
            intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
            startActivity(intent);

        } catch (Exception e) {
        }
    }


}
