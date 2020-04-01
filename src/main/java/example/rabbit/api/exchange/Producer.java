package example.rabbit.api.exchange;

import org.apache.commons.lang.RandomStringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author trink
 */
public class Producer {

    public static void main(String[] args) {

        int total = 3;
        for (int i = total; i > 0; i--) {
            addMessage();
        }
    }

    private static void addMessage() {

        int strLen = 150;
        String message = null;
        try {
            message = RandomStringUtils.randomAlphabetic(strLen);
            Constant.directChannel.basicPublish(Constant.directExchangeName, Constant.directRouteKey, null,
                    message.getBytes(StandardCharsets.UTF_8));
            System.out.println("[x] Sent: " + message);

            message = RandomStringUtils.randomAlphabetic(strLen);
            Constant.topicChannel.basicPublish(Constant.topicExchangeName, "test.init", null,
                    message.getBytes(StandardCharsets.UTF_8));
            System.out.println("[x] Sent: " + message);
            message = RandomStringUtils.randomAlphabetic(strLen);
            Constant.topicChannel.basicPublish(Constant.topicExchangeName, "test.create", null,
                    message.getBytes(StandardCharsets.UTF_8));
            System.out.println("[x] Sent: " + message);
            message = RandomStringUtils.randomAlphabetic(strLen);
            Constant.topicChannel.basicPublish(Constant.topicExchangeName, "test.send.abc", null,
                    message.getBytes(StandardCharsets.UTF_8));
            System.out.println("[x] Sent: " + message);

            message = RandomStringUtils.randomAlphabetic(strLen);
            Constant.fanoutChannel.basicPublish(Constant.fanoutExchangeName, Constant.fanoutRouteKey, null,
                    message.getBytes(StandardCharsets.UTF_8));
            System.out.println("[x] Sent: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
