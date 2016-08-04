package com.zxl.testrxbus;

import android.os.Bundle;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RxBusEvent<Bean> beanRxBusEvent = new RxBusEvent<>();
        RxBus.getDefault().post(beanRxBusEvent);
    }

    @Override
    public void bindData(RxBusEvent rxBusEvent) {
        super.bindData(rxBusEvent);
    }
}
