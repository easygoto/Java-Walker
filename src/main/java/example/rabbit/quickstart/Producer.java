package example.rabbit.quickstart;

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
        for (int i = total; i > 0; i--) {
            addMessage();
        }
    }

    private static void addMessage() {

        Map<String, Object> headers = new HashMap<>(1);
        headers.put(RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));

        // expiration 过期时间, 单位是毫秒, 时间一到自动清除
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .deliveryMode(2).contentEncoding("UTF-8").expiration("10000").headers(headers).build();

        int strLen = 150;
        String message = RandomStringUtils.randomAlphabetic(strLen);
        try {
            Constant.channel.basicPublish("", Constant.queueName, properties, message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[x] Sent: " + message);
    }
}
