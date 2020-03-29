package example.rabbit.api.ack;

import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * @author trink
 */
public class Consumer {

    public static void main(String[] args) throws IOException {

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println("[x] Received: " + message);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if ((Integer) delivery.getProperties().getHeaders().get("num") % 2 == 0 && new Random().nextInt(10) > 5) {
                Constant.channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, true);
            } else {
                Constant.channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };
        // 手工签收
        Constant.channel.basicConsume(Constant.queueName, false, deliverCallback, consumerTag -> { });
    }
}
