package example.rabbit.api.exchange;

import com.rabbitmq.client.Channel;
import example.rabbit.RabbitUtil;

import java.io.IOException;

/**
 * @author trink
 */
public class Constant {

    public static String directExchangeName = "test_direct_exchange";

    public static String directExchangeType = "direct";

    public static String directQueueName = "test_direct_queue";

    public static String directRouteKey = "test.direct";

    public static String topicExchangeName = "test_topic_exchange";

    public static String topicExchangeType = "topic";

    public static String topicQueueName = "test_topic_queue";

    public static String topicRouteKey = "test.#";

    public static String fanoutExchangeName = "test_fanout_exchange";

    public static String fanoutExchangeType = "fanout";

    public static String fanoutQueueName = "test_fanout_queue";

    /**
     * 即使指定也不会起作用
     */
    public static String fanoutRouteKey = "";

    public static Channel directChannel;

    public static Channel topicChannel;

    public static Channel fanoutChannel;

    static {

        RabbitUtil rabbitUtil = new RabbitUtil();
        directChannel = rabbitUtil.newChannel();
        topicChannel = rabbitUtil.newChannel();
        fanoutChannel = rabbitUtil.newChannel();

        try {
            directChannel.exchangeDeclare(directExchangeName, directExchangeType);
            directChannel.queueDeclare(directQueueName, false, false, false, null);
            directChannel.queueBind(directQueueName, directExchangeName, directRouteKey);

            topicChannel.exchangeDeclare(topicExchangeName, topicExchangeType);
            topicChannel.queueDeclare(topicQueueName, false, false, false, null);
            topicChannel.queueBind(topicQueueName, topicExchangeName, topicRouteKey);

            fanoutChannel.exchangeDeclare(fanoutExchangeName, fanoutExchangeType);
            fanoutChannel.queueDeclare(fanoutQueueName, false, false, false, null);
            fanoutChannel.queueBind(fanoutQueueName, fanoutExchangeName, fanoutRouteKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
