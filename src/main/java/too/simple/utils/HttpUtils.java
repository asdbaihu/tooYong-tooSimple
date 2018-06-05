package too.simple.utils;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create by JIUN LIU on 2018/6/5.
 */
public class HttpUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 通过响应头中set-Cookie来获取response中的cookie信息
     *
     * @param response
     * @return
     */
    public static List<Cookie> getResponseCookies(HttpResponse response) {
        List<Cookie> cookies = null;
        Header[] hds = response.getAllHeaders();
        if (hds != null && hds.length > 0) {
            for (int i = 0; i < hds.length; i++) {
                logger.info("response-Header:" + hds[i].getName() + "  =   " + hds[i].getValue());
                if (hds[i].getName().equalsIgnoreCase("Set-Cookie")) {
                    if (cookies == null) {
                        cookies = new ArrayList<Cookie>();
                    }
                    String cookiestring[] = hds[i].getValue().split(";");
                    String ss[] = cookiestring[0].split("=", 2);
                    String cookiename = "";
                    String cookievalue = "";
                    Cookie cookie = null;
                    if (ss.length > 1) {
                        cookiename = ss[0];
                        cookievalue = ss[1];
                        cookie = new BasicClientCookie(cookiename, cookievalue);
                        cookies.add(cookie);
                    }
                }
            }
        }
        return cookies;
    }

    public static String getResponseCookiesToString(HttpResponse response) {
        StringBuffer cookies = new StringBuffer();
        Header[] hds = response.getAllHeaders();
        boolean start = true;
        if (hds != null && hds.length > 0) {
            for (int i = 0; i < hds.length; i++) {
                if (hds[i].getName().equalsIgnoreCase("Set-Cookie")) {
                    if (!start) {
                        cookies.append(";");
                    }
                    start = false;
                    cookies.append(hds[i].getValue());
                }
            }
        }
        return cookies.toString();
    }

    public static String URL_REGEX = "(http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?";

    public static String getMacherForRegex(String regex, String data) {
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(data);
            if (matcher.find()) {
                return matcher.group(0);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getHost(String url)
    {	try
    {
        Pattern p = Pattern.compile("(http://|https://)?([^/]*)",Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(url);
        return m.find()?m.group(2):url;
    }catch (Exception e)
    {
        return null;
    }
    }

    public static String getHostWithProtocol(String url)
    {	try
    {
        Pattern p = Pattern.compile("(http://|https://)?[^/]*",Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(url);
        return m.find()?m.group(0):url;
    }catch (Exception e)
    {
        return null;
    }
    }
}
