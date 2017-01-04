package com.sand.taskmanager.login;

/**
 * Created by Bi guang on 2017/1/4.
 */

public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
