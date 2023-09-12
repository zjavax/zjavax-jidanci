package cn.zjavax.zjavaxjidanci;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZjavaxJidanciApplication.class)
public class BaiduPoolTests {

    @Autowired
    private BaiduPoolRepository baiduPoolRepository;

    static String url = "https://pool.pm/stake/c1f5cfd4330339e90ba83a64d269a81cf415d7adab36403e27b910f7"; // 这个没有问题

    @Test
    public void addBaiduPool() {
        try {
            Connection connection = Jsoup.connect(url)
                    .ignoreContentType(true)
                    .userAgent("Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36") // User-Agent of Chrome 55
                    .referrer("https://www.tablenow.vn/ho-chi-minh/bo-suu-tap")
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .header("Accept", "application/json, text/plain, */*")
                    //                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("Accept-Language", "zh-CN,zh;q=0.9,ja;q=0.8,en;q=0.7")
                    .header("Connection", "keep-alive")
//                    .requestBody(json)
                    .maxBodySize(0)
                    .timeout(3000 * 10)
                    .method(Connection.Method.GET);

            Connection.Response response = connection.execute();
            String body = response.body();

            LinkedHashMap<String, StakeInfo> map= JSON.parseObject(body ,new TypeReference<LinkedHashMap<String, StakeInfo>>(){}, Feature.OrderedField);

            BaiduPool baiduPool = new BaiduPool();
            for(Map.Entry<String, StakeInfo> entry:map.entrySet() ) {
                if (!entry.getValue().isOwner() && entry.getValue().getTotalAda() >= 10000) {

                    System.out.println(entry.getKey() + "+" + entry.getValue().getTotalAda() + "+" + entry.getValue().getEpoch());
                    baiduPool.stakeAddress12 = entry.getKey().substring(0,12);
                    baiduPool.stakeAddress = entry.getKey();
                    baiduPool.totalAda = entry.getValue().getTotalAda();
                    baiduPool.epoch = entry.getValue().getEpoch();

                    baiduPoolRepository.save(baiduPool);
                }

            }





        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class StakeInfo {
    int epoch;
    BigInteger amount;
    boolean owner;
    BigInteger reward;


    public int getEpoch() {
        return epoch;
    }

    public void setEpoch(int epoch) {
        this.epoch = epoch;
    }



    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    public BigInteger getReward() {
        return reward;
    }

    public void setReward(BigInteger reward) {
        this.reward = reward;
    }

    public int getTotalAda() {
        return Integer.valueOf(this.getAmount().add(this.getReward()).divide(BigInteger.valueOf(1000000)).toString());
    }
}

