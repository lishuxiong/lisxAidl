package com.putao.ptx.kotlin.auth;

import android.content.Context;

import com.putao.ptx.kotlin.Account;
import com.putao.ptx.kotlin.IAccountCallback;
import com.putao.ptx.kotlin.mode.Request;
import com.putao.ptx.kotlin.mode.Response;

/**
 * Created by Admin on 2017/7/25.
 */

public class AccountImp extends AbstractAccount {

    //如果需要使用Context
    private Context mContext;

    public AccountImp(Context context) {
        this.mContext = context;
    }

    @Override
    public String getNameInfo() {
        //这里直接返回测试数据
        return "张三and李四";
    }

    @Override
    public void getAccountInfo(Request request, IAccountCallback cb) {
        Account account = new Account();
        account.name = "lisx";
        account.age = 27;
        //这里在包装一层对象，方便返回更多信息
        Response response = new Response();
        response.account = account;
        try {
            //这里直接yoga接口回调数据对象、当然也可以直接return返回，取决于自己定义的aidl接口
            cb.onResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
