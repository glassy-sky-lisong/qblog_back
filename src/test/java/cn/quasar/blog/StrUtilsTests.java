package cn.quasar.blog;

import cn.quasar.blog.utils.StrUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class StrUtilsTests {

    @Test
    void StrSplit() {
        String demo = "Vue,React,Pug,SpringBoot";

        List<String> strings = StrUtils.splitStr(demo, ",");
        String s = StrUtils.mergeList(strings, ",");
        System.out.println(s);
    }

    @Test
    void test1() {
        String demo = "Vue,React,Pug,SpringBoot";
        String substring = demo.substring(0, demo.indexOf(","));
        System.out.println(substring);
        System.out.println(demo.substring(demo.indexOf(","), demo.length()));
    }
}
