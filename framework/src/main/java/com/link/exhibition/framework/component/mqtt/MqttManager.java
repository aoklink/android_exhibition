package com.link.exhibition.framework.component.mqtt;

import com.alibaba.fastjson.JSON;
import com.link.exhibition.framework.KeysConstants;
import com.link.exhibition.framework.base.BaseApplication;
import com.link.exhibition.framework.bean.HomeRequest;
import com.link.exhibition.framework.utils.data.DeviceUtils;
import com.link.exhibition.framework.utils.data.L;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Created on 2019/9/9  11:29
 * chenpan pan.chen@linkfeeling.cn
 */
@SuppressWarnings("unused")
public final class MqttManager {

    private static final String TAG = "MqttManager";

    private MqttAndroidClient mqttAndroidClient;

    private static MqttManager sMqttManager;

    public static MqttManager getInstance() {
        if (sMqttManager == null) {
            sMqttManager = new MqttManager();
        }
        return sMqttManager;
    }

    public void connect(MqttCallbackExtended callback) {
        if (mqttAndroidClient == null) {
            mqttAndroidClient = new MqttAndroidClient(BaseApplication.getAppContext(), KeysConstants.SERVER_URL, KeysConstants.GID + DeviceUtils.getMac());
        }
        mqttAndroidClient.setCallback(callback);

        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setConnectionTimeout(10);
        mqttConnectOptions.setKeepAliveInterval(15);
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setCleanSession(true);
        try {
            mqttConnectOptions.setUserName(KeysConstants.SIGNATURE + KeysConstants.ACCESS_KEY + KeysConstants.SEPARATOR + KeysConstants.INSTANCE_ID);
            mqttConnectOptions.setPassword(Tool.macSignature(KeysConstants.GID + DeviceUtils.getMac(), KeysConstants.SECRET_KEY).toCharArray());
        } catch (Exception e) {
            L.e(TAG, "exception:setPassword", e);
        }

        try {
            mqttAndroidClient.connect(mqttConnectOptions, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    L.e(TAG, "connect:onSuccess");
                    subscribeToTopic();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    L.e(TAG, "connect:onFailure", exception);
                }
            });
        } catch (MqttException e) {
            L.e(TAG, "connect:exception", e);
        }
    }

    public void subscribeToTopic() {
        try {
            final String topicFilter[] = {KeysConstants.TOPIC};
            final int[] qos = {1};
            mqttAndroidClient.subscribe(topicFilter, qos, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    L.e(TAG, "subscribe:success");
                    publishMessage(JSON.toJSONString(new HomeRequest()));
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    L.e(TAG, "subscribe:failed", exception);
                }
            });

        } catch (MqttException ex) {
            L.e(TAG, "subscribe:exception", ex);
        }
    }


    private void publishMessage(String json) {
        try {
            MqttMessage message = new MqttMessage();
            final String msg = json;
            message.setPayload(msg.getBytes());
            mqttAndroidClient.publish(KeysConstants.TOPIC, message, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    L.e(TAG, "publish:success:" + msg);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    L.e(TAG, "publish:failed:" + msg);
                }
            });
        } catch (MqttException e) {
            L.e(TAG, "publish:exception", e);
        }
    }

}
