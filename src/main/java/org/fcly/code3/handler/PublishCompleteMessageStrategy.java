package org.fcly.code3.handler;

import io.netty.channel.Channel;
import io.netty.handler.codec.mqtt.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: fcly
 * Date: 2022-11-15
 * Description: publish complete消息
 */
@Slf4j
@Component
public class PublishCompleteMessageStrategy   implements MessageStrategy{
    @Override
    public void sendResponseMessage(Channel channel, MqttMessage mqttMessage) {
        MqttMessageIdVariableHeader messageIdVariableHeader = (MqttMessageIdVariableHeader) mqttMessage.variableHeader();

        //	构建返回报文
        MqttFixedHeader mqttFixedHeaderBack = new MqttFixedHeader(MqttMessageType.PUBCOMP,false, MqttQoS.AT_MOST_ONCE,false,0x02);
        MqttMessageIdVariableHeader mqttMessageIdVariableHeaderBack = MqttMessageIdVariableHeader.from(messageIdVariableHeader.messageId());
        MqttMessage publishCompleteMsg = new MqttMessage(mqttFixedHeaderBack,mqttMessageIdVariableHeaderBack);
        log.info("返回消息:"+publishCompleteMsg.toString());

        channel.writeAndFlush(publishCompleteMsg);

    }
}
