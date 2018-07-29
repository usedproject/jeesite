package com.zhidisoft.bos.web.listener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.zhidisoft.bos.web.i18n.ConstantUtil;
import com.zhidisoft.bos.web.i18n.I18n;
import com.zhidisoft.bos.web.i18n.PropertiesUtil;
import com.zhidisoft.bos.web.i18n.XMLUtil;

public class ContextInitListener implements ServletContextListener {

    public ContextInitListener() {
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO 容器启动完成之后，进行获取模板文件，并将模板对象的值赋给i18n对象
    	 // TODO 将中英文properties中的文件中的内容进行获取成字符串，然后将这个字符串进行替换掉i18n对象中的相关部分
    	 // TODO 将i18n中的指定部分的内容输出到i18n.js文件中
    	 // TODO 在web.xml中进行配置这个监听器
    	FileOutputStream fStream=null;
//    	FileOutputStream fStream2=null;
    	 //获取模板文件
    	String path = this.getClass().getClassLoader().getResource("i18n.xml").getPath();
    	I18n i18n = XMLUtil.fromXMLFile(path);
    	//获取中英文对应的properties文件中的值
    	String znString=PropertiesUtil.getResource("CN.properties");
    	String enString=PropertiesUtil.getResource("EN.properties");
    	//替换掉i18n中的属性中的指定部位
    	String context = i18n.getContext();
    	context = context.replaceAll(ConstantUtil.ZNString, znString);
    	context = context.replaceAll(ConstantUtil.ENString, enString);
    	//将字符串变成i18n.js
    	String realPath = arg0.getServletContext().getRealPath("/");
    	realPath = realPath.concat("js"+File.separator+"i18n.js");
    	try {
			fStream=new FileOutputStream(realPath);
			fStream.write(context.getBytes());
//			realPath="D:\\EnglishEclipse\\bos-parent\\bos-web\\src\\main\\webapp\\js\\i18n.js";
//			fStream2=new FileOutputStream(realPath);
//			fStream2.write(context.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}finally{
			if (fStream !=null ) {
				try {
					fStream.flush();
					fStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    }
    
}
