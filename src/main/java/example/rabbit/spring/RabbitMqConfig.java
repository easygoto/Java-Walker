package example.rabbit.spring;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * @author trink
 */
@Configuration
@ComponentScan({"example.rabbit.spring"})
public class RabbitMqConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses("127.0.0.1:5672");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/test");
        return connectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        // 该类封装了对 RabbitMQ 的管理操作
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    @Bean
    public TopicExchange exchange001() {
        return new TopicExchange("test.spring.topic001", true, false);
    }

    @Bean
    public TopicExchange exchange002() {
        return new TopicExchange("test.spring.topic002", true, false);
    }

    @Bean
    public Queue queue001() {
        return new Queue("test.spring.queue001", true);
    }

    @Bean
    public Queue queue002() {
        return new Queue("test.spring.queue002", true);
    }

    @Bean
    public Queue queue003() {
        return new Queue("test.spring.queue003", true);
    }

    @Bean
    public Binding binding001() {
        return BindingBuilder.bind(queue001()).to(exchange001()).with("spring.*");
    }

    @Bean
    public Binding binding002() {
        return BindingBuilder.bind(queue002()).to(exchange002()).with("rabbit.*");
    }

    @Bean
    public Binding binding003() {
        return BindingBuilder.bind(queue003()).to(exchange001()).with("mq.*");
    }

    @Bean
    public Queue queueImage() {
        return new Queue("test.spring.image.queue", true);
    }

    @Bean
    public Queue queuePdf() {
        return new Queue("test.spring.pdf.queue", true);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        // 简化版 RabbitMQ 发送和接收消息操作
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public SimpleMessageListenerContainer messageContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(queue001(), queue002(), queue003(), queueImage(), queuePdf());
        // 当前消费者数量
        container.setConcurrentConsumers(1);
        // 最大消费者数量
        container.setMaxConcurrentConsumers(5);
        // 是否重回队列
        container.setDefaultRequeueRejected(false);
        // 自动签收
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        // 消费者标签生成策略
        container.setConsumerTagStrategy(queue -> queue + ": " + UUID.randomUUID().toString());

        container.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {
            String msg = new String(message.getBody());
            System.out.println("---------------- 消费者: " + msg);
        });
        return container;
    }
}
