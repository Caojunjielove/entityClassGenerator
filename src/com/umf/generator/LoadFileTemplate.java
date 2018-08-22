package com.umf.generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

public class LoadFileTemplate {

	
	/**
	 * @Description 加载excel文件并解析为类模板
	 * <p>创建人：hp ,  2018年8月21日  下午4:01:17</p>
	 * <p>修改人：hp ,  2018年8月21日  下午4:01:17</p>
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
