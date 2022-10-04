package utils;

import org.apache.commons.codec.binary.Base64;

public class HandleAuthentication {
    public static String encodeCredStr(String email, String token){
        //Authentication
//        String email = "trongkien1199@gmail.com";
//        String token = "ctHOKZT21TFgpOppzAaY146D";
        if(email == null || token == null){
            throw new IllegalArgumentException("[ERR]: Email or token cannot be null");
        }
        String cred = email.concat(":").concat(token);
        byte[] encodeCred = Base64.encodeBase64(cred.getBytes());
        String encodeCredStr = new String(encodeCred);
        return encodeCredStr;
    }
}
