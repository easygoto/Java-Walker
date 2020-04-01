package example.rabbit.api.confirm;

import org.apache.commons.lang.RandomStringUtils;

import java.io.IOException;

/**
 * @author trink
 */
public class Producer {

    public static void main(String[] args) {

        int total = 5;
        for (int i = 0; i < total; i++) {
            addMessage();
        }
    }

    private static void addMessage() {

        int strLen = 150;
        String message = RandomStringUtils.randomAlphabetic(strLen);
        try {
            Constant.channel.basicPublish(Constant.exchangeName, "confirm.save", null, message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[x] Sent: " + message);
    }
}
