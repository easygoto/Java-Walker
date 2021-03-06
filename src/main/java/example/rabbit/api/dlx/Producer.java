package example.rabbit.api.dlx;

import com.rabbitmq.client.AMQP;
import org.apache.commons.lang.RandomStringUtils;

import java.io.IOException;

/**
 * @author trink
 */
public class Producer {

    public static void main(String[] args) {

        int total = 5;
        for (int i = total; i > 0; i--) {
            addMessage();
        }
    }

    private static void addMessage() {

        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .deliveryMode(2).contentEncoding("UTF-8").expiration("10000").build();

        int strLen = 150;
        String message = RandomStringUtils.randomAlphabetic(strLen);
        try {
            Constant.channel.basicPublish(Constant.exchangeName, "dlx.save", true, properties, message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[x] Sent: " + message);
    }
}
