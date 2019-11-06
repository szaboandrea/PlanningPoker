package com.example.planningpoker;

import com.google.firebase.database.DataSnapshot;

import java.util.List;

public interface OnGetDataListener {
    void onSuccess(List<String> dataList);
}
