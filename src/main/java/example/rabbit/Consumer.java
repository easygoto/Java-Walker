package example.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author trink
 */
public class Consumer {

    private static final String QUEUE_NAME = "test";

    public static void main(String[] args) {

        RabbitUtil rabbitUtil = new RabbitUtil();
        Channel channel = rabbitUtil.getChannel();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
        };

        try {
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
