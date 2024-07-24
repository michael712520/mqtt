package org.fcly.code3.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.mqtt.*;
import lombok.extern.slf4j.Slf4j;
import org.fcly.code3.handler.MessageStrategy;
import org.fcly.code3.handler.MessageStrategyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: fcly
 * Date: 2022-11-16
 * Description: mqtt broker channel 处理
 * ChannelInboundHandlerAdapter:实现了ChannelInboundHandler 的接口
 */
@Component
@Slf4j
@ChannelHandler.Sharable
public class MqttBrokerChannelHandler extends ChannelInboundHandlerAdapter  {

    @Autowired
    MessageStrategyManager messageStrategyManager;

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        MqttMessage mqttMessage = (MqttMessage) msg;
        log.info("--------------------------begin---------------------------*");
        log.info("来自终端:" + channelHandlerContext.channel().remoteAddress());
        log.info("接收消息：" + mqttMessage.toString());
        try {
            MqttMessageType type = mqttMessage.fixedHeader().messageType();
            MessageStrategy messageStrategy =  messageStrategyManager.getMessageStrategy(type);
            if(messageStrategy!=null){
                messageStrategy.sendResponseMessage(channelHandlerContext.channel(),mqttMessage);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        log.info("--------------------------end---------------------------*");

    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
