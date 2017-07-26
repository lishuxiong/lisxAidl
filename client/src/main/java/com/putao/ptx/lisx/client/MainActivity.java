package com.putao.ptx.lisx.client;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.putao.ptx.kotlin.mode.Response;


public class MainActivity extends AppCompatActivity {

    private TextView tv_user_info, tv_account;
    private AccountManager manager = null;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Response response = (Response) msg.obj;
            tv_user_info.setText(response.account.toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = new AccountManager(MainActivity.this, handler);
        tv_user_info = (TextView) findViewById(R.id.tv_user_info);
        tv_account = (TextView) findViewById(R.id.tv_account);
        tv_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.getAccount();
            }
        });

    }
}
