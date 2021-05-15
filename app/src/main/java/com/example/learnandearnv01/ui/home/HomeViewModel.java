package com.example.learnandearnv01.ui.home;

import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> profileName;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        profileName = new MutableLiveData<>();

        mText.setValue("This is home fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }
}