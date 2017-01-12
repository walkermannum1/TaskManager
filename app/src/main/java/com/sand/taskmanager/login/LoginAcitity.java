package com.sand.taskmanager.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.sand.taskmanager.R;
import com.sand.taskmanager.ShowtaskActivity;
import com.sand.taskmanager.User;

import java.util.HashMap;

import static android.icu.text.DateTimePatternGenerator.PatternInfo.OK;

/**
 * Created by Biguang on 2017/1/4.
 */

public class LoginAcitity extends AppCompatActivity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private ImageView log;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private CheckBox remembPass;

    private String address = "http://116.236.224.54:21219/TestTaskManagerDemo/Login";
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result = " ";
            if ("OK".equals(msg.obj.toString())) {
                result = getResources().getString(R.string.success);
            } else if ("Wrong".equals(msg.obj.toString())) {
                result = getResources().getString(R.string.fail);
            } else {
                result = msg.obj.toString();
            }
            Toast.makeText(LoginAcitity.this, result, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        log = (ImageView) findViewById(R.id.imageView);
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        remembPass = (CheckBox) findViewById(R.id.remember_pass);
        login = (Button) findViewById(R.id.login);

        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember) {
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            remembPass.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isInputValid()) {
                    return;
                }
                String name = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                HashMap<String, String> parms = new HashMap<String, String>();
                parms.put(User.USERNAME, name);
                parms.put(User.PASSWORD, password);
                try {
                    String url =  HttpUtils.getURLWithParams(address, parms);
                    HttpUtils.sendHttpRequest(url, new HttpCallbackListener() {
                        @Override
                        public void onFinish(String response) {
                            Message message = new Message();
                            message.obj = response;
                            mHandler.sendMessage(message);
                        }

                        @Override
                        public void onError(Exception e) {
                            Message message = new Message();
                            message.obj = e.toString();
                            mHandler.sendMessage(message);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private boolean isInputValid() {
                return true;
            }
        });
    }
}