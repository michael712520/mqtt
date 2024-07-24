package org.fcly.code3.netty;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: fcly
 * Date: 2022-11-15
 * Description: mqtt broker channel初始化器
 */
@Component
public class MqttBrokerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Resource
    MqttBrokerChannelHandler mqttBrokerChannelHandler;

    public MqttBrokerChannelInitializer() {
        super();
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast("mqttDecoder", new MqttDecoder());
        socketChannel.pipeline().addLast("mqttEncoder", MqttEncoder.INSTANCE);
        //心跳超时控制
        socketChannel.pipeline().addLast("idle",
                new IdleStateHandler(15, 0, 0, TimeUnit.MINUTES));
        socketChannel.pipeline().addLast("mqttHandler", mqttBrokerChannelHandler);
    }
}
