package com.astudios.rescuebuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import essential.Essential;
import networkutil.VolleySingleton;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button logIn;
    private EditText contact, password;
    private Essential essential;
    private String num, pass;
    private StringRequest stringRequest;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Essential.NukeSSLCerts.nuke();
        findIds();
    }

    private void findIds() {
        essential = new Essential(this);

        logIn = findViewById(R.id.login_continue);
        contact = findViewById(R.id.login_phone);
        password = findViewById(R.id.login_password);

        logIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_continue:
                logInContinue();
                break;
        }
    }

    private void logInContinue() {
        if (essential.checkInternet()) {
            num = contact.getText().toString().trim();
            pass = password.getText().toString();
            if (!num.matches(Essential.contactNumberPattern) || pass.equals("")) {
                if (!num.matches(Essential.contactNumberPattern))
                    contact.setError(Essential.REQUIRED);
                if (pass.equals(""))
                    password.setError(Essential.REQUIRED);
            } else {
                logInRequest();
            }
        } else {
            essential.show(Essential.NO_INTERNET, Essential.ERROR);
        }
    }

    private void logInRequest() {
        //show progress
        essential.showDialog();

        stringRequest = new StringRequest(Request.Method.POST, Essential.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //cancelling the dialog
                        essential.cancelDialog();
                        essential.show(Essential.LOGIN_SUCCESS, Essential.SUCCESS);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //cancelling the dialog
                        essential.cancelDialog();
                        essential.show(error.toString(), Essential.ERROR);
                        Log.e("Err", error.toString());
                    }
                }) {
            //            @Override
//            public String getBodyContentType() {
//                return "application/x-www-form-urlencoded; charset=UTF-8";
//            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put(Essential.MOBILE_KEY, num);
                map.put(Essential.PASS_KEY, pass);
                return map;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String token = response.headers.get("x-auth");
                Log.e("Parse", token);
                return super.parseNetworkResponse(response);
            }

        };
        //adding request
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void register(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }
}
