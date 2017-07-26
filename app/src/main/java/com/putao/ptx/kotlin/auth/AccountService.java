package com.putao.ptx.kotlin.auth;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by Admin on 2017/7/25.
 */

public class AccountService extends Service {

    private static AccountImp accountImp;

    public AccountService() {
        accountImp = new AccountImp(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return accountImp.getIBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_REDELIVER_INTENT;
    }
}
