package cn.zjavax.minswap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

public class GetLimitOrders {

    static String url = "https://app.minswap.org/swap?currencySymbolA=&tokenNameA=&currencySymbolB=5d16cc1a177b5d9ba9cfa9793b07e60f1fb70fea1f8aef064415d114&tokenNameB=494147";


    public static void main3(String[] args) {



        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);





        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String html = EntityUtils.toString(entity);

            // 将获取到的 HTML 内容传递给 Jsoup 进行解析
            Document document = Jsoup.parse(html);
            Elements divElement = document.select("div[id=__next]");


            // 在这里可以使用 Jsoup 提供的方法来解析和提取页面中的数据
            // 例如：获取标题
            String title = document.title();
            System.out.println("页面标题：" + title);

            // 其他解析操作...
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "D:\\soft_zx\\jar\\geckodriver.exe");

        Document document;
        try {

            WebDriver driver = new FirefoxDriver();

            driver.get(url);

            document = Jsoup.parse(driver.getPageSource());


//            document = Jsoup.connect("").ignoreContentType(true).timeout(50000000).maxBodySize(0).get();


            Elements divElement = document.select("div[id=__next]");
            Elements select = divElement.select("div[class=relative flex min-h-full w-full flex-col bg-fixed font-main bg-light dark\\:bg-dark]");

            System.out.println("123");


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void main2(){

        Document document;
        try {





            document = Jsoup.connect("").ignoreContentType(true).timeout(50000000).maxBodySize(0).get();


            Elements divElement = document.select("div[id=__next]");
            Elements select = divElement.select("div[class=relative flex min-h-full w-full flex-col bg-fixed font-main bg-light dark\\:bg-dark]");

            System.out.println("123");


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}

