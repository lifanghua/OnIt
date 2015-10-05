package com.lfhzy.onit;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lifanghua0602 on 9/13/15.
 */
public class OnItRowData implements Parcelable {
    private String title;

    public OnItRowData(String title) {
        this.title = title;
    }

    public String getTitle() {
        return  title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static OnItRowData getDefaultInstance() {
        return new OnItRowData("");
    }

    /********** Below are Parcelable implementations **************/

    protected OnItRowData(Parcel in) {
        title = in.readString();
    }

    public static final Creator<OnItRowData> CREATOR = new Creator<OnItRowData>() {
        @Override
        public OnItRowData createFromParcel(Parcel in) {
            return new OnItRowData(in);
        }

        @Override
        public OnItRowData[] newArray(int size) {
            return new OnItRowData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
    }
}
