package too.simple.exemple;

import too.simple.Page;
import too.simple.Request;
import too.simple.Site;
import too.simple.Spider;
import too.simple.processor.PageProcessor;

/**
 * Create by JIUN·LIU at 2018/10/16
 */
public class OriginUser implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);
    @Override
    public void process(Page page) {

        System.out.println("================");
    }

    @Override
    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {
        Request request = new Request();
        request.setUrl("https://www.cnblogs.com/gonjan-blog/p/6538984.html");
        Spider.create(new OriginUser()).addRequest(request).run();
    }
}
