package cn.it.web.bookforum.common;

import org.mindrot.jbcrypt.BCrypt;
//该类用于密码加密与解密
public class Hash {
    public static String hashString(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    public static boolean verifyString(String password, String hashedpassword) {
        return BCrypt.checkpw(password, hashedpassword);
    }
}
