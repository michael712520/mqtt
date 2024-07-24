package org.fcly.code3.handler;

import io.netty.channel.Channel;
import io.netty.handler.codec.mqtt.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: fcly
 * Date: 2022-11-15
 * Description: UnSubscribe Ack消息
 */
@Slf4j
@Component
public class UnSubscribeAckMessageStrategy   implements MessageStrategy{
    @Override
    public void sendResponseMessage(Channel channel, MqttMessage mqttMessage) {
        /*---------------------------解析接收的消息---------------------------*/
        MqttMessageIdVariableHeader messageIdVariableHeader = (MqttMessageIdVariableHeader) mqttMessage.variableHeader();

        /*---------------------------构建返回的消息---------------------------*/
        MqttMessageIdVariableHeader variableHeaderBack = MqttMessageIdVariableHeader.from(messageIdVariableHeader.messageId());
        MqttFixedHeader mqttFixedHeaderBack = new MqttFixedHeader(MqttMessageType.UNSUBACK, false, MqttQoS.AT_MOST_ONCE, false, 2);
        MqttUnsubAckMessage unSubAckMsg = new MqttUnsubAckMessage(mqttFixedHeaderBack,variableHeaderBack);
        log.info("返回消息:"+unSubAckMsg.toString());

        channel.writeAndFlush(unSubAckMsg);
    }
}
