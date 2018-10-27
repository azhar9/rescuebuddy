package com.astudios.rescuebuddy;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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
import locationutil.GPS;
import networkutil.VolleySingleton;

public class RegisterActivity extends AppCompatActivity implements GPS.locCallback {


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
    public static final String[] gender_types = {"Female", "Male", "Others"};


    // GPSTracker class
    GPS gps;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Essential.NukeSSLCerts.nuke();
        findIds();


    }

    @Override
    protected void onResume() {
        super.onResume();
        gps = new GPS(this, this);
        location = gps.getLocation();
        if (location != null) {
            essential.show(location.getLatitude() + "\n" + location.getLongitude(), Essential.INFO);
            // btnShowLocation.setText(location.getLatitude()+"\n"+location.getLongitude());
        }
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
        //populate the spinner with data
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, gender_types);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        registergender.setAdapter(aa);

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
        String gender = registergender.getSelectedItem().toString();
        // String gender = "male";
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

        registerRequest(name, mobile, email, address, gender, age, height, weight, pass);


    }

    private void registerRequest(final String name, final String mobile, final String email, final String address, final String gender, final String age, final String height, final String weight, final String pass) {
        //show progress
        essential.showDialog();

        stringRequest = new StringRequest(Request.Method.POST, Essential.REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //cancelling the dialog
                        essential.cancelDialog();
                        essential.show(response, Essential.INFO);

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
                map.put(Essential.NAME_KEY, name);
                map.put(Essential.EMAIL_KEY, email);
                map.put(Essential.PASS_KEY, pass);
                map.put(Essential.MOBILE_KEY, mobile);
                map.put(Essential.AGE_KEY, age);
                map.put(Essential.WEIGHT_KEY, weight);
                map.put(Essential.HEIGHT_KEY, height);
                map.put(Essential.ADDRESS_KEY, address);
                map.put(Essential.GENDER_KEY, gender);

                //get lat and long from gps and then put here
                if (location != null) {
                    map.put(Essential.LAT_KEY, location.getLatitude() + "");
                    map.put(Essential.LON_KEY, location.getLongitude() + "");
                } else {
                    map.put(Essential.LAT_KEY, "12");
                    map.put(Essential.LON_KEY, "14");
                }
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

    @Override
    public void ca(Location location) {
        this.location = location;
        if (location != null) {
            essential.show(location.getLatitude() + "\n" + location.getLongitude(), Essential.INFO);
            // btnShowLocation.setText(location.getLatitude()+"\n"+location.getLongitude());
        }
    }
}
