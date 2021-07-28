package com.pd.common.utils;
import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

//Access restriction: The type BASE64Decoder is not accessible due to restriction on required library ,java build path jre�ĳ�workspace 
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * DES���� �����㷨
 * @author zhangdi
 *
 */
public class DesUtil {

    private final static String DES = "DES";
    private final static String ENCODE = "GBK";

    

    
    /**
     * Description ���ݼ�ֵ���м���
     * @param data ����������
     * @param key ��Կ
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key) throws Exception {
        byte[] bt = encrypt(data.getBytes(ENCODE), key.getBytes(ENCODE));
        String strs = new BASE64Encoder().encode(bt);
        return strs;
    }

    /**
     * ���ݼ�ֵ���н���
     * @param data ����������
     * @param key    ��Կ
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String decrypt(String data, String key) throws IOException,
            Exception {
        if (data == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buf = decoder.decodeBuffer(data);
        byte[] bt = decrypt(buf, key.getBytes(ENCODE));
        return new String(bt, ENCODE);
    }

    /**
     * Description ���ݼ�ֵ���м���
     * 
     * @param data
     * @param key
     *            ���ܼ�byte����
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // ����һ�������ε������Դ
        SecureRandom sr = new SecureRandom();

        // ��ԭʼ��Կ���ݴ���DESKeySpec����
        DESKeySpec dks = new DESKeySpec(key);

        // ����һ����Կ������Ȼ��������DESKeySpecת����SecretKey����
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher����ʵ����ɼ��ܲ���
        Cipher cipher = Cipher.getInstance(DES);

        // ����Կ��ʼ��Cipher����
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }

    /**
     * Description ���ݼ�ֵ���н���
     * 
     * @param data
     * @param key ���ܼ�byte����
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // ����һ�������ε������Դ
        SecureRandom sr = new SecureRandom();

        // ��ԭʼ��Կ���ݴ���DESKeySpec����
        DESKeySpec dks = new DESKeySpec(key);

        // ����һ����Կ������Ȼ��������DESKeySpecת����SecretKey����
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher����ʵ����ɽ��ܲ���
        Cipher cipher = Cipher.getInstance(DES);

        // ����Կ��ʼ��Cipher����
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }
    
    public static void main(String[] args){
        String data = "12AUism810jsqASI08";
        //��Կ�ĳ��ȱ�����8�ı���
       // String key ="12345678abcdefgh";
         String key ="22345678abcdefgh";

        System.out.println("����ǰ===>"+data);
        try {
        	String e1=encrypt(data, key);
            System.out.println("ָ��key���ܺ�===>"+e1);
            String d1=decrypt(e1, key);
           System.out.println("ָ��key���ܺ�===>"+d1);
            
            
        //   ����ǰ===>12AUism810jsqASI08
        	//	   ָ��key���ܺ�===>JWtfwlWtpNjyPIVTNXHmG9a9gyR8o0lU
        	//	   ָ��key���ܺ�===>12AUism810jsqASI08
       //    ����ǰ===>12AUism810jsqASI08
        	//	   ָ��key���ܺ�===>/EyJSjHBoqbROfJ/arVGoC7EFP+qOIVS
        	//	   ָ��key���ܺ�===>12AUism810jsqASI08
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}