package example.rabbit.api.returnlistener;

import com.rabbitmq.client.Channel;
import example.rabbit.RabbitUtil;

import java.io.IOException;

/**
 * @author trink
 */
public class Constant {

    public static String routingKey = "return.save";

    public static String routingKeyError = "abc.save";

    public static String exchangeName = "test_return_exchange";

    public static String exchangeType = "topic";

    public static String queueName = "test_return_queue";

    public static Channel channel;

    static {

        RabbitUtil rabbitUtil = new RabbitUtil();
        channel = rabbitUtil.newChannel();

        try {
            channel.exchangeDeclare(exchangeName, exchangeType, true, false, null);
            channel.queueDeclare(queueName, true, false, false, null);
            channel.queueBind(queueName, exchangeName, "return.#");
            channel.addReturnListener((replyCode, replyText, exchange, routingKey, properties, body) -> {
                System.err.println("---------handle  return----------");
                System.err.println("replyCode: " + replyCode);
                System.err.println("replyText: " + replyText);
                System.err.println("exchange: " + exchange);
                System.err.println("routingKey: " + routingKey);
                System.err.println("properties: " + properties);
                System.err.println("body: " + new String(body));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
