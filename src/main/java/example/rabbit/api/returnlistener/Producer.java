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
        String message = RandomStringUtils.randomAlphabetic(strLen);
        // 第三个参数表示是否返回在生产端
        try {
            Constant.channel.basicPublish(Constant.exchangeName, Constant.routingKey, true, null,
                    message.getBytes());
            Constant.channel.basicPublish(Constant.exchangeName, Constant.routingKeyError, true, null,
                    message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[x] Sent: " + message);
    }
}
