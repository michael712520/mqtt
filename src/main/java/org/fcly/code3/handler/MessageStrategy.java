package org.fcly.code3.handler;

import io.netty.channel.Channel;
import io.netty.handler.codec.mqtt.MqttMessage;

/**
 * Created with IntelliJ IDEA.
 * User: fcly
 * Date: 2022-11-16
 * Description: 返回mqtt client的消息接口
 */
public interface MessageStrategy {
    void sendResponseMessage(Channel channel, MqttMessage mqttMessage);
}
