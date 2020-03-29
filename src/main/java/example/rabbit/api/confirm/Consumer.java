package example.rabbit.api.confirm;

import com.rabbitmq.client.Channel;
import example.rabbit.RabbitUtil;

import java.io.IOException;

/**
 * @author trink
 */
public class Consumer {

    public static void main(String[] args) throws IOException {

        RabbitUtil rabbitUtil = new RabbitUtil();
        Channel channel = rabbitUtil.getChannel();

        // 绑定
        channel.exchangeDeclare(Constant.exchangeName, Constant.exchangeType, true);
        channel.queueDeclare(Constant.queueName, true, false, false, null);
        channel.queueBind(Constant.queueName, Constant.exchangeName, Constant.routingKey);
        channel.basicConsume(Constant.queueName, true, RabbitUtil.deliverCallback, consumerTag -> { });
    }
}
