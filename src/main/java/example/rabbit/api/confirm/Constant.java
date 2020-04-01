package example.rabbit.api.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import example.rabbit.RabbitUtil;

import java.io.IOException;

/**
 * @author trink
 */
public class Constant {

    public static String routingKey = "confirm.#";

    public static String exchangeName = "test_confirm_exchange";

    public static String exchangeType = "topic";

    public static String queueName = "test_confirm_queue";

    public static Channel channel;

    static {

        RabbitUtil rabbitUtil = new RabbitUtil();
        channel = rabbitUtil.newChannel();

        try {
            channel.exchangeDeclare(exchangeName, exchangeType, true);
            channel.queueDeclare(queueName, true, false, false, null);
            channel.queueBind(queueName, exchangeName, routingKey);
            channel.confirmSelect();
            channel.addConfirmListener(new ConfirmListener() {
                @Override
                public void handleAck(long deliveryTag, boolean multiple) {
                    System.out.println(deliveryTag + ", ack: " + multiple);
                }

                @Override
                public void handleNack(long deliveryTag, boolean multiple) {
                    System.out.println(deliveryTag + ", no ack: " + multiple);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
