package com.mycompany.app;

import com.mycompany.app.Model.Antique;
import com.mycompany.app.Model.Bidder;
import redis.clients.jedis.Jedis;

import java.util.HashSet;
import java.util.Set;

/**
 * Hello world!
 */
public class App {
    static Set<Antique> antiqueSet = new HashSet<>();
    static Set<Bidder> bidderSet = new HashSet<>();
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello World!");
        Jedis jedis = new Jedis();
//        jedis.set("events/city/rome", "32,15,223,828");
//        jedis.expire("events/city/rome", 4);
//        String cachedResponse = jedis.get("events/city/rome");
//
//        System.out.println(jedis.ttl("events/city/rome"));
//        Thread.sleep(5000);
//        System.out.println(jedis.ttl("events/city/rome"));

        jedis.sadd("k", "ww", "w2", "w3");
        System.out.println(jedis.smembers("k"));
        jedis.expire("k", 3);
        Thread.sleep(4000);
        System.out.println(jedis.smembers("k"));

        Bidder b1 = new Bidder("kpk", "mme", 788.9);

//        jedis.set("b1".getBytes(), SerializationUtils.serialize(b1));
//        Bidder b2 = SerializationUtils.deserialize(jedis.get("b1".getBytes()));
//        System.out.println(b2);
    }
}
