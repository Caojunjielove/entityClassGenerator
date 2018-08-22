package com.umf.generator;

/** 
 * @Description：属性对象
 * <p>创建日期：2018年8月21日 </p>
 * @version V1.0  
 * @author hp
 * @see
 */
public class Attribute {

	private String name;
	private String notes;
	private boolean isMust;
	private boolean isRegular;
	private String regularExpression;
	
	public String getName() {
		return name;
	}
	public boolean isMust() {
		return isMust;
	}
	public String getRegularExpression() {
		return regularExpression;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setMust(boolean isMust) {
		this.isMust = isMust;
	}
	public void setRegularExpression(String regularExpression) {
		this.regularExpression = regularExpression;
	}
	public boolean isRegular() {
		return isRegular;
	}
	public void setRegular(boolean isRegular) {
		this.isRegular = isRegular;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	@Override
	public String toString() {
		return "Attribute [name=" + name + ", notes=" + notes + ", isMust=" + isMust + ", isRegular=" + isRegular
				+ ", regularExpression=" + regularExpression + "]";
	}
	
}
