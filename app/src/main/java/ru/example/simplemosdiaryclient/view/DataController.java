package ru.example.simplemosdiaryclient.view;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class DataController {
    private static DataController instance;
    private MutableLiveData<List<String>> data = new MutableLiveData<>();

    private DataController() {
    }

    public static synchronized DataController getInstance() {
        if (instance == null) {
            instance = new DataController();
        }
        return instance;
    }

    public void setData(List<String> newData) {
        data.setValue(newData);
    }

    public MutableLiveData<List<String>> getData() {
        return data;
    }
}
