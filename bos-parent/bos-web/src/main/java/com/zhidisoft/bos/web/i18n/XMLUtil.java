package com.zhidisoft.bos.web.i18n;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.hp.hpl.sparta.xpath.ThisNodeTest;

public class XMLUtil {
	
	/**
	 * 将Xml模板的内容封装到指定的i18n对象中
	 * @param file xml文件的名称
	 * @return
	 */
	static public I18n fromXMLFile(String file){
		SAXReader saxReader=new SAXReader();
		Document doc;
		I18n i18n = null;
		try {
			doc = saxReader.read(file);
			Element root= doc.getRootElement();
			String text = root.getText();
			i18n=new I18n();
			i18n.setContext(text);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return i18n;
	}
	
	public static void main(String[] args) {
		String path = I18n.class.getClassLoader().getResource("i18n.xml").getPath();
		fromXMLFile(path);
	}
}
