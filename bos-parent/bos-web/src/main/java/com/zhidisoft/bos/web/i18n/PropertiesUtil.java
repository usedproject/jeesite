package com.zhidisoft.bos.web.i18n;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	public static String getResource(String file){
		String path = PropertiesUtil.class.getClassLoader().getResource(file).getPath();
		Properties prop = new Properties();
		StringBuffer sBuffer=null;
		try {
			//装载配置文件
			prop.load(new FileInputStream(new File(path)));
			if (prop.size()>0) {
				sBuffer=new StringBuffer();
				for(Object key:prop.keySet()){
					sBuffer.append(key.toString());
					sBuffer.append(":");
					sBuffer.append('"');
					sBuffer.append(prop.get(key).toString());
					sBuffer.append('"');
					sBuffer.append(",");
				}
				sBuffer.deleteCharAt(sBuffer.length() - 1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//返回获取的值
		return sBuffer.toString();
	}
}
