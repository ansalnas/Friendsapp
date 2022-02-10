package com.example.friendsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
EditText ed1,ed2,ed3,ed4;
Button b1;
String getyname,getfname,getniname,getdes;
String apiurl="https://dummyapifriends.herokuapp.com/adddata";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=(EditText)findViewById(R.id.yna);
        ed2=(EditText)findViewById(R.id.yfname);
        ed3=(EditText)findViewById(R.id.yfn);
        ed4=(EditText)findViewById(R.id.dyf);
       b1=(AppCompatButton)findViewById(R.id.submit);
       b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            getyname=ed1.getText().toString();
            getfname=ed2.getText().toString();
            getniname=ed3.getText().toString();
            getdes=ed4.getText().toString();
            StringRequest sr=new StringRequest(Request.Method.POST, apiurl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG);
                    ed1.setText("");
                    ed2.setText("");
                    ed3.setText("");
                    ed4.setText("");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
               Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG);

                }

            })
               {

                   @Override
                   protected Map<String, String> getParams() throws AuthFailureError {
                       HashMap<String,String> hm=new HashMap();
                       hm.put("name",getyname);
                       hm.put("friendName",getfname);
                       hm.put("friendNickName",getniname);
                       hm.put("DescribeYourFriend",getdes);
                       return hm;
                   }
               };

               RequestQueue rq= Volley.newRequestQueue(getApplicationContext());
               rq.add(sr);



           }
       });


    }
}