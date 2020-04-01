package example.rabbit.api.exchange;

import example.rabbit.RabbitUtil;

import java.io.IOException;

/**
 * @author trink
 */
public class Consumer {

    public static void main(String[] args) throws IOException {

        Constant.directChannel.basicConsume(
                Constant.directQueueName, true, RabbitUtil.deliverCallback, consumerTag -> { });

        Constant.topicChannel.basicConsume(
                Constant.topicQueueName, true, RabbitUtil.deliverCallback, consumerTag -> { });

        Constant.fanoutChannel.basicConsume(
                Constant.fanoutQueueName, true, RabbitUtil.deliverCallback, consumerTag -> { });
    }
}
