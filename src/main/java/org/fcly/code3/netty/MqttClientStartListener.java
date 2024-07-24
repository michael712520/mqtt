package org.fcly.code3.netty;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fcly
 * Date: 2022-11-16
 * Description: mqtt broker的启动
 */

@Component
public class MqttClientStartListener implements ApplicationRunner {
    @Resource
    MqttBroker mqttBroker;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        mqttBroker.start();
    }
}
