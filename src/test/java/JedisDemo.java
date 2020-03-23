import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class JedisDemo {

    private Jedis  client;
    private Random random;

    @Before
    public void init() {
        client = new Jedis("127.0.0.1", 6379);
        client.auth("123123");
        client.connect();

        random = new Random();
    }

    @Test
    public void addStringKeys() {
        for (int i = 0; i < 5; i++) {
            String key = UUID.randomUUID().toString();
            client.set(key, RandomStringUtils.randomAlphabetic(100));
            System.out.println(key + " added ...");
        }

        for (int i = 0; i < 5; i++) {
            String key = UUID.randomUUID().toString();
            int len = random.nextInt(15) + 5;
            for (int j = 1; j < len; j++) {
                client.lpush(key, RandomStringUtils.randomAlphabetic(100));
            }
            System.out.println(key + " added ...");
        }

        for (int i = 0; i < 5; i++) {
            String key = UUID.randomUUID().toString();
            int len = random.nextInt(15) + 5;
            for (int j = 0; j < len; j++) {
                String field = RandomStringUtils.randomAlphabetic(10);
                client.hset(key, field, RandomStringUtils.randomAlphabetic(100));
            }
            System.out.println(key + " added ...");
        }

        for (int i = 0; i < 5; i++) {
            String key = UUID.randomUUID().toString();
            int len = random.nextInt(15) + 5;
            for (int j = 1; j < len; j++) {
                client.sadd(key, RandomStringUtils.randomAlphabetic(100));
            }
            System.out.println(key + " added ...");
        }

        for (int i = 0; i < 5; i++) {
            String key = UUID.randomUUID().toString();
            int len = random.nextInt(15) + 5;
            for (int j = 1; j < len; j++) {
                client.zadd(key, random.nextDouble(), RandomStringUtils.randomAlphabetic(100));
            }
            System.out.println(key + " added ...");
        }
    }

    @Test
    public void showKeys() {
        for (String key : client.keys("*")) {
            switch (client.type(key)) {
                case "string":
                    showString(key);
                    break;
                case "list":
                    showList(key);
                    break;
                case "hash":
                    showHash(key);
                    break;
                case "set":
                    showSet(key);
                    break;
                case "zset":
                    showZset(key);
                    break;
                default:
                    break;
            }
        }
        client.flushDB();
    }

    private void showZset(String key) {
        System.out.println("zset :");
        System.out.println(key + " :");
        for (Tuple tuple : client.zrangeWithScores(key, 0, -1)) {
            System.out.println(tuple.getElement() + " : " + tuple.getScore());
        }
        System.out.println();
    }

    private void showSet(String key) {
        System.out.println("set :");
        System.out.println(key + " :");
        for (String value : client.smembers(key)) {
            System.out.println(value);
        }
        System.out.println();
    }

    private void showHash(String key) {
        System.out.println("hash :");
        System.out.println(key + " :");
        for (Map.Entry<String, String> entry : client.hgetAll(key).entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println();
    }

    private void showList(String key) {
        System.out.println("list :");
        System.out.println(key + " :");
        for (String value : client.lrange(key, 0, -1)) {
            System.out.println(value);
        }
        System.out.println();
    }

    private void showString(String key) {
        System.out.println("string :");
        System.out.println(key + " :");
        System.out.println(client.get(key));
        System.out.println();
    }
}
