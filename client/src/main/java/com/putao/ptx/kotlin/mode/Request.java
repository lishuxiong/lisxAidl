package com.putao.ptx.kotlin.mode;

import android.os.Parcel;
import android.os.Parcelable;

public class Request implements Parcelable {

    public String appid;
    public String packageName;
    public String versionName;
    public int versionCode;
    public int type;

    public Request() {
    }

    protected Request(Parcel in) {
        appid = in.readString();
        packageName = in.readString();
        versionName = in.readString();
        versionCode = in.readInt();
        type = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(appid);
        dest.writeString(packageName);
        dest.writeString(versionName);
        dest.writeInt(versionCode);
        dest.writeInt(type);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Request> CREATOR = new Creator<Request>() {
        @Override
        public Request createFromParcel(Parcel in) {
            return new Request(in);
        }

        @Override
        public Request[] newArray(int size) {
            return new Request[size];
        }
    };
}
