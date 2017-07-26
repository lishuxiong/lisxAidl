// IAccountCallback.aidl
package com.putao.ptx.kotlin;
import com.putao.ptx.kotlin.mode.Response;

interface IAccountCallback {

        oneway void onResponse(in Response response);
}
