//package com.example.owner.firebasedb;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.widget.ListView;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
///**
// * Created by owner on 4/2/2018.
// */
//
//public class ViewDatabase extends AppCompatActivity {
//
//    private FirebaseDatabase mFirebaseDatabase;
//    private DatabaseReference myRef;
//    private String userID;
//    private ListView mListView;
//
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.view_database_layout);
//
//        mListView = findViewById(R.id.user_list);
//        mFirebaseDatabase = FirebaseDatabase.getInstance();
//        myRef = mFirebaseDatabase.getReference();
//
//
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d("hi", "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w("no", "Failed to read value.", error.toException());
//            }
//        });
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                showData(dataSnapshot);
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//    }
//
//    private void showData(DataSnapshot dataSnapshot) {
//
//      //  for(DataSnapshot ds : dataSnapshot.getChildren()){
//            DataSnapshot ds ;
//            ds = (DataSnapshot) dataSnapshot.getChildren();
//            UserInformation uInfo = new UserInformation();
//            uInfo.setName(ds.child(userID).getValue(UserInformation.class).getName());
//            uInfo.setEmail(ds.child(userID).getValue(UserInformation.class).getEmail());
//            uInfo.setPhone_num(ds.child(userID).getValue(UserInformation.class).getPhone_num());
//
//            Log.d("USERS","showData: Name: " + uInfo.getName());
//            Log.d("USERS","showData: Email: " + uInfo.getEmail());
//            Log.d("USERS","showData: Phone_num: " + uInfo.getPhone_num());
//
//
//
//      //  }
//
//
//    }
//
//}
