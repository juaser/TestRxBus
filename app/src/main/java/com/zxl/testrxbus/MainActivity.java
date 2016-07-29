package com.zxl.testrxbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RxBusEvent<Bean> beanRxBusEvent = new RxBusEvent<>();
        RxBus.getDefault().post(beanRxBusEvent);
    }

    public void sub() {
        RxBus.getDefault().toObservable(RxBusEvent.class)
                .subscribe(new RxBusSubscriber<RxBusEvent>() {
                               @Override
                               protected void onEvent(RxBusEvent rxBusEvent) {

                               }

                               @Override
                               public void onError(Throwable e) {
                                   super.onError(e);
                               }
                           }

                );
    }
}
