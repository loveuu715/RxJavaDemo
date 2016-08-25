package com.vv.mvp.mvp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VV on 2016/8/21.
 */
public class MyModel implements BaseModel<List<String>> {
    @Override
    public void loadData(ModelCallback<List<String>> listener) {
        List<String> data = new ArrayList<>();
        data.add("1");
        data.add("2");
        data.add("3");
        data.add("4");
        listener.onComplete(data);
    }
}
