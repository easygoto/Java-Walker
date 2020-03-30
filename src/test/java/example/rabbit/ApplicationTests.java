package example.rabbit;

import example.rabbit.spring.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
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
}
