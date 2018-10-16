package too.simple;

import org.junit.Test;
import too.simple.selector.Html;
import too.simple.utils.HttpConstant;

import java.util.HashMap;

/**
 * Create by JIUN·LIU at 2018/10/16
 */
public class SpiderTest {


    @Test
    public void test() {
        Spider excute = Spider.connect("https://www.cnblogs.com/gonjan-blog/p/6538984.html")
                .method(HttpConstant.Method.GET)
                .retryTimes(3)
                .excute();
        Page page = excute.page;
        Html html = page.getHtml();
        System.out.println(html);
        String charset = page.getCharset();
        System.err.println(charset);
    }
}
