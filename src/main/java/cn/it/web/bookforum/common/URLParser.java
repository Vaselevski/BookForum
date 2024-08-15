package cn.it.web.bookforum.common;

import jakarta.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLParser {

    /**
     * 从当前 URL 中提取指定字符串后的内容
     * @param request HttpServletRequest 对象
     * @param targetString 需要解析的目标字符串（例如 "/ddd/xxx"）
     * @return 指定字符串后的内容，如果没有找到则返回 null
     */
    public static String extractContentAfterString(HttpServletRequest request, String targetString) {
        String path = request.getRequestURI();

        String regex = Pattern.quote(targetString) + "/(.*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(path);

        // 查找匹配项并提取内容
        if (matcher.find()) {
            return matcher.group(1); // 获取目标字符串后的内容
        } else {
            return null; // 如果没有匹配，返回 null
        }
    }
}
