package example.rabbit.api.exchange.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import example.rabbit.RabbitUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 可以正则匹配的交换机
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

        String exchangeName = "test_topic_exchange";
        String exchangeType = "topic";
        String queueName = "test_topic_queue";
        String routeKey = "test.#";

        channel.exchangeDeclare(exchangeName, exchangeType);
        channel.queueDeclare(queueName, false, false, false, null);
        channel.queueBind(queueName, exchangeName, routeKey);
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}
