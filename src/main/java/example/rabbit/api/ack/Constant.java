package example.rabbit.api.ack;

import com.rabbitmq.client.Channel;
import example.rabbit.RabbitUtil;

import java.io.IOException;

/**
 * @author trink
 */
public class Constant {

    public static String routingKey = "ack.#";

    public static String exchangeName = "test_ack_exchange";

    public static String exchangeType = "topic";

    public static String queueName = "test_ack_queue";

    public static Channel channel;

    static {

        RabbitUtil rabbitUtil = new RabbitUtil();
        channel = rabbitUtil.newChannel();

        // 设置应答
        try {
            channel.exchangeDeclare(Constant.exchangeName, Constant.exchangeType, true, false, null);
            channel.queueDeclare(Constant.queueName, true, false, false, null);
            channel.queueBind(Constant.queueName, Constant.exchangeName, Constant.routingKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
