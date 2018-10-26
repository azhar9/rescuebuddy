package com.astudios.rescuebuddy;

import android.app.VoiceInteractor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import essential.Essential;
import networkutil.VolleySingleton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private android.widget.ImageView loginimageview;
    private android.widget.EditText loginphone;
    private android.widget.Button logincontinue;
    public static final int MAX_NUMBER_LENGTH = 10;
    private Essential essential;
    private StringRequest stringRequest;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connecting views with ids and setting listeners
        findIds();

    }

    private void findIds() {
        //init essential object
        essential = new Essential(this);

        this.logincontinue = (Button) findViewById(R.id.login_continue);
        logincontinue.setOnClickListener(this);

        this.loginphone = (EditText) findViewById(R.id.login_phone);
        this.loginimageview = (ImageView) findViewById(R.id.login_imageview);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.login_continue:
                if (essential.checkInternet()) {
                    String num = loginphone.getText().toString();
                    if (num.equals(""))
                        loginphone.setError(Essential.REQUIRED);
                    else if (num.length() < MAX_NUMBER_LENGTH) {
                        essential.show(Essential.NOT_VALID_NUM, Essential.ERROR);
                    } else
                        login(num);
                } else
                    essential.show(Essential.NO_INTERNET, Essential.ERROR);
                break;
        }

    }

    private void login(final String num) {

        //show progress
        essential.showDialog();



        //init the request object
        stringRequest = new StringRequest(Request.Method.POST, Essential.LOGIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //cancelling the dialog
                essential.cancelDialog();
                essential.show(response, Essential.INFO);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //cancelling the dialog
                        essential.cancelDialog();
                        essential.show(error.toString(), Essential.INFO);

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put(Essential.MOBILE_KEY, num);
                return map;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                if (response != null) {
                    //statusCode = response.statusCode;
                }
                return super.parseNetworkResponse(response);
            }


        };

        //adding request
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
