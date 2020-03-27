package example.rabbit.api.exchange.fanout;

import com.rabbitmq.client.Channel;
import example.rabbit.RabbitUtil;
import org.apache.commons.lang.RandomStringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 不会走路由键的交换机
 *
 * @author trink
 */
public class Producer {

    public static void main(String[] args) throws IOException {

        RabbitUtil rabbitUtil = new RabbitUtil();
        Channel channel = rabbitUtil.getChannel();

        int total = (int) 1e4, strLen = 150;
        String exchangeName = "test_fanout_exchange";
        String routeKey = "";
        channel.queueDeclare(exchangeName, false, false, false, null);

        for (int i = total; i > 0; i--) {
            String message = RandomStringUtils.randomAlphabetic(strLen);
            channel.basicPublish(exchangeName, routeKey, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }
        rabbitUtil.close();
    }
}
