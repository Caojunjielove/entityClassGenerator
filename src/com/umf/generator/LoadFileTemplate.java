package com.umf.generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

public class LoadFileTemplate {

	
	/**
	 * @Description ����excel�ļ�������Ϊ��ģ��
	 * <p>�����ˣ�hp ,  2018��8��21��  ����4:01:17</p>
	 * <p>�޸��ˣ�hp ,  2018��8��21��  ����4:01:17</p>
	 *
	 * @param filePath
	 * @return
	 * @throws IOException
	 * List<ClassTempalte> 
	 */
	public List<ClassTempalte> parseFile(String filePath) throws IOException{
		ExcelParseTool excelParseTool = new ExcelParseTool(filePath);
        Workbook workbook = excelParseTool.initWorkBook();
        List<ClassTempalte> list = new ArrayList<ClassTempalte>();
        excelParseTool.parseWorkbook(workbook,list);
        return list;
	}	
}
