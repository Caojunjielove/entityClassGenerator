package com.umf.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.umf.utils.PropertiesLoaderUtils;

/** 
 * @Description：生成controller的工具
 * <p>创建日期：2018年8月21日 </p>
 * @version V1.0  
 * @author hp
 * @see
 */
public class ControllerGenerator implements CreateBean {
	
	
	/**
	 * @Description：生成类文件
	 * <p>创建人：hp ,  2018年8月21日  下午5:59:34</p>
	 * <p>修改人：hp ,  2018年8月21日  下午5:59:34</p>
	 *
	 * @param classTempalte
	 * void 
	 */
	public void createBeanMethod(ClassTempalte classTempalte){
		String content = parse(classTempalte);
		try {
			String path = PropertiesLoaderUtils.get("outPath");
			String controllerName = classTempalte.getClassName().substring(0,classTempalte.getClassName().length() -11);
			controllerName = controllerName + "Controller";
			String packagePath = PropertiesLoaderUtils.get("controller.package");
			packagePath = packagePath.replace(".", "\\");
			path = path + "\\" + packagePath;
			File file = new File(path);
			
			if(!file.exists()){
				file.mkdirs();
			}
			FileWriter fw = new FileWriter(path + "\\" +controllerName + ".java");
			PrintWriter pw = new PrintWriter(fw);
			pw.println(content);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String parse(ClassTempalte classTempalte) {
		StringBuffer sb = new StringBuffer();
		String controllerPackage = PropertiesLoaderUtils.get("controller.package");
		String parentAllClassName = PropertiesLoaderUtils.get("controller.parent.class");
		String parentName = null;
		if(parentAllClassName != null && !"".equals(parentAllClassName.trim())){
			parentName = parentAllClassName.substring(parentAllClassName.lastIndexOf(".") + 1);
			if(!parentAllClassName.equals(controllerPackage + "." + parentName )){
				sb.append("import ").append(parentAllClassName).append(";\r\n");
			}
		}
		sb.append("package ").append(controllerPackage).append(";\r\n");
		sb.append("import javax.validation.Valid;\r\n");
		sb.append("import org.springframework.web.bind.annotation.RequestMapping;\r\n");
		sb.append("import com.umf.frame.annotation.AppController;\r\n");
		sb.append("import com.umf.frame.annotation.AppRequestBody;\r\n");
		sb.append("import ").append(PropertiesLoaderUtils.get("resparentClass")).append(";\r\n");
		sb.append("import ").append(PropertiesLoaderUtils.get("model.package") + "." + classTempalte.getClassName()).append(";\r\n");
		String controllerName = classTempalte.getClassName().substring(0,classTempalte.getClassName().length() -11);
		controllerName = controllerName + "Controller";
		sb.append("\r\n\r\n");
		sb.append("@AppController\r\n");
		sb.append("public class " + controllerName);
		if(parentName != null){
			sb.append(" extends " + parentName);
		}
		sb.append(" {\r\n\r\n");
		processAllMethod(sb,classTempalte);
		sb.append("}\r\n");
		System.out.println(sb.toString());
		return sb.toString();

	}
	

	/**
	 * 生成所有的方法
	 * 
	 * @param sb
	 */
	private void processAllMethod(StringBuffer sb,ClassTempalte classTempalte) {
		String responseName = PropertiesLoaderUtils.get("resparentClass");
		responseName = responseName.substring(responseName.lastIndexOf(".") + 1);
		
		String resName = classTempalte.getClassName().substring(0,classTempalte.getClassName().length() - 11);
		resName = resName + "ResponseData";
		sb.append("\t/**\r\n");
		sb.append("\t * 业务处理逻辑\r\n");
		sb.append("\t */\r\n");
		sb.append("\t@RequestMapping(value=\"/####\")\r\n");
		sb.append("\tpublic " + responseName + " busiProcess(@Valid @AppRequestBody " + classTempalte.getClassName() + " reqData) {\r\n");
		sb.append("\t\t" + resName + " resData = (" + resName +  ")reqData.createResponseData();\r\n");
		sb.append("\t\tresData.success();\r\n");
		sb.append("\t\treturn resData;\r\n");
		sb.append("\t}\r\n\r\n");
	}
}
