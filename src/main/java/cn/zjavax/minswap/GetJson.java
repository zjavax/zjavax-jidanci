package cn.zjavax.minswap;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class GetJson {
    public static void main(String[] args) throws IOException {
        File file=new File("D:\\java_code\\jidanci\\zjavax-jidanci\\src\\main\\java\\cn\\zjavax\\minswap\\lifi1.json");
        String content= FileUtils.readFileToString(file,"UTF-8");

        List<LimitSellOrder> limitSellOrders = JSONObject.parseArray(content, LimitSellOrder.class);
        limitSellOrders.sort(Comparator.comparingDouble(LimitSellOrder::getPrice));

        System.out.println(limitSellOrders.size());
        for (LimitSellOrder order : limitSellOrders) {
            System.out.println("Price: " + order.getPrice() + ", Input: " + order.getInput());
        }


    }
}


@Getter
@Setter
class LimitSellOrder{
    double price;
    double input;
}
