package com.umf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.umf.utils.PropertiesLoaderUtils;

public class StoreProptiesFile {

//	private static final String REQ_COMMOM_CODE = "funCode|reqDate|reqTime|calling|merId|clientVer";
	
	public static void createMsgFile(List<Attribute>  attrList) throws FileNotFoundException, IOException{
		Properties pro = new Properties();
		for (Attribute attr : attrList) {
//			if(REQ_COMMOM_CODE.contains(attr.getName())){
//				continue;
//			}
			if(attr.isMust()){
				pro.put(attr.getName() + ".empty", attr.getName() + "����Ϊ��");
			}
			if(attr.isRegular()){
				pro.put(attr.getName() + ".pattern.error", attr.getName() + "��ʽ����ȷ");
			}
		}
		pro.store(new FileOutputStream(PropertiesLoaderUtils.get("outPath") + "\\" + "ValidationMessages.properties"),"У����Ϣ");
	}
}
