package com.astudios.rescuebuddy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    private android.widget.TextView registertitle;
    private android.widget.TextView registerinfo;
    private android.widget.EditText registername;
    private android.widget.EditText registeremail;
    private android.widget.EditText registeraddress;
    private android.widget.EditText registergender;
    private android.widget.EditText registerage;
    private android.widget.EditText registerheight;
    private android.widget.EditText registerweight;
    private android.widget.EditText registermobile;
    private android.widget.Button registersave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //findId();

    }

//        private void findId() {
//            this.registersave = (Button) findViewById(R.id.register_save);
//            this.registermobile = (EditText) findViewById(R.id.register_mobile);
//            this.registerweight = (EditText) findViewById(R.id.register_weight);
//            this.registerheight = (EditText) findViewById(R.id.register_height);
//            this.registerage = (EditText) findViewById(R.id.register_age);
//            this.registergender = (EditText) findViewById(R.id.register_gender);
//            this.registeraddress = (EditText) findViewById(R.id.register_address);
//            this.registeremail = (EditText) findViewById(R.id.register_email);
//            this.registername = (EditText) findViewById(R.id.register_name);
//            this.registerinfo = (TextView) findViewById(R.id.register_info);
//            this.registertitle = (TextView) findViewById(R.id.register_title);
//        }
}
