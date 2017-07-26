// IAccountAPI.aidl
package com.putao.ptx.kotlin;
import com.putao.ptx.kotlin.mode.Request;
import com.putao.ptx.kotlin.IAccountCallback;
// Declare any non-default types here with import statements

interface IAccountAPI {

   //基本数据类型
   String getName();

   //自定义实现parcelable接口数据类型
   oneway void getAccount(in Request request, in IAccountCallback cb);

}
