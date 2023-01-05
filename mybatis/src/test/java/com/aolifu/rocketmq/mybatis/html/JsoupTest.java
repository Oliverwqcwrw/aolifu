package com.aolifu.rocketmq.mybatis.html;

import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class JsoupTest {

    @Test
    public void parseHtmlTest() throws IOException {
        File input = new File("/Users/wangqiang/Downloads/temp/sales.html");
        final Document parse = Jsoup.parse(input, "UTF-8");
        System.out.println(parse);
    }
}
