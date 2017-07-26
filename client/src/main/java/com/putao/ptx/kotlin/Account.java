package com.putao.ptx.kotlin;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by lisx on 2017/7/25.
 */

public class Account implements Parcelable {

    public String name;
    public int age;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.age);
    }

    public Account() {
    }

    protected Account(Parcel in) {
        this.name = in.readString();
        this.age = in.readInt();

    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel source) {
            return new Account(source);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

}
