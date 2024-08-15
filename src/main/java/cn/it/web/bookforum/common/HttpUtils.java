package cn.it.web.bookforum.common;

import jakarta.servlet.http.HttpServletResponse;

public class HttpUtils {


    public static void disableCaching(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies
    }
}
