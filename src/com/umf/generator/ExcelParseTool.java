package com.umf.generator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.umf.utils.PropertiesLoaderUtils;

/** 
 * @Description：解析excel工具类
 * <p>创建日期：2018年8月21日 </p>
 * @version V1.0  
 * @author hp
 * @see
 */
public class ExcelParseTool {
	
    private String mFilePath;
    void setFilePath(String filePath) {
        mFilePath = filePath;
    }

    public ExcelParseTool(String filePath){
    	mFilePath = filePath;
    }
    
    private static final String SUFFIX_2003 = ".xls";
    private static final String SUFFIX_2007 = ".xlsx";

    public Workbook initWorkBook() throws IOException {
        File file = new File(mFilePath);
        InputStream is = new FileInputStream(file);

        Workbook workbook = null;
        if (mFilePath.endsWith(SUFFIX_2003)) {
            workbook = new HSSFWorkbook(is);
        } else if (mFilePath.endsWith(SUFFIX_2007)) {
            workbook = new XSSFWorkbook(is);
        }

        return workbook;
    }

    public void parseWorkbook(Workbook workbook, List<ClassTempalte> list) {
        int numOfSheet = workbook.getNumberOfSheets();
        for (int i = 0; i < numOfSheet; ++i) {
            Sheet sheet = workbook.getSheetAt(i);
            list.add(parseSheet(sheet));
        }
    }

    private ClassTempalte parseSheet(Sheet sheet) {
    	
        Row row;
        ClassTempalte classTempalte = new ClassTempalte();
        classTempalte.setClassName(sheet.getSheetName());
        classTempalte.setPackageinfo(PropertiesLoaderUtils.get("model.package"));
        
        Iterator<Row> iterator = sheet.iterator();
        List<Attribute> attrList = new ArrayList<Attribute>();
        Attribute attr = null;
        while(iterator.hasNext()) {
            row = iterator.next();
            List<String> cells = parseRow(row);
            System.out.println("单元格信息:" + cells.get(0));
            if("[REQ]".equals(cells.get(0).trim())){
            	classTempalte.setReq(true);
            	String parent = PropertiesLoaderUtils.get("reqparentClass");
            	classTempalte.setParentClassAllName(parent);
            	classTempalte.setParentClass(parent.substring(parent.lastIndexOf(".") + 1));
            	continue;
            }else if("[RES]".equals(cells.get(0).trim())){
            	classTempalte.setReq(false);
            	String parent = PropertiesLoaderUtils.get("resparentClass");
            	classTempalte.setParentClassAllName(parent);
            	classTempalte.setParentClass(parent.substring(parent.lastIndexOf(".") + 1));
            	continue;
            }else{
            	attr = new Attribute();
            	attr.setName(cells.get(0));
            	attr.setNotes(cells.get(1));
            	if("M".equals(cells.get(2).trim())){
            		attr.setMust(true);
            	}else{
            		attr.setMust(false);
            	}
            	if(cells.size() > 3 && cells.get(3)!= null && !"".equals(cells.get(3).trim())){
        			attr.setRegular(true);
        			attr.setRegularExpression(cells.get(3));
            	}else{
            		attr.setRegular(false);
            	}
            	attrList.add(attr);
            }
            classTempalte.setAttrList(attrList);
        }
        return classTempalte;
    }



    private List<String> parseRow(Row row) {
        List<String> rst = new ArrayList<>();
        Cell cell;
        Iterator<Cell> iterator = row.iterator();
        while (iterator.hasNext()) {
            cell = iterator.next();
            cell.setCellType(CellType.STRING);
            rst.add(cell.getStringCellValue());
        }
        return rst;
    }
}
