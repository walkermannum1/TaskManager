package com.sand.taskmanager.login;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by Biguang on 2017/1/4.
 */

public class LoginAcitity extends AppCompatActivity implements View.OnClickListener{

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private ImageView log;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private CheckBox remembPass;

    @Override
    public void onClick(View view) {

    }
}
