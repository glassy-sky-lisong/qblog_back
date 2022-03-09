package cn.quasar.blog;

import cn.quasar.blog.utils.StrTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StrTaskTests {

    @Autowired
    private StrTask strTask;

    @Test
    void test1() {
        boolean b = (boolean) strTask.start("hello").isEmpty().and().equals("hello").getData("boolean", true);
        System.out.println(b);
    }
}
