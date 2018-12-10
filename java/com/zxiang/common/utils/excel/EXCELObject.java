package com.zxiang.common.utils.excel;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zxiang.common.utils.excel.EXCELTools.Elements;
import com.zxiang.common.utils.excel.EXCELTools.Rule;



/**
 * 
 * @author thinkpad
 * 
 */
public class EXCELObject {

	private String fname;
	private String title;
	private String[] pTit;
	private String[] cols;
	private String[] titH;
	private String[] titN;
	private String[] width;
	private String[] widthP;

	// 元素
	private Elements[] e;

	private Integer startRow;
	private Integer endColumn;
	private String iFilePath;//读取文件的路径
	private String eFilePath;//导出文件的路径
	private boolean isCheck;
	private List<Map<String, String>> dataList;
	private Integer pageSize;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String[] getWidthP() {
		return widthP;
	}

	public void setWidthP(String[] widthP) {
		this.widthP = widthP;
	}

	public String[] getWidth() {
		return width;
	}

	public void setWidth(String[] width) {
		this.width = width;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<Map<String, String>> getDataList() {
		return dataList;
	}

	public void setDataList(List<Map<String, String>> dataList) {
		this.dataList = dataList;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		startRow = startRow - 1;
		this.startRow = startRow;
	}

	public Integer getEndColumn() {
		return endColumn;
	}

	public void setEndColumn(Integer endColumn) {
		this.endColumn = endColumn;
	}

	public String getiFilePath() {
		return iFilePath;
	}

	public void setiFilePath(String iFilePath) {
		this.iFilePath = iFilePath;
	}

	public String geteFilePath() {
		return eFilePath;
	}

	public void seteFilePath(String eFilePath) {
		this.eFilePath = eFilePath;
	}

	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String[] getpTit() {
		return pTit;
	}

	public void setpTit(String[] pTit) {
		this.pTit = pTit;
	}

	public String[] getCols() {
		return cols;
	}

	public void setCols(String[] cols) {
		this.cols = cols;
	}

	public String[] getTitH() {
		return titH;
	}

	public void setTitH(String[] titH) {
		this.titH = titH;
	}

	public String[] getTitN() {
		return titN;
	}

	public void setTitN(String[] titN) {
		this.titN = titN;
	}

	public Elements[] getE() {
		return e;
	}

	public void setE(Elements[] e) {
		this.e = e;
	}

	public Elements getElementsClass() {
		return EXCELTools.getInstance().getElementsInstance();
	}

	public Rule getRuleInstance() {
		return EXCELTools.getInstance().getRuleInstance();
	}

	public Map<String, Object> getData() {
		File file = new File(iFilePath);
		EXCELTools tools = new EXCELTools();
		if (isCheck != true) {
			e = new Elements[8];
			for (int i = 0; i < 8; i++) {
				e[i] = getElementsClass();
			}
		}
		return (Map<String, Object>) tools.getData(file, startRow, endColumn,
				e, isCheck);
	}

	public File setData() throws Exception {
		if (pageSize == null) {
			return EXCELTools.xlsWrite(eFilePath, fname,title, pTit, cols, titH,
					titN, dataList,width,widthP);
		} else {
			return EXCELTools.xlsWritePage(eFilePath, fname,title, pTit, cols, titH,
					titN, dataList, pageSize,width,widthP);
		}

	}
	
	public File setDatabinding() throws Exception {
		if (pageSize == null) {
			return EXCELTools.xlsWritebinding(eFilePath, fname,title, pTit, cols, titH,
					titN, dataList,width,widthP);
		} else {
			return EXCELTools.xlsWritePagebinding(eFilePath, fname,title, pTit, cols, titH,
					titN, dataList, pageSize,width,widthP);
		}

	}
	public void exportExcel(String moderName,String excelName,File exportFile,HttpServletRequest request ,HttpServletResponse response) {
		  FileInputStream fs = null;
	        try {
	            fs = new FileInputStream(exportFile);
	        } catch (FileNotFoundException e) {
	        	//LogInfo.getInstance().info(request,LogController.class,"["+moderName+"][生成excel错误! 日志管理 不存在!]["+e+"]");
	            return;
	        }
	       
	        //这个一定要设定，告诉浏览器这次请求是一个下载的数据流
	        response.setContentType("APPLICATION/OCTET-STREAM");
//	        String excelName="日志管理.xls";
	        try {
	                  excelName = new String(excelName.getBytes("gb2312"), "iso-8859-1");
	                  response.setHeader("Content-Disposition", "attachment; filename=\"" + excelName + "\"");//这里设置一下让浏览
	                  response.addHeader("Content-Length", "" + exportFile.length());
//				response.setHeader("Content-Disposition", "attachment; filename=" +  "中文文件名".getBytes("GBK"), "ISO-8859-1")  + ".xls");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				//LogInfo.getInstance().info(request,LogController.class,"["+moderName+"][Excel文件名称编码报错！]");
				e1.printStackTrace();
			}
	        // 写出流信息
	        int b = 0;
	        try {
	                //这里的 response 就是你 servlet 的那个传参进来的 response
	            PrintWriter out = response.getWriter();
	            while ((b = fs.read()) != -1) {
	                out.write(b);
	            }
	            fs.close();
	            out.close();
//	            LogInfo.getInstance().info(request,LogController.class,"["+moderName+"][下载完毕]");
	        } catch (Exception e) {
	        	//LogInfo.getInstance().info(request,LogController.class,"["+moderName+"][下载文件失败!]["+e+"]"); 
	        }
//		    LogInfo.getInstance().info(request,LogController.class,"["+moderName+"][导出excel操作结束]");
		    if(exportFile.exists()) {
		    	exportFile.delete();
		    }
		    
	}
}
