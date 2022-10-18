package com.mycompany.app.Controller;

import com.mycompany.app.Model.Antique;
import com.mycompany.app.Model.Bidder;
import lombok.Data;
import redis.clients.jedis.Jedis;

import static org.apache.commons.lang3.SerializationUtils.serialize;

@Data
public class RedisController {
    private static RedisController instance;
    private Jedis jedis;
    private String antiqueSet;
    private String bidderSet;

    private RedisController() {
        jedis = new Jedis();
        antiqueSet = "antiques";
        bidderSet = "bidders";
    }

    public static RedisController getInstance() {
        if (instance == null)
            instance = new RedisController();
        return instance;
    }

    public int addAntique(Antique antique) {
        int stat = (int) jedis.sadd(antiqueSet.getBytes(), serialize(antique));
        jedis.expire(serialize(antique), 10);
        return stat;
    }

    public int registerBidder(Bidder bidder) {
        return (int) jedis.sadd(bidderSet.getBytes(), serialize(bidder));
    }

    public void auct(Antique antique, double price) {
        if (jedis.ttl(antique.getId()) < 10) {
            antique.setPrice(price);
            int stat = (int) jedis.sadd(antiqueSet.getBytes(), serialize(antique));
            jedis.expire(antiqueSet,10);
        }
    }

    public int bid(Antique antique, Bidder bidder) {
        if (jedis.ttl(antique.getId()) > 10) {
            int stat = (int) jedis.srem(antiqueSet, antique.getId());
            bidder.setBudget(bidder.getBudget() - antique.getPrice());
            return stat;
        }
        return -1;
    }
}
