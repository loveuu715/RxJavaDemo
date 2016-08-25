package com.vv.mydesignframework.base.event;

/**
 * Created by VV on 2016/8/23.
 */
public class EventObject {
    private Object mResult;
    private int mEventId;

    public EventObject(int eventId, Object result) {
        mEventId = eventId;
        mResult = result;
    }

    public <T> T getResult () {
        return (T) mResult;
    }

    public void setResult(Object result) {
        mResult = result;
    }

    public int getEventId() {
        return mEventId;
    }

    public void setEventId(int eventId) {
        mEventId = eventId;
    }
}
