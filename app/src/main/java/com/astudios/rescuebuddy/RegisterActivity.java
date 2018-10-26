package com.astudios.rescuebuddy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import essential.Essential;
import networkutil.VolleySingleton;

public class RegisterActivity extends AppCompatActivity {


    private EditText registername;
    private EditText registermobile;
    private EditText registeremail;
    private EditText registeraddress;
    private EditText registerage;
    private EditText registerheight;
    private android.widget.Spinner registergender;
    private EditText registerweight;
    private EditText registerpassword;
    private EditText registerreenterPassword;
    private Button registercontinue;
    private Essential essential;
    private StringRequest stringRequest;
    public static final int MIN_PASS_LEN = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Essential.NukeSSLCerts.nuke();
        findIds();


    }

    private void findIds() {

        essential = new Essential(this);

        this.registercontinue = (Button) findViewById(R.id.register_continue);
        registercontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerContinue();
            }
        });
        this.registerreenterPassword = (EditText) findViewById(R.id.register_reenterPassword);
        this.registerpassword = (EditText) findViewById(R.id.register_password);
        this.registerweight = (EditText) findViewById(R.id.register_weight);
        this.registergender = (Spinner) findViewById(R.id.register_gender);
        this.registerheight = (EditText) findViewById(R.id.register_height);
        this.registerage = (EditText) findViewById(R.id.register_age);
        this.registeraddress = (EditText) findViewById(R.id.register_address);
        this.registeremail = (EditText) findViewById(R.id.register_email);
        this.registermobile = (EditText) findViewById(R.id.register_mobile);
        this.registername = (EditText) findViewById(R.id.register_name);
    }

    private void registerContinue() {

        String pass = registerpassword.getText().toString();
        String rpass = registerreenterPassword.getText().toString();
        String weight = registerweight.getText().toString();
        String height = registerheight.getText().toString();
        String age = registerage.getText().toString();
        String gender = registergender.getPrompt().toString();
        String address = registeraddress.getText().toString();
        String email = registeremail.getText().toString().toLowerCase().trim();
        String mobile = registermobile.getText().toString().trim();
        String name = registername.getText().toString().toLowerCase().trim();

        if (pass.equals("") || weight.equals("") || height.equals("") || age.equals("")
                || gender.equals("") || address.equals("") || email.equals("") ||
                mobile.equals("") || name.equals(""))
            essential.show(Essential.FILL_FORM, Essential.ERROR);

        else if (pass.length() < MIN_PASS_LEN)
            essential.show("Minimum password is 6 characters", Essential.ERROR);

       else if (!pass.equals(rpass))
            essential.show(Essential.PASSWORD_NOMATCH, Essential.ERROR);

       registerRequest(name,mobile,email,address,gender,age,height,weight,pass);



    }

    private void registerRequest(String name, String mobile, String email, String address, String gender, String age, String height, String weight, String pass) {
        //show progress
        essential.showDialog();

        stringRequest = new StringRequest(Request.Method.POST, Essential.REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //cancelling the dialog
                        essential.cancelDialog();
                        essential.show(Essential.REGISTERED_SUCCESS, Essential.SUCCESS);
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
//                map.put(Essential.MOBILE_KEY, num);
//                map.put(Essential.PASS_KEY, pass);
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
}
