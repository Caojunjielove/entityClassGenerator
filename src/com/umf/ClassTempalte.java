package com.umf;

import java.util.List;

/** 
 * @Description�������
 * <p>�������ڣ�2018��8��21�� </p>
 * @version V1.0  
 * @author hp
 * @see
 */
public class ClassTempalte {

	private boolean isReq; //�Ƿ�������
	private String  packageinfo; //����
	private String parentClassAllName; //����ȫ��
	private String parentClass; //������
	private String className; 
	private List<Attribute>  attrList;
	public boolean isReq() {
		return isReq;
	}
	public String getPackageinfo() {
		return packageinfo;
	}
	public String getParentClassAllName() {
		return parentClassAllName;
	}
	public String getParentClass() {
		return parentClass;
	}
	public String getClassName() {
		return className;
	}
	public List<Attribute> getAttrList() {
		return attrList;
	}
	public void setReq(boolean isReq) {
		this.isReq = isReq;
	}
	public void setPackageinfo(String packageinfo) {
		this.packageinfo = packageinfo;
	}
	public void setParentClassAllName(String parentClassAllName) {
		this.parentClassAllName = parentClassAllName;
	}
	public void setParentClass(String parentClass) {
		this.parentClass = parentClass;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public void setAttrList(List<Attribute> attrList) {
		this.attrList = attrList;
	}
	@Override
	public String toString() {
		return "ClassTempalte [isReq=" + isReq + ", packageinfo=" + packageinfo + ", parentClassAllName="
				+ parentClassAllName + ", parentClass=" + parentClass + ", className=" + className + ", attrList="
				+ attrList + "]";
	}
	
}
