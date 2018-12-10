package com.zxiang.common.utils.excel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ExcelServiceUtil{

	public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
    public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";
    
	public static List<Object> importData(MultipartFile file) throws Exception {
		 if (file.isEmpty()) {
	        throw new Exception("上传文件不能为空");
	     }
		String fileType = getSuffix(file.getOriginalFilename());
		InputStream is =file.getInputStream();
		if(OFFICE_EXCEL_2003_POSTFIX.equals(fileType)) {
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			return readHSSFWorkbook(hssfWorkbook);
		}else if(OFFICE_EXCEL_2010_POSTFIX.equals(fileType)) {
			XSSFWorkbook workbook  = new XSSFWorkbook(is);
			return readXSSFWorkbook(workbook);
		}else {
			throw new Exception("上传文件类型"+ fileType +"有误");
		}
	}

	private static List<Object> readHSSFWorkbook(HSSFWorkbook hssfWorkbook) throws Exception {
		List<Object> sheetList = new ArrayList<Object>();
		List<Object> dataList = null;
//		int sheets = hssfWorkbook.getNumberOfSheets(); // 获取表格一共几页
		HSSFSheet sheet = hssfWorkbook.getSheetAt(0); // 获取表格第一页的数据
		int rows = sheet.getPhysicalNumberOfRows(); // 获取当前页表格一共有几行
		if(rows == 0) {
			return sheetList;
		}
		HSSFRow xssfRow = null;
		for (int i = 1; i < rows; i++) {
			 xssfRow = sheet.getRow(i);
			 if(xssfRow == null) continue;
			 dataList = new ArrayList<Object>();
			 for(int j=0;j< xssfRow.getLastCellNum();j++){
              	dataList.add(getHSSFCellValue(xssfRow.getCell(j)));
              }
			 sheetList.add(dataList);
		}	
		return sheetList;
	}
	private static List<Object> readXSSFWorkbook(XSSFWorkbook workbook) throws Exception {
		List<Object> sheetList = new ArrayList<Object>();
		List<Object> dataList = null;
//		int sheets = workbook.getNumberOfSheets(); // 获取表格一共几页
		XSSFSheet sheet = workbook.getSheetAt(0); // 获取表格第一页的数据
		int rows = sheet.getPhysicalNumberOfRows(); // 获取当前页表格一共有几行
		if(rows == 0) {
			return sheetList;
		}
		XSSFRow xssfRow =null;
		for (int i = 1; i < rows; i++) {
			 xssfRow = sheet.getRow(i);
			 if(xssfRow == null) continue;
			 dataList = new ArrayList<Object>();
			 for(int j=0;j< xssfRow.getLastCellNum();j++){
              	dataList.add(getXSSFCellValue(xssfRow.getCell(j)));
              }
			 sheetList.add(dataList);
		}	
		return sheetList;
	}


	public static String getSuffix(String fileName) {
		if(StringUtils.isBlank(fileName)) {
			return "";
		}
		if(fileName.contains(".")) {
			return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		}
		return "";
	}
	@SuppressWarnings("static-access")
	private static Object getHSSFCellValue(HSSFCell hssfCell) throws Exception {
		if(hssfCell!=null){
    		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
    			return String.valueOf(hssfCell.getBooleanCellValue()).trim();
    		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
    			Object inputValue = null;
    			Double doubleVal = hssfCell.getNumericCellValue();
    			long longVal = Math.round(doubleVal);  
    		    if (Double.parseDouble(longVal + ".0") == doubleVal)  
    		        inputValue = longVal;  
    		    else  
    		        inputValue = doubleVal;
    			return String.valueOf(inputValue).trim();
    		}else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_STRING) {
    			return hssfCell.getStringCellValue().trim();
    		}else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_BLANK){
    			return "";
    		}else {
    			throw new Exception("表格数据未知类型");
    		}
    	}
		return "";
	}
	@SuppressWarnings("static-access")
	private static String getXSSFCellValue(XSSFCell xssfRow) throws Exception {
		if (xssfRow != null) {
			return "";
		}
		if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfRow.getBooleanCellValue()).trim();
		} else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
			Object inputValue = null;
			Double doubleVal = xssfRow.getNumericCellValue();
			long longVal = Math.round(doubleVal);
			if (Double.parseDouble(longVal + ".0") == doubleVal)
				inputValue = longVal;
			else
				inputValue = doubleVal;
			return String.valueOf(inputValue).trim();
		} else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_STRING) {
			return xssfRow.getStringCellValue().trim();
		} else if(xssfRow.getCellType() == xssfRow.CELL_TYPE_BLANK){
			return "";
		} else {
			throw new Exception("表格数据未知类型");
		}
	}
}
