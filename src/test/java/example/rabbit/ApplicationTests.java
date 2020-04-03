package example.rabbit;

import example.rabbit.spring.Application;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Test
    public void testAdmin() {
        rabbitAdmin.declareExchange(new DirectExchange("test.spring.direct", false, false));
        rabbitAdmin.declareQueue(new Queue("test.spring.direct.queue", false));
        rabbitAdmin.declareBinding(new Binding("test.spring.direct.queue",
                Binding.DestinationType.QUEUE, "test.spring.direct", "direct", new HashMap<>()));

        rabbitAdmin.declareExchange(new TopicExchange("test.spring.topic", false, false));
        rabbitAdmin.declareQueue(new Queue("test.spring.topic.queue", false));
        rabbitAdmin.declareBinding(BindingBuilder.bind(new Queue("test.spring.topic.queue", false))
                .to(new TopicExchange("test.spring.topic", false, false)).with("user.#"));

        rabbitAdmin.declareExchange(new FanoutExchange("test.spring.fanout", false, false));
        rabbitAdmin.declareQueue(new Queue("test.spring.fanout.queue", false));
        rabbitAdmin.declareBinding(BindingBuilder.bind(new Queue("test.spring.fanout.queue", false))
                .to(new FanoutExchange("test.spring.fanout", false, false)));

        rabbitAdmin.purgeQueue("test.spring.direct.queue", false);
        rabbitAdmin.purgeQueue("test.spring.topic.queue", false);
        rabbitAdmin.purgeQueue("test.spring.fanout.queue", false);
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMessage() {
        String messageContent;
        Message message;

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("text/plain");
        messageProperties.getHeaders().put("desc", "详情描述");
        messageProperties.getHeaders().put("type", "自定义的消息类型");

        messageContent = RandomStringUtils.randomAlphanumeric(32);
        message = new Message(messageContent.getBytes(), messageProperties);
        rabbitTemplate.convertAndSend("test.spring.topic001", "spring.amqp", message, msg -> {
            msg.getMessageProperties().getHeaders().put("desc", "extra info desc");
            msg.getMessageProperties().getHeaders().put("attr", "extra new attr");
            return msg;
        });
        System.out.println("++++++++++++++++ 生产者: " + messageContent);

        messageContent = RandomStringUtils.randomAlphanumeric(32);
        message = new Message(messageContent.getBytes(), messageProperties);
        rabbitTemplate.convertAndSend("test.spring.topic001", "spring.test", message);
        System.out.println("++++++++++++++++ 生产者: " + messageContent);

        messageContent = RandomStringUtils.randomAlphanumeric(32);
        message = new Message(messageContent.getBytes(), messageProperties);
        rabbitTemplate.convertAndSend("test.spring.topic002", "rabbit.amqp", message);
        System.out.println("++++++++++++++++ 生产者: " + messageContent);

        messageContent = RandomStringUtils.randomAlphanumeric(32);
        message = new Message(messageContent.getBytes(), messageProperties);
        rabbitTemplate.send("test.spring.topic002", "rabbit.test", message);
        System.out.println("++++++++++++++++ 生产者: " + messageContent);
    }
}
