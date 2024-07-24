package org.fcly.code3.handler;

import io.netty.channel.Channel;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttQoS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: fcly
 * Date: 2022-11-16
 * Description: PINGRESP消息
 */
@Slf4j
@Component
public class PingMessageStrategy implements MessageStrategy {
    @Override
    public void sendResponseMessage(Channel channel, MqttMessage mqttMessage) {
        MqttFixedHeader fixedHeader = new MqttFixedHeader(MqttMessageType.PINGRESP, false, MqttQoS.AT_MOST_ONCE, false, 0);
        MqttMessage pingBackMsg = new MqttMessage(fixedHeader);
        log.info("返回消息:" + pingBackMsg.toString());

        channel.writeAndFlush(pingBackMsg);
    }
}
