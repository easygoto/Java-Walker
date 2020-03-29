package example.rabbit.api.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
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

        // 设置应答
        channel.exchangeDeclare(Constant.exchangeName, Constant.exchangeType, true);
        channel.queueDeclare(Constant.queueName, true, false, false, null);
        channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println(deliveryTag + ", ack: " + multiple);
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println(deliveryTag + ", no ack: " + multiple);
            }
        });

        int total = 5, strLen = 150;
        for (int i = total; i > 0; i--) {
            String message = RandomStringUtils.randomAlphabetic(strLen);
            channel.basicPublish(Constant.exchangeName, "confirm.save", null, message.getBytes());
            System.out.println("[x] Sent: " + message);
        }
    }
}
