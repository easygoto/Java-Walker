package example.rabbit.api.exchange.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import example.rabbit.RabbitUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 不会走路由键的交换机
 *
 * @author trink
 */
public class Consumer {

    public static void main(String[] args) throws IOException {

        RabbitUtil rabbitUtil = new RabbitUtil();
        Channel channel = rabbitUtil.getChannel();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
        };

        String exchangeName = "test_fanout_exchange";
        String exchangeType = "fanout";
        String queueName = "test_fanout_queue";
        String routeKey = "";

        channel.exchangeDeclare(exchangeName, exchangeType);
        channel.queueDeclare(queueName, false, false, false, null);
        channel.queueBind(queueName, exchangeName, routeKey);
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}
