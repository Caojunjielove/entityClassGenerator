package com.umf;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.umf.utils.PropertiesLoaderUtils;

/** 
 * @Description�����������Ĺ���
 * <p>�������ڣ�2018��8��21�� </p>
 * @version V1.0  
 * @author hp
 * @see
 */
public class CreateBean {
	
	private static final String REQ_COMMOM_CODE = "funCode|reqDate|reqTime|calling|merId|clientVer";
	private static final String RES_COMMOM_CODE = "funCode|reqDate|reqTime|retCode|retMsg";
	
	/**
	 * @Description���������ļ�
	 * <p>�����ˣ�hp ,  2018��8��21��  ����5:59:34</p>
	 * <p>�޸��ˣ�hp ,  2018��8��21��  ����5:59:34</p>
	 *
	 * @param classTempalte
	 * void 
	 */
	public void createBeanMethod(ClassTempalte classTempalte){
		String content = parse(classTempalte);
		try {
			String path = PropertiesLoaderUtils.get("outPath");
			FileWriter fw = new FileWriter(path + "\\" +classTempalte.getClassName() + ".java");
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
		sb.append("package ").append(classTempalte.getPackageinfo()).append(";\r\n");
		if(classTempalte.isReq()){
			sb.append("import ").append("javax.validation.constraints.Pattern").append(";\r\n");
			sb.append("import ").append("org.hibernate.validator.constraints.NotEmpty").append(";\r\n");
			sb.append("import ").append(PropertiesLoaderUtils.get("resparentClass")).append(";\r\n");
		} else {
			sb.append("import ").append(PropertiesLoaderUtils.get("reqparentClass")).append(";\r\n");
		}
		sb.append("import ").append(classTempalte.getParentClassAllName()).append(";\r\n\r\n\r\n");
		sb.append("public class " + classTempalte.getClassName() + " extends " + classTempalte.getParentClass() + " {\r\n\r\n");
		
		processAllAttrs(sb,classTempalte);
		if(!classTempalte.isReq()){
			processConstruct(sb,classTempalte);
		}
		processAllMethod(sb,classTempalte);
		if(classTempalte.isReq()){
			processCreateResponseData(sb,classTempalte);
		}
		processToString(sb,classTempalte);
		sb.append("}\r\n");
		System.out.println(sb.toString());
		return sb.toString();

	}
	
	/**
	 * �����������
	 * 
	 * @return
	 */
	private void processAllAttrs(StringBuffer sb,ClassTempalte classTempalte) {
		
		for (Attribute attribute : classTempalte.getAttrList()) {
			
			if(classTempalte.isReq()){
				if(REQ_COMMOM_CODE.contains(attribute.getName())){
					continue;
				}
			}else{
				if(RES_COMMOM_CODE.contains(attribute.getName())){
					continue;
				}
			}
			sb.append("\t/**\r\n");
			sb.append("\t * " + attribute.getNotes() +  "\r\n");
			sb.append("\t */\r\n");
			
			if(classTempalte.isReq() && attribute.isMust()){
				sb.append("\t@NotEmpty(message=\"{" + attribute.getName() + ".empty}\")\r\n");
			}
			if(classTempalte.isReq() && attribute.isRegular()){
				sb.append("\t@Pattern(regexp=\"{" + attribute.getRegularExpression() + "}\",message=\"{" + attribute.getName() + ".pattern.error}\")\r\n");
			}
			sb.append("\tprivate String " + attribute.getName() + ";\r\n\r\n");
		}
	}

	/**
	 * �������еķ���
	 * 
	 * @param sb
	 */
	private void processAllMethod(StringBuffer sb,ClassTempalte classTempalte) {
		
		for (Attribute attribute : classTempalte.getAttrList()) {
			if(classTempalte.isReq()){
				if(REQ_COMMOM_CODE.contains(attribute.getName())){
					continue;
				}
			}else{
				if(RES_COMMOM_CODE.contains(attribute.getName())){
					continue;
				}
			}
			sb.append("\t/**\r\n");
			sb.append("\t * ����" + attribute.getNotes() +  "\r\n");
			sb.append("\t */\r\n");
			sb.append("\tpublic void set" + initcap(attribute.getName()) + "(String " + attribute.getName()
					+ ") {\r\n");
			sb.append("\t\tthis." + attribute.getName() + " = " + attribute.getName() + ";\r\n");
			sb.append("\t}\r\n\r\n");

			sb.append("\t/**\r\n");
			sb.append("\t * ��ȡ" + attribute.getNotes() +  "\r\n");
			sb.append("\t */\r\n");
			sb.append("\tpublic String get"
					+ initcap(attribute.getName()) + " (){\r\n");
			sb.append("\t\treturn " + attribute.getName() + ";\r\n");
			sb.append("\t}\r\n\r\n");
		}
	}
	
	/**
	 * ���ɹ��췽��
	 * 
	 * @param sb
	 */
	private void processConstruct(StringBuffer sb,ClassTempalte classTempalte) {
		sb.append("\t/**\r\n");
		sb.append("\t * �вεĹ��췽��\r\n");
		sb.append("\t */\r\n");
		sb.append("\tpublic ").append(classTempalte.getClassName()).append("(RequestData requestData) {\r\n");
		sb.append("\t\tsuper(requestData);\r\n");
		sb.append("\t}\r\n\r\n");
	}
	
	/**
	 * ���ɴ�����Ӧ��ķ���
	 * 
	 * @param sb
	 */
	private void processCreateResponseData(StringBuffer sb,ClassTempalte classTempalte) {
		sb.append("\t/**\r\n");
		sb.append("\t * �����������Ӧ����Ӧ��\r\n");
		sb.append("\t */\r\n");
		sb.append("\tpublic ResponseData createResponseData() {\r\n");
		sb.append("\t\t return new " + classTempalte.getClassName().substring(0,classTempalte.getClassName().length() - 11) + "ResponseData"   + "();\r\n");
		sb.append("\t}\r\n\r\n");
	}
	
	/**
	 * ����toString����
	 * 
	 * @param sb
	 */
	private void processToString(StringBuffer sb,ClassTempalte classTempalte) {
		
		sb.append("\t/**\r\n");
		sb.append("\t * toString����\r\n");
		sb.append("\t */\r\n");
		sb.append("\tpublic String toString() {\r\n");
		sb.append("\t\t return \"" + classTempalte.getClassName() + "[");
		for (Attribute attr : classTempalte.getAttrList()) {
			sb.append(attr.getName()).append("=\" + ");
			if(classTempalte.isReq()){
				if(REQ_COMMOM_CODE.contains(attr.getName())){
					sb.append("get").append(initcap(attr.getName())).append("()");
				}else{
					sb.append(attr.getName());
				}
			}else{
				if(RES_COMMOM_CODE.contains(attr.getName())){
					sb.append("get").append(initcap(attr.getName())).append("()");
				}else{
					sb.append(attr.getName());
				}
			}
			sb.append(" + \",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]\";\r\n");
		sb.append("\t}\r\n");
	}

	private String initcap(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }
	
	
}
