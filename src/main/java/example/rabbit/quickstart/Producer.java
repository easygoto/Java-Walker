package example.rabbit.quickstart;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import example.rabbit.RabbitUtil;
import org.apache.commons.lang.RandomStringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author trink
 */
public class Producer {

    public static void main(String[] args) throws IOException {

        RabbitUtil rabbitUtil = new RabbitUtil();
        Channel channel = rabbitUtil.getChannel();

        int total = (int) 5, strLen = 150;
        String queueName = "test00";
        for (int i = total; i > 0; i--) {
            String message = RandomStringUtils.randomAlphabetic(strLen);
            channel.queueDeclare(queueName, false, false, false, null);

            // 自定义属性
            Map<String, Object> headers = new HashMap<>(1);
            headers.put(RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));

            // expiration 过期时间, 单位是毫秒, 时间一到自动清除
            AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                    .deliveryMode(2).contentEncoding("UTF-8").expiration("10000").headers(headers).build();

            channel.basicPublish("", queueName, properties, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
        rabbitUtil.close();
    }
}
