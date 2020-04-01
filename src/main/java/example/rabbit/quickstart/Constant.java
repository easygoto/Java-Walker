package example.rabbit.quickstart;

import com.rabbitmq.client.Channel;
import example.rabbit.RabbitUtil;

import java.io.IOException;

/**
 * @author trink
 */
public class Constant {

    public static String queueName = "test00";

    public static Channel channel;

    static {

        RabbitUtil rabbitUtil = new RabbitUtil();
        channel = rabbitUtil.newChannel();

        try {
            channel.queueDeclare(Constant.queueName, false, false, false, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
