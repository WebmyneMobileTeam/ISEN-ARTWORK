package com.xitij.android.isen_artwork.ui;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.google.gson.GsonBuilder;
import com.xitij.android.isen_artwork.R;
import com.xitij.android.isen_artwork.helpers.CallWebService;
import com.xitij.android.isen_artwork.helpers.Constants;
import com.xitij.android.isen_artwork.helpers.Functions;
import com.xitij.android.isen_artwork.helpers.PrefUtils;
import com.xitij.android.isen_artwork.model.User;

public class LoginScreen extends ActionBarActivity implements View.OnClickListener {

    private Button btnLogin;
    private EditText edName;
    private EditText edPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        edName = (EditText) findViewById(R.id.edName);
        edPassword = (EditText) findViewById(R.id.edPassword);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnLogin:
                processLogin();
                break;
        }
    }

    private void processLogin() {

        final ProgressDialog pd = ProgressDialog.show(LoginScreen.this,"Please wait","Fetching user data",false);
        String push = String.format("%s/%s",edName.getText().toString(),edPassword.getText().toString());
        new CallWebService(Constants.API_LOGIN + push, CallWebService.TYPE_JSONOBJECT) {
            @Override
            public void response(String response) {

                pd.dismiss();
                Functions.logD("Response Login", response);
                User currentUser = new GsonBuilder().create().fromJson(response, User.class);

                if(currentUser.loginState.success.equalsIgnoreCase("1")){

                    Functions.displayMessage(LoginScreen.this,"Login Successfull");
                    PrefUtils.setUser(LoginScreen.this,currentUser);
                    Functions.fireIntent(LoginScreen.this,AddArtWorkScreen.class);
                    PrefUtils.setLogin(LoginScreen.this,true);
                    finish();

                }else{
                    Functions.displayMessage(LoginScreen.this,"Login Failed");
                }
            }

            @Override
            public void error(VolleyError error) {
                pd.dismiss();
                Functions.displayMessage(LoginScreen.this, error.getMessage());

            }
        }.start();
    }
}
