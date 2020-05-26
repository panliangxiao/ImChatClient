package com.plx.android.im;

import android.util.Log;

public class EchoClient {
    private static final String TAG = EchoClient.class.getSimpleName();

    private final LongLifeSocket mLongLiveSocket;

    public EchoClient(String host, int port) {
        mLongLiveSocket = new LongLifeSocket(
                host, port,
                new LongLifeSocket.DataCallback() {
                    @Override
                    public void onData(byte[] data, int offset, int len) {
                        Log.i(TAG, "EchoClient: received: " + new String(data, offset, len));
                    }
                },
                // 返回 true，所以只要出错，就会一直重连
                new LongLifeSocket.ErrorCallback() {
                    @Override
                    public boolean onError() {
                        return true;
                    }
                });
    }

    public void send(String msg) {
        mLongLiveSocket.write(msg.getBytes(), new LongLifeSocket.WritingCallback() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "onSuccess: ");
            }

            @Override
            public void onFail(byte[] data, int offset, int len) {
                Log.w(TAG, "onFail: fail to write: " + new String(data, offset, len));
                // 连接成功后，还会发送这个消息
                mLongLiveSocket.write(data, offset, len, this);
            }
        });
    }
}