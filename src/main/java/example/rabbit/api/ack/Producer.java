package example.rabbit.api.ack;

import com.rabbitmq.client.AMQP;
import org.apache.commons.lang.RandomStringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author trink
 */
public class Producer {

    public static void main(String[] args) {

        int total = 5;
        for (int i = 0; i < total; i++) {
            addMessage(i);
        }
    }

    private static void addMessage(int index) {

        Map<String, Object> headers = new HashMap<>(1);
        headers.put("num", index);
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .deliveryMode(2).contentEncoding("UTF-8").headers(headers).build();

        int strLen = 150;
        String message = RandomStringUtils.randomAlphabetic(strLen);
        try {
            Constant.channel.basicPublish(Constant.exchangeName, "ack.save", properties, message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[x] Sent: " + message);
    }
}
