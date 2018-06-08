package top.danny.tools.encrypt;

import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huyuyang@lxfintech.com
 * @Title: RSAUtilTest
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-08-17 17:06:45
 */
public class RSAUtilTest {

    @Test
    public void getKeysTest() throws NoSuchAlgorithmException {
        Map map = RSAUtil.getKeys();
        Assert.assertNotNull(map.get("private"));
        Assert.assertNotNull(map.get("public"));
        String privateKey = map.get("private").toString();
        String publicKey = map.get("public").toString();
        System.out.println("私钥：" + privateKey);
        System.out.println("公钥：" + publicKey);
    }

    @Test
    public void test() throws Exception {
        HashMap<String, Object> map = RSAUtil.getKeys();
        //生成公钥和私钥
        RSAPublicKey publicKey = (RSAPublicKey) map.get("public");
        RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");
        System.out.println("公钥：" + String.valueOf(Base64.encode(publicKey.getEncoded())));
        System.out.println("私钥：" + String.valueOf(Base64.encode(privateKey.getEncoded())));
        System.out.println("private-modulus：" + privateKey.getModulus().toString());
        System.out.println("public-modulus：" + publicKey.getModulus().toString());

        //模
        String modulus = publicKey.getModulus().toString();
        //公钥指数
        String public_exponent = publicKey.getPublicExponent().toString();
        System.out.println("公钥指数："+public_exponent);

        //私钥指数
        String private_exponent = privateKey.getPrivateExponent().toString();
        System.out.println("私钥指数："+private_exponent);
        //明文
        String ming = "123456789";
        //使用模和指数生成公钥和私钥
        RSAPublicKey pubKey = RSAUtil.getPublicKey(modulus, public_exponent);
        RSAPrivateKey priKey = RSAUtil.getPrivateKey(modulus, private_exponent);
        //加密后的密文
        String mi = RSAUtil.encryptByPublicKey(ming, pubKey);
        System.err.println("密文：" + mi);
        //解密后的明文
        ming = RSAUtil.decryptByPrivateKey(mi, priKey);
        System.err.println("明文：" + ming);


        //根据私钥字符串生成公钥对象
        String siyao=String.valueOf(Base64.encode(privateKey.getEncoded()));
        RSAPrivateKey privateKey1=RSAUtil.getPrivateKey(Base64.decode(siyao));
        String ming1= RSAUtil.decryptByPrivateKey(mi, privateKey1);
        System.err.println("明文：" + ming);
    }

    @Test
    public void jiemi() throws Exception {
        String mi = "53a1f81d45e16dabd0ca7c737ab2634488c14281e6ffbf7224d5d240abf4d37610c540de8374fbea8504e244cf45c8ed232bc5c30c0a35cb2fef3d4ccf808a6571c2215c3c94078519dc10bc0873555985681621069619e89878c666e774a2fdcbca5a952f6a93123931fd0c8a1929e17f24ae968e3b00836f2d3ffcf0f37cf2ecd8191a0ae230ce108ae1a40df87ad431c22e0ef3a3fd59622d";
        String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIa0dEMpr6YhwPZ/vPDf3cd3Ljg88QLXD4V89gMo+IpmT1PH8uEFRKMtpAejIPBbBHfAf4O3TYIm521YqvHke/Z8xtfGSzCEypWTGTl/Upg5kpwVyfxocCNR6EHYggGUv+3Ic9UiIQ1TzSxVxlNXPy3kDPlHrzXyj37GoSDMV4PRAgMBAAECgYBAsRVM1A5JKNi1CS/CNqEaFECcPZg5lvMbVqIHbnu+a9gZEmOwcYk/HMIz8MH27J8+q7IRncyZfvmR6A1g0iw9vwAjHPmBmNYMiSHnNlBBhSlvhq5YkslUQJLTA1jSNTgEED4Jk+Xj1F11V6pH6wuiu6RD4njrjYTPALOCBm8KKQJBAP4xFDg6BuNB9p18Q8GnT1ibEOuHZ4tVZMRvvGyk+EphKSxZtNQUAzlNaipT1xLdzO5u+CM/rc/AEM5XSKv9dFMCQQCHqcWYeR92CZw3hmkXWwXQI4I/5ZISn/+XKEsy7cuarjUhFlIykDjSRxGoymGs5utqyHuEFNdBy/Xa/JnKSeLLAkAZc4yUvKqn4pjw19po73mwY4ZkLuCdQOWgDZgOQM5jlCrtN+Y1PfO8rxWsO0zTpMW6Mf5CeUPMA20rW+342p3lAkAY1x03LfZ0xo20HQhhHmmSEu5mhMKaZnGGW/po+OGvEPbpeAzU9VNyLVEuduArqJyykMMNDEPd6Fq8HkSKpbzBAkBlpHr/792us1QRsR7jk0+pGyuyLRyruxvldgojBDBVFWceTLi5x86aNrq2QZufVVPnr3NZUpWZChvgQczKv1hS";
        RSAPrivateKey priKey = RSAUtil.getPrivateKey(Base64.decode(privateKey));
        String ming = RSAUtil.decryptByPrivateKey(mi, priKey);
        System.out.println("明文："+ming);
    }

}
