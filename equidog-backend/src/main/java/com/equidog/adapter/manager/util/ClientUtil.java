package com.equidog.adapter.manager.util;

import org.springframework.http.HttpHeaders;

import java.util.HashMap;
import java.util.Map;

public class ClientUtil {


    public static HttpHeaders addHeaders(Map<String,String> mapHeaders){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(mapHeaders);
        return httpHeaders;
    }

}
