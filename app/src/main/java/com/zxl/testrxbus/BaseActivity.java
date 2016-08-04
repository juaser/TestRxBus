package com.zxl.testrxbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import rx.Subscription;

/**
 * @类说明：
 * @author：zxl
 * @CreateTime 2016/8/4.
 */
public class BaseActivity extends FragmentActivity {
    protected String TAG = getClass().getName() + "====";
    private Subscription mRxSub, mRxSubSticky;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * RxBus 接收绑定数据
     */
    protected void subscribeEvent() {
        RxSubscriptions.remove(mRxSub);
        mRxSub = RxBus.getDefault().toObservable(RxBusEvent.class)
                .subscribe(new RxBusSubscriber<RxBusEvent>() {
                    @Override
                    protected void onEvent(RxBusEvent rxBusEvent) {
                        bindData(rxBusEvent);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        Log.e(TAG, "Rxbus>>Error>>resubscribes");
                        subscribeEvent();
                    }
                });
        RxSubscriptions.add(mRxSub);
    }

    private void subscribeEventSticky() {
        if (mRxSubSticky != null && !mRxSubSticky.isUnsubscribed()) {
            RxSubscriptions.remove(mRxSubSticky);
        } else {
            mRxSubSticky = RxBus.getDefault().toObservableSticky(RxBusEvent.class)
                    .subscribe(new RxBusSubscriber<RxBusEvent>() {
                        @Override
                        protected void onEvent(RxBusEvent eventSticky) {
                            Log.i(TAG, "onNext--Sticky-->");
                            bindData(eventSticky);
                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);
                            Log.e(TAG, "onError--Sticky");
                            /**
                             * 这里注意: Sticky事件 不能在onError时重绑事件,这可能导致因绑定时得到引起Error的Sticky数据而产生死循环
                             */
                        }
                    });
            RxSubscriptions.add(mRxSubSticky);
        }
    }

    public void bindData(RxBusEvent rxBusEvent) {
        Log.e(TAG, "接收到RxBus数据");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxSubscriptions.remove(mRxSub);
        RxSubscriptions.remove(mRxSubSticky);
    }
}
