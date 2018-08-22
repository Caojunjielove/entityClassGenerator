package com.umf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.umf.utils.PropertiesLoaderUtils;

public class Main {

	public static void main(String[] args) throws IOException {
		LoadFileTemplate loadFile = new LoadFileTemplate();
		List<ClassTempalte> list = loadFile.parseFile(PropertiesLoaderUtils.get("inputFIle"));
		List<Attribute> attrList = new ArrayList<Attribute>();
		
		CreateBean createBean = new CreateBean();
		for (ClassTempalte classTempalte : list) {
			createBean.createBeanMethod(classTempalte);
			if(classTempalte.isReq()){
				attrList.addAll(classTempalte.getAttrList());
			}
		}
		StoreProptiesFile.createMsgFile(attrList);
	}
}
