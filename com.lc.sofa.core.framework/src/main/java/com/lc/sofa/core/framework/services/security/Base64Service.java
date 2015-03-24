package com.lc.sofa.core.framework.services.security;

import com.lc.sofa.core.framework.services.Service;

/**
 * 
 * BASE64编解码服务接口
 * @author YZ
 *
 */
public interface Base64Service extends Service{

    public static final String BASE64_FLAG="_LC_";
    
    /**
      * 进行Base64编码,返回编码后的串
      * @param info
      * @return
      */
    public String encryptToBase64(String info);	
	
    /**
     * Base64解码,返回编码前的串 
     * @param info
     * @return
     */
    public String decryptBase64(String info);
    
    /**
     * 判断该编码是否是经过Base64编码 
     * @param info
     * @return
     */
    public boolean isBase64(String info);
    
}
