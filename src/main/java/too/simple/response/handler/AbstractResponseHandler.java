package too.simple.response.handler;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.cookie.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import too.simple.Page;
import too.simple.Request;
import too.simple.Task;
import too.simple.response.handler.impl.DefaultResponseValidator;
import too.simple.selector.PlainText;
import too.simple.utils.CharsetUtils;
import too.simple.utils.HttpUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public abstract class AbstractResponseHandler {
    private Logger logger = LoggerFactory.getLogger(AbstractResponseHandler.class);
    protected IResponseValidator responseValidator = new DefaultResponseValidator();

    public Page handleResponse(Request request, String charset, HttpResponse httpResponse, Task task) throws Exception {
        //logger.info(content);


        byte[] bytes = IOUtils.toByteArray(httpResponse.getEntity().getContent());
        String contentType = httpResponse.getEntity().getContentType() == null ? "" : httpResponse.getEntity().getContentType().getValue();
        Page page = new Page();
        page.setBytes(bytes);
        if (!request.isBinaryContent()) {
            if (charset == null) {
                charset = getHtmlCharset(contentType, bytes);
            }
            page.setCharset(charset);
            page.setRawText(new String(bytes, charset));
        }
        page.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        page.setCookies(HttpUtils.getResponseCookies(httpResponse));
        page.setUrl(new PlainText(request.getUrl()));
        page.setDownloadSuccess(true);

        request.setPage(page);
        request.setCookie(HttpUtils.getResponseCookiesToString(httpResponse));
        Header[] allHeaders = httpResponse.getAllHeaders();
        request.page.setHeaders(allHeaders);
        request.putExtra("Location", httpResponse.getFirstHeader("Location") == null ? null : httpResponse.getFirstHeader("Location").getValue());
        request.setSuccess(true);
        request.page = page;
        return page;
    }

    public IResponseValidator getResponseValidator() {
        return responseValidator;
    }

    public AbstractResponseHandler setResponseValidator(IResponseValidator responseValidator) {
        this.responseValidator = responseValidator;
        return this;
    }


    private String getHtmlCharset(String contentType, byte[] contentBytes) throws IOException {
        String charset = CharsetUtils.detectCharset(contentType, contentBytes);
        if (charset == null) {
            charset = Charset.defaultCharset().name();
            logger.warn("Charset autodetect failed, use {} as charset. Please specify charset in Site.setCharset()", Charset.defaultCharset());
        }
        return charset;
    }

    protected String formatCookies(List<Cookie> cookies) {
        StringBuilder sb = new StringBuilder();
        for (Cookie c : cookies) {
            if (!"".equals(sb.toString())) {
                sb.append(" ");
            }
            sb.append(c.getName());
            sb.append("=");
            sb.append(c.getValue());
            sb.append(";");
        }
        return sb.toString();
    }
}
