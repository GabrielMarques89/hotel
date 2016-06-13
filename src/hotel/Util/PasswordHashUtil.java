package hotel.Util;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordHashUtil {

    public String encrypt(String value){
        return DigestUtils.sha1Hex(value);
    }
}
