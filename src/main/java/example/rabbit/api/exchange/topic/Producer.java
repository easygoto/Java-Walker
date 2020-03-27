package example.rabbit.api.exchange.topic;

import com.rabbitmq.client.Channel;
import example.rabbit.RabbitUtil;
import org.apache.commons.lang.RandomStringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 可以正则匹配的交换机
 *
 * @author trink
 */
public class Producer {

    public static void main(String[] args) throws IOException {

        RabbitUtil rabbitUtil = new RabbitUtil();
        Channel channel = rabbitUtil.getChannel();

        int total = (int) 1e4, strLen = 150;
        String exchangeName = "test_topic_exchange";
        String routeKey1 = "test.init";
        String routeKey2 = "test.create";
        String routeKey3 = "test.send.abc";
        channel.queueDeclare(exchangeName, false, false, false, null);

        for (int i = total; i > 0; i--) {
            String message = RandomStringUtils.randomAlphabetic(strLen);
            channel.basicPublish(exchangeName, routeKey1, null, message.getBytes(StandardCharsets.UTF_8));
            channel.basicPublish(exchangeName, routeKey2, null, message.getBytes(StandardCharsets.UTF_8));
            channel.basicPublish(exchangeName, routeKey3, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }
        rabbitUtil.close();
    }
}
