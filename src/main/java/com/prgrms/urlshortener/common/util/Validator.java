package com.prgrms.urlshortener.common.util;

import com.prgrms.urlshortener.common.error.exception.WrongFieldException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isBlank;


public class Validator {
    public static void validateUrl(String url) {
        if(isBlank(url)) {
            throw new WrongFieldException("url", url, "blank is not allowed at url");
        }

        if(isNonValidUrlPattern(url)) {
            throw new WrongFieldException("url", url, "not a valid url");
        }
    }

    public static void validateRequestCount(long requestCount) {
        if(requestCount < 0) {
            throw new WrongFieldException("requestCount", requestCount, "requestCount >= 0");
        }
    }

    public static void validateEncodedId(String encodedId) {
        if(isBlank(encodedId)) {
            throw new WrongFieldException("encodedId", encodedId, "blank is not allowed at encodedId");
        }

        if(isNonBase62EncodedId(encodedId)) {
            throw new WrongFieldException("encodedId", encodedId, "not a valid encodedId");
        }
    }

    private static boolean isNonValidUrlPattern(String url) {
        Pattern pattern = Pattern.compile("[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)");
        Matcher matcher = pattern.matcher(url);

        if(matcher.matches()) {
            return false;
        }else{
            return true;
        }
    }

    private static boolean isNonBase62EncodedId(String encodedId) {
        Pattern pattern = Pattern.compile("[0-9A-Za-z]");
        Matcher matcher = pattern.matcher(encodedId);

        if(matcher.matches()) {
            return false;
        }else{
            return true;
        }
    }
}
