package example.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author trink
 */
public class RabbitUtil {

    Connection connection = null;

    public RabbitUtil() {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/test");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        try {
            connection = connectionFactory.newConnection();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public Channel newChannel() {
        Channel channel = null;
        try {
            channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return channel;
    }

    public static DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        // 属性可以使用 delivery.getProperties() 获取
        String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
        System.out.println("[x] Received: " + message);
    };
}
