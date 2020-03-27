package example.rabbit.api.exchange.direct;

import com.rabbitmq.client.Channel;
import example.rabbit.RabbitUtil;
import org.apache.commons.lang.RandomStringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 完全匹配路由的交换机
 *
 * @author trink
 */
public class Producer {

    public static void main(String[] args) throws IOException {

        RabbitUtil rabbitUtil = new RabbitUtil();
        Channel channel = rabbitUtil.getChannel();

        int total = (int) 1e4, strLen = 150;
        String exchangeName = "test_direct_exchange";
        String routeKey = "test.direct";
        channel.queueDeclare(exchangeName, false, false, false, null);

        for (int i = total; i > 0; i--) {
            String message = RandomStringUtils.randomAlphabetic(strLen);
            channel.basicPublish(exchangeName, routeKey, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }
        rabbitUtil.close();
    }
}
