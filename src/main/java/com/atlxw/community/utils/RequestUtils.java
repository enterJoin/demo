package com.atlxw.community.utils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

    /**
     * 获取客户端真实的IP
     * @param request
     * @return
     */
    public static String getIPAddress(HttpServletRequest request){
        String ip = null;

        //X-Forwarded-For: Squid服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");
        if(ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)){
            //Proxy-Client-IP: apache服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }
        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号来分隔开，并且第一个为客户端真实ip
        if(ipAddresses != null && ipAddresses.length() != 0){
            ip = ipAddresses.split(",")[0];
        }
        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)){
            ip = request.getRemoteAddr();
        }

        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

    /**
     * 获取项目根目录的URL
     * @param request
     * @return
     */
    public static String getProjectRootUrl(HttpServletRequest request){
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
    }
}
