package example.rabbit.api.dlx;

import com.rabbitmq.client.Channel;
import example.rabbit.RabbitUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author trink
 */
public class Consumer {

    public static void main(String[] args) throws IOException {

        Constant.channel.basicConsume(Constant.queueName, true, RabbitUtil.deliverCallback, consumerTag -> { });
        Constant.channel.basicConsume(Constant.dlxQueueName, true, RabbitUtil.deliverCallback, consumerTag -> { });
    }
}
