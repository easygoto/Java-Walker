package example.rabbit.api.confirm;

/**
 * @author trink
 */
public class Constant {

    public static String routingKey = "confirm.#";

    public static String exchangeName = "test_confirm_exchange";

    public static String exchangeType = "topic";

    public static String queueName = "test_confirm_queue";
}
