package example.rabbit.api.returnlistener;

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
        channel.exchangeDeclare(Constant.exchangeName, Constant.exchangeType, true, false, null);
        channel.queueDeclare(Constant.queueName, true, false, false, null);
        channel.queueBind(Constant.queueName, Constant.exchangeName, "return.#");

        channel.basicConsume(Constant.queueName, true, RabbitUtil.deliverCallback, consumerTag -> { });
    }
}
