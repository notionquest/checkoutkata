package com.notionquest.checkoutkata.exception;

/**
 * CheckoutKata run time exception wrapper class
 */
public class CheckoutKataApplicationException extends RuntimeException {

    private static final long serialVersionUID = -8249618975582252749L;

    public CheckoutKataApplicationException(String msg, Throwable t) {
        super(msg, t);
    }

    public CheckoutKataApplicationException(String msg) {
        super(msg);
    }
}
