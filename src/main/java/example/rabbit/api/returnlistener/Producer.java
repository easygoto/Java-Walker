package example.rabbit.api.returnlistener;

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

        int strLen = 150;
        String message;
        // 第三个参数表示是否返回在生产端
        try {
            message = RandomStringUtils.randomAlphabetic(strLen);
            Constant.channel.basicPublish(Constant.exchangeName, Constant.routingKey, true, null,
                    message.getBytes());
            System.out.println("[x] Sent: " + message);

            message = RandomStringUtils.randomAlphabetic(strLen);
            Constant.channel.basicPublish(Constant.exchangeName, Constant.routingKeyError, true, null,
                    message.getBytes());
            System.out.println("[x] Sent: " + message + " ---- Error");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
