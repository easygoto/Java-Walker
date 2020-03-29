package example.rabbit.api.returnlistener;

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
        channel.exchangeDeclare(Constant.exchangeName, Constant.exchangeType, true, false, null);
        channel.queueDeclare(Constant.queueName, true, false, false, null);
        channel.addReturnListener((replyCode, replyText, exchange, routingKey, properties, body) -> {
            System.err.println("---------handle  return----------");
            System.err.println("replyCode: " + replyCode);
            System.err.println("replyText: " + replyText);
            System.err.println("exchange: " + exchange);
            System.err.println("routingKey: " + routingKey);
            System.err.println("properties: " + properties);
            System.err.println("body: " + new String(body));
        });

        int total = 5, strLen = 150;
        for (int i = total; i > 0; i--) {
            String message = RandomStringUtils.randomAlphabetic(strLen);
            // 第三个参数表示是否返回在生产端
            channel.basicPublish(Constant.exchangeName, Constant.routingKey, true, null, message.getBytes());
            channel.basicPublish(Constant.exchangeName, Constant.routingKeyError, true, null, message.getBytes());
            System.out.println("[x] Sent: " + message);
        }
    }
}
