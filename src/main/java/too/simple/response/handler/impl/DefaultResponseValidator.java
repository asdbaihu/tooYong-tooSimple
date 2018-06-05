package too.simple.response.handler.impl;


import too.simple.Request;
import too.simple.response.handler.IResponseValidator;

public class DefaultResponseValidator implements IResponseValidator {
    @Override
    public boolean validate(Request request)throws Exception
    {
        return true;
    }
}
