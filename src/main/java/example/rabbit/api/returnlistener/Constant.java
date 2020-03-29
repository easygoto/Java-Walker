package example.rabbit.api.returnlistener;

/**
 * @author trink
 */
public class Constant {

    public static String routingKey = "return.save";

    public static String routingKeyError = "abc.save";

    public static String exchangeName = "test_return_exchange";

    public static String exchangeType = "topic";

    public static String queueName = "test_return_queue";
}
