package com.putao.ptx.lisx.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

import com.putao.ptx.kotlin.IAccountAPI;
import com.putao.ptx.kotlin.IAccountCallback;
import com.putao.ptx.kotlin.mode.Request;
import com.putao.ptx.kotlin.mode.Response;

/**
 * Created by Admin on 2017/7/25.
 */

public class AccountManager {

    private static final String TAG = AccountManager.class.getSimpleName();
    //要绑定服务端的包名
    private static final String PACKAGE_ACCOUNT = "com.putao.ptx.kotlin";
    //要绑定对应服务端的action
    private static final String BIND_AUTH_SERVICE = "com.putao.ptx.kotlin.BIND_ACCOUNT_SERVICE";

    private Context context;
    private boolean connected;
    private IAccountAPI api;
    private final Object lock = new Object();
    private Handler mHandler;

    public AccountManager(Context context, Handler handler) {
        this.context = context.getApplicationContext();
        this.mHandler = handler;
        bindServiceIfNeeded();
    }


    private void bindServiceIfNeeded() {
        if (!connected) {
            Intent intent = new Intent(BIND_AUTH_SERVICE);
            intent.setPackage(PACKAGE_ACCOUNT);
            boolean result = context.bindService(intent, new ServiceConnection() {

                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    api = IAccountAPI.Stub.asInterface(service);
                    synchronized (lock) {
                        connected = true;
                        lock.notifyAll();
                    }
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    api = null;
                    synchronized (lock) {
                        connected = false;
                        lock.notifyAll();
                    }
                }
            }, Context.BIND_AUTO_CREATE);
            Log.d(TAG, "result:" + result);
        }
    }


    private boolean waitForAccess() {
        long start = System.currentTimeMillis();
        synchronized (lock) {
            if (!connected) {
                try {
                    lock.wait(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.d(TAG, "waitForAccess spent:" + (System.currentTimeMillis() - start));
        return connected;
    }

    public String getName() {
        bindServiceIfNeeded();
        //同步获取数据，会阻塞住线程
        String name = "";
        if (waitForAccess()) {
            try {
                name = api.getName();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return name;
    }

    public void getAccount() {
        bindServiceIfNeeded();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (waitForAccess()) {
                    try {
                        Request request = new Request();
                        api.getAccount(request, new IAccountCallback.Stub() {
                            @Override
                            public void onResponse(Response response) throws RemoteException {
                                Log.d("lisx",response.account.toString());
                                Message msg = new Message();
                                msg.obj = response;
                                mHandler.sendMessage(msg);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }
}
