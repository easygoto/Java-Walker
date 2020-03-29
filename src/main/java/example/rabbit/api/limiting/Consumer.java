package example.rabbit.api.limiting;

import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author trink
 */
public class Consumer {

    public static void main(String[] args) throws IOException {

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            try {
                Thread.sleep(500);
                // 如果不手工应答, 消费端会一直卡在这里
                Constant.channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println("[x] Received: " + message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        // 手工签收
        Constant.channel.basicConsume(Constant.queueName, false, deliverCallback, consumerTag -> { });
    }
}
