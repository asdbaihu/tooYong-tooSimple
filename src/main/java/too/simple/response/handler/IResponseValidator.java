package too.simple.response.handler;


import too.simple.Request;

public interface IResponseValidator {
    boolean validate(Request request)throws Exception;
}
