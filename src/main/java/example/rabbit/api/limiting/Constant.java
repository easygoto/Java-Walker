package example.rabbit.api.limiting;

import com.rabbitmq.client.Channel;
import example.rabbit.RabbitUtil;

import java.io.IOException;

/**
 * @author trink
 */
public class Constant {

    public static String routingKey = "qos.#";

    public static String exchangeName = "test_qos_exchange";

    public static String exchangeType = "topic";

    public static String queueName = "test_qos_queue";

    public static Channel channel;

    static {

        RabbitUtil rabbitUtil = new RabbitUtil();
        channel = rabbitUtil.newChannel();

        try {
            channel.exchangeDeclare(exchangeName, exchangeType, true, false, null);
            channel.queueDeclare(queueName, true, false, false, null);
            channel.queueBind(queueName, exchangeName, routingKey);
            channel.basicQos(0, 1, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
