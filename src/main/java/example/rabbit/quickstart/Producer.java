package example.rabbit.quickstart;

import com.rabbitmq.client.Channel;
import example.rabbit.RabbitUtil;
import org.apache.commons.lang.RandomStringUtils;

import java.io.IOException;

/**
 * @author trink
 */
public class Producer {

    public static void main(String[] args) throws IOException {

        RabbitUtil rabbitUtil = new RabbitUtil();
        Channel channel = rabbitUtil.getChannel();

        int total = (int) 5e4, strLen = 150;
        String queueName = "test00";
        for (int i = total; i > 0; i--) {
            String message = RandomStringUtils.randomAlphabetic(strLen);
            channel.queueDeclare(queueName, false, false, false, null);
            channel.basicPublish("", queueName, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
        rabbitUtil.close();
    }
}
