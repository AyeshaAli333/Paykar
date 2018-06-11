package com.example.hackathon.hackathonesplit;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class EventUserPerActivity extends AppCompatActivity {

    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_user);

        Intent intent = getIntent();

        final String eventname = intent.getStringExtra("eventname");
        final String username = intent.getStringExtra("username");
        final int eventid = intent.getIntExtra("eventid",  -1);
        final String name = intent.getStringExtra("name");
        final int age = intent.getIntExtra("age", -1);
        final String amount = intent.getStringExtra("amount");
        final String dateofcreation = intent.getStringExtra("dateofcreation");

        count = 0;
        // Display user details
        final EditText etusernameadd = (EditText) findViewById(R.id.etusernameadd);
        final EditText etuserper = (EditText) findViewById(R.id.etuserper);
        final Button bcreateandadduserevent = (Button) findViewById(R.id.bcreateandadduserevent);
        bcreateandadduserevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = etusernameadd.getText().toString();

                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                count++;
                                AlertDialog.Builder builder = new AlertDialog.Builder(EventUserPerActivity.this);
                                builder.setMessage(count+" User Added To Event"+eventname)
                                        .setNegativeButton("Add More", null)
                                        .create()
                                        .show();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(EventUserPerActivity.this);
                                builder.setMessage("User Not Present in System")
                                        .setNegativeButton("Create Account", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                EventUserRequest eventuserRequest = new EventUserRequest(String.valueOf(eventid), username, amount, amount, amount, dateofcreation, eventname, responseListener);
                RequestQueue queue = Volley.newRequestQueue(EventUserPerActivity.this);
                queue.add(eventuserRequest);
            }
        });
        final Button bcalnotification = (Button) findViewById(R.id.bcalnotification);
        bcalnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EventUserPerActivity.this, InitialUpdateActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("name", name);
                intent.putExtra("age", age);
                intent.putExtra("count", count);
                intent.putExtra("amount", amount);
                intent.putExtra("eventid", eventid);
                EventUserPerActivity.this.startActivity(intent);
            }
        });
    }
}
