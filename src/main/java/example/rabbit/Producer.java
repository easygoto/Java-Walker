package example.rabbit;

import com.rabbitmq.client.Channel;
import org.apache.commons.lang.RandomStringUtils;

import java.io.IOException;

/**
 * @author trink
 */
public class Producer {

    private static final String QUEUE_NAME = "test";

    public static void main(String[] args) {

        RabbitUtil rabbitUtil = new RabbitUtil();
        Channel channel = rabbitUtil.getChannel();

        try {
            int total = (int) 5e5, strLen = 150;
            for (int i = total; i > 0; i--) {
                String message = RandomStringUtils.randomAlphabetic(strLen);
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println(" [x] Sent '" + message + "'");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        rabbitUtil.close();
    }
}
