package br.com.santander.product.application.core.exception;

public class BusinessException extends RuntimeException  {
    private static final long serialVersionUID = 1L;

    public BusinessException() {
        super();
    }
    public BusinessException(String msg)   {
        super(msg);
    }
    public BusinessException(String msg, Exception e)  {
        super(msg, e);
    }
}

