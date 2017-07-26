package com.putao.ptx.kotlin.mode;

import android.os.Parcel;
import android.os.Parcelable;

import com.putao.ptx.kotlin.Account;


public class Response implements Parcelable {
    public int code;
    public String message;
    public Account account;

    public Response(Parcel in) {
        code = in.readInt();
        message = in.readString();
        account = in.readParcelable(Account.class.getClassLoader());
    }

    public Response() {
    }

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeString(message);
        dest.writeParcelable(account, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Response> CREATOR = new Creator<Response>() {
        @Override
        public Response createFromParcel(Parcel in) {
            return new Response(in);
        }

        @Override
        public Response[] newArray(int size) {
            return new Response[size];
        }
    };
}
