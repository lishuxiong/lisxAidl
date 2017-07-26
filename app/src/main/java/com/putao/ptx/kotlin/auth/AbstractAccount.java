package com.putao.ptx.kotlin.auth;


import android.os.IBinder;
import android.os.RemoteException;

import com.putao.ptx.kotlin.IAccountAPI;
import com.putao.ptx.kotlin.IAccountCallback;
import com.putao.ptx.kotlin.mode.Request;

/**
 * Created by Admin on 2017/7/25.
 */

abstract class AbstractAccount {

    private Transport mTransport = new Transport();

    private class Transport extends IAccountAPI.Stub {
        @Override
        public String getName() throws RemoteException {
            return getNameInfo();
        }

        @Override
        public void getAccount(Request request, IAccountCallback cb) throws RemoteException {
            getAccountInfo(request,cb);
        }
    }

    public abstract String getNameInfo();

    public abstract void getAccountInfo(Request request, IAccountCallback cb);

    public final IBinder getIBinder() {
        return mTransport.asBinder();
    }
}
