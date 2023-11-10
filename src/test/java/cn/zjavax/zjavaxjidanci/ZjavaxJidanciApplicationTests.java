package cn.zjavax.zjavaxjidanci;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZjavaxJidanciApplicationTests {


    public static void main(String[] args) {
        String tmp="";
        for(int i=1337;i<1337+15;i++){
            tmp=tmp+i+",";
        }
        System.out.println(tmp);

    }

	@Test
	void contextLoads() {
	}

}
