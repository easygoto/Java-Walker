package example.rabbit.api.dlx;

import com.rabbitmq.client.Channel;
import example.rabbit.RabbitUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author trink
 */
public class Constant {

    public static String exchangeType = "topic";

    public static String routingKey = "dlx.#";

    public static String exchangeName = "test_dlx_exchange";

    public static String queueName = "test_dlx_queue";

    public static String dlxRoutingKey = "#";

    public static String dlxExchangeName = "dlx.exchange";

    public static String dlxQueueName = "dlx.queue";

    public static Channel channel = null;

    static {

        RabbitUtil rabbitUtil = new RabbitUtil();
        channel = rabbitUtil.newChannel();

        try {
            Map<String, Object> arguments = new HashMap<>(1);
            arguments.put("x-dead-letter-exchange", dlxExchangeName);
            channel.exchangeDeclare(exchangeName, exchangeType);
            channel.queueDeclare(queueName, false, false, false, arguments);
            channel.queueBind(queueName, exchangeName, routingKey);

            // 声明死信队列
            channel.exchangeDeclare(dlxExchangeName, exchangeType, true, false, null);
            channel.queueDeclare(dlxQueueName, true, false, false, null);
            channel.queueBind(dlxQueueName, dlxExchangeName, dlxRoutingKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
