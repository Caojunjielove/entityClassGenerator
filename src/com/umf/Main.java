package com.umf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.umf.generator.Attribute;
import com.umf.generator.ClassTempalte;
import com.umf.generator.ControllerGenerator;
import com.umf.generator.CreateBean;
import com.umf.generator.CreateClinetBean;
import com.umf.generator.CreateServerBean;
import com.umf.generator.LoadFileTemplate;
import com.umf.generator.StoreProptiesFile;
import com.umf.utils.PropertiesLoaderUtils;

public class Main {

	public static void main(String[] args) throws IOException {
		LoadFileTemplate loadFile = new LoadFileTemplate();
		List<ClassTempalte> list = loadFile.parseFile(PropertiesLoaderUtils.get("inputFIle"));
		List<Attribute> attrList = new ArrayList<Attribute>();
		
		boolean isClient  = Boolean.valueOf(PropertiesLoaderUtils.get("isClient"));
		
		CreateBean createBean;
		
		if(isClient){
			createBean = new CreateClinetBean();
		}else{
			createBean = new CreateServerBean();
		}
		ControllerGenerator controllerGenerator = new ControllerGenerator();
		
		for (ClassTempalte classTempalte : list) {
			createBean.createBeanMethod(classTempalte);
			if(classTempalte.isReq()){
				attrList.addAll(classTempalte.getAttrList());
				if(!isClient){
					controllerGenerator.createBeanMethod(classTempalte);
				}
			}
		}
		if(!isClient){
			StoreProptiesFile.createMsgFile(attrList);
		}
		
	}
}

