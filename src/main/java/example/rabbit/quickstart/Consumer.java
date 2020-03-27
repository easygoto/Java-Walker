package example.rabbit.quickstart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import example.rabbit.RabbitUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
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

        String queueName = "test00";
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}
