package example.rabbit.api.confirm;

import example.rabbit.RabbitUtil;

import java.io.IOException;

/**
 * @author trink
 */
public class Consumer {

    public static void main(String[] args) throws IOException {

        Constant.channel.basicConsume(Constant.queueName, true, RabbitUtil.deliverCallback, consumerTag -> { });
    }
}
