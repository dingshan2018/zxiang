package com.zxiang.common.utils.excel;






import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import jxl.Cell;
import jxl.CellView;
import jxl.Image;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class EXCELTools {
	private static final String DATA="data";
	private static final String ERROR_DATA="errorData";
	private static EXCELTools et;

	public static EXCELTools getInstance() {
		if (et == null) {
			synchronized (EXCELTools.class) {
				if (et == null) {
					et = new EXCELTools();
				}
			}
		}
		return et;
	}

	public static void getFile(byte[] bfile, String filePath, String fileName) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			File dir = new File(filePath);
			if (!dir.exists() && dir.isDirectory()) {//判断文件目录是否存在  
				dir.mkdirs();
			}
			file = new File(filePath + "\\" + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bfile);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public List<ListInfo> getDataList(File file, int startRow, int endColumn,
			Elements[] ele) {
		return (List<ListInfo>) ((Map) getData(file, startRow, endColumn, ele,
				false)).get(DATA);
	}

	public List<ListInfo> getErrorDataList(File file, int startRow,
			int endColumn, Elements[] ele) {
		return (List<ListInfo>) ((Map) getData(file, startRow, endColumn, ele,
				true)).get(ERROR_DATA);
	}

	// startRow 开始行
	// endColumn 结束列
	public Object getData(File file, int startRow, int endColumn,
			Elements[] ele, boolean isCheck) {
		List<ListInfo> list = new ArrayList<ListInfo>();
		List<ListInfo> errorList = new ArrayList<ListInfo>();
		Map<String, Object> Result = new HashMap<String, Object>();
		try {
			Workbook book = Workbook.getWorkbook(file);
			Sheet sheet = book.getSheet(0);
			int TatalCount = sheet.getRows();
			for (int i = startRow; i < TatalCount; i++) {
				Map<String, Object> map = new HashMap<String, Object>();

				Map<String, Map<String, Object>> errorMap = new HashMap<String, Map<String, Object>>();
				String row = "";
				for (int j = 0; j < endColumn; j++) {
					ListInfo listInfo = new ListInfo();
					if (ele[j] != null) {
						if (ele[j].getIsMagic() == false) {//处理非图片							
							Rule r = ele[j].getRule();
							Cell cell = sheet.getCell(j, i);
							String val = cell.getContents();
							listInfo.setImage(false);
							listInfo.setCols(j + 1);
							listInfo.setRow(i + 1);
							listInfo.setVal(val);
							listInfo.setMsgInfo(ele[j].getMsgInfo());
							// map.put(i+"-"+j , val);
							row += "(" + i + "i-j" + j + "=val:" + val + "),";
							if (isCheck == true) {
								if (vaild(r, val) == false) {
									// errorMap.put(i+"-"+j, map);
									errorList.add(listInfo);
								}
							}
							list.add(listInfo);
						} else {
							//处理图片
							Cell cell = sheet.getCell(j, i);
							String val = cell.getContents();
							listInfo.setCols(j + 1);
							listInfo.setImage(true);
							listInfo.setRow(i + 1);
							listInfo.setVal(val);
							listInfo.setMsgInfo(ele[j].getMsgInfo());
							// map.put(i+"-"+j , val);
							int c = sheet.getNumberOfImages();
							String rc = i + "-" + j;
							for (int k = 0; k < c; k++) {
								Image image = sheet.getDrawing(k);
								int key = (int) image.getRow();// 拿到圖片所在的行索引
								int col = (int) image.getColumn();
								String temp = key + "-" + col;
								if (temp.equals(rc)) {
									System.out.println(i
											+ " image.getImageData()"
											+ image.getImageData());
									listInfo.setImageData(image.getImageData());
									// map.put(key+"-"+col,
									// image.getImageData());
									// getFile(image.getImageData(),"D://",i+"-"+j+".jpg");
									break;
								}
							}
							list.add(listInfo);
						}

					}

				}

			}

		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Result.put(DATA, list);
		Result.put(ERROR_DATA, errorList);
		return Result;
	}

	private static boolean isDigitD(char ch) {
		try {
			int i = Integer.parseInt(String.valueOf(ch));
			return true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}

	}

	 //校验，单元格内容
	public static boolean vaild(Rule r, String val) {
		String reg = null;
		if (r != null) {
			reg = r.getExp();
			if (r.getMaxLength() != null) {//最大长度
				if (val.length() < r.getMaxLength()) {
					return false;
				}
			}
			if (r.getMinLenght() != null) {//最小长度
				if (val.length() > r.getMinLenght()) {
					return false;
				}
			}
			if (r.getIsNum() != null) {//是否为数字
				Pattern p = Pattern.compile("[0-9]*");
				Matcher m = p.matcher(val);
				boolean rs = m.matches();
				return rs;
			}
			if (r.getIsAbc() != null) {//是否字母
				Pattern p = Pattern.compile("^[a-zA-Z]*");
				Matcher m = p.matcher(val);
				boolean rs = m.matches();
				return rs;
			}
		}
		if (reg == null) {//正则表达式验证
			return true;
		} else {
			Pattern p = Pattern.compile(reg);
			Matcher m = p.matcher(val);
			boolean rs = m.matches();
			return rs;
		}

	}

	//获取标题
	public static Object getTitles(File file, int r, int c) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Workbook book;
		try {
			book = Workbook.getWorkbook(file);
			Sheet sheet = book.getSheet(0);
			for (int i = 0; i < r; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int j = 0; j < c; j++) {
					Cell cell = sheet.getCell(j, i);
					String val = cell.getContents();
					map.put(i + "-" + j, val);
				}
				list.add(map);
			}
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 表格大标题样式
	 * @return
	 * @throws Exception
	 */
	private static WritableCellFormat BTFormat() throws Exception {
		try {
			WritableFont font = new WritableFont(WritableFont.ARIAL, 14,
					WritableFont.BOLD, true);
			WritableCellFormat format = new WritableCellFormat(font);
			format.setAlignment(jxl.format.Alignment.CENTRE);
			format.setBorder(Border.ALL, BorderLineStyle.THIN,
					jxl.format.Colour.BLACK);
			return format;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 大标题(即文件名称)
	 * @param sheet         工作表
	 * @param fname         文件名称
	 * @throws Exception
	 */
	private static void writeBTitle(WritableSheet sheet, String fname)
			throws Exception {
		try {
			Label t = new Label(0, 0, fname, BTFormat());
			sheet.addCell(t);
			//设置行高500
			sheet.setRowView(0, 500, false);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 表格小标题样式
	 * @return
	 * @throws Exception
	 */
	private static WritableCellFormat STFormat() throws Exception {
		try {
			WritableFont font = new WritableFont(WritableFont.ARIAL, 12,
					WritableFont.BOLD);
			WritableCellFormat format = new WritableCellFormat(font);
			format.setAlignment(jxl.format.Alignment.CENTRE);
			format.setBackground(jxl.format.Colour.GREY_25_PERCENT);
			format.setBorder(Border.ALL, BorderLineStyle.THIN,
					jxl.format.Colour.BLACK);
			return format;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 序号列
	 * @param sheet        工作表
	 * @param col          起始列
	 * @param row          起始行
	 * @return
	 * @throws Exception
	 */
	private static int writeIndex(WritableSheet sheet, int col, int row)
			throws Exception {
		try {
			Label label = new Label(col, row, "序号", STFormat());
			sheet.addCell(label);
			sheet.setColumnView(col, 8);
			col++;
		} catch (Exception e) {
			throw e;
		}
		return col;
	}

	/**
	 * 父级小标题
	 * @param sheet         工作表
	 * @param pTit          父级标题
	 * @param cols          父级标题跨越单元格数
	 * @param col           起始列
	 * @param row           起始行
	 * @return              子级标题起始列
	 * @throws Exception
	 */
	private static int writePTitle(WritableSheet sheet, String[] pTit,
			String[] cols, int col, int row,String widthP[]) throws Exception {
		try {
			int scol = col;
			for (int i = 0; i < pTit.length; i++) {
				String tit = pTit[i];
				Label label = new Label(scol, row, tit, STFormat());
				sheet.addCell(label);
				if(widthP !=null) {
					if(widthP.length<=i||widthP[i].equals("")){
						sheet.setColumnView(col, (tit.length() * 4));
					}else {
						sheet.setColumnView(col, (Integer.valueOf(widthP[i])));	
					}

				}else {
					sheet.setColumnView(col, (tit.length() * 4));
				}
				int c = Integer.parseInt(cols[i]);
				if (c > 0) {
					sheet.mergeCells(scol, row, scol + (c - 1), row);
					scol = scol + c;
				} else {
					sheet.mergeCells(scol, row, scol, row + 1);
					scol++;
					//col++;
				}
			}
		} catch (Exception e) {
			throw e;
		}
		boolean t=true;
		for (int i = 0; i < cols.length; i++) {
			int c = Integer.parseInt(cols[i]);
			if(t&&c == 0) {
				col++;
			}else {
				t=false;
			}
		}
		return col;
	}

	/**
	 * 子级小标题
	 * @param sheet         工作表
	 * @param titH          表格标题
	 * @param col           起始列
	 * @param row           起始行
	 * @return              返回总列数
	 * @throws Exception
	 */
	private static int writeSTitle(WritableSheet sheet, String[] titH, int col,
			int row,String width[]) throws Exception {
		try {
			for(int i=0; i<titH.length;i++) {
				String tit = titH[i];
				if (tit != null) {
					Label label = new Label(col, row, tit, STFormat());
					sheet.addCell(label);
					if(width==null) {
						sheet.setColumnView(col, (tit.length() * 4));	
					}else {
						if(width.length<=i||width[i].equals("")){
							sheet.setColumnView(col, (tit.length() * 4));
						}else {
							sheet.setColumnView(col, (Integer.valueOf(width[i])));	
						}
					}
								
					col++;
				}
			}
			for (String tit : titH) {
				
			}
		} catch (Exception e) {
			throw e;
		}
		return col;
	}

	/**
	 * 表格数据样式
	 * @return
	 * @throws Exception
	 */
	private static WritableCellFormat DTFormat() throws Exception {
		try {
			WritableFont font = new WritableFont(WritableFont.ARIAL, 10);
			WritableCellFormat format = new WritableCellFormat(font);
			format.setAlignment(jxl.format.Alignment.CENTRE);
			format.setWrap(true);
			format.setBorder(Border.ALL, BorderLineStyle.THIN,
					jxl.format.Colour.BLACK);
			return format;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 写入数据
	 * @param sheet          工作表
	 * @param titN           表格标题索引
	 * @param dataList       数据集合
	 * @param row            起始行
	 * @return
	 * @throws Exception
	 */
	private static int writeData(WritableSheet sheet, String[] titN,
			List<Map<String, String>> dataList, int row,boolean serial) throws Exception {
		try {
			long begin = System.currentTimeMillis();
			if (dataList != null) {
				for (int i = 0; i < dataList.size(); i++) {
					int col = 0;
					Map<String, String> record = dataList.get(i);
					if(serial) {
						// 序号
						sheet.addCell(new Label(col++, row, (i + 1) + "",
								DTFormat()));
					}
					for (String idx : titN) {
						String dv = "";
						// BigDecimal无法转为String，先转为Object
						Object obj = record.get(idx);
						if (obj != null) {
							dv = obj.toString();
						}
						if (idx.indexOf("image") != -1) {
							if (dv != null && !dv.equals("")) {
								File fileP = new File(dv);
								String[] ImageType = dv.split("\\.");
								File newFile = null;
								BufferedImage bufferedImage = null;
								if (!ImageType.equals("PNG")) {
									newFile = new File(ImageType[0] + ".PNG");
									System.out.println("ImageType[0]+.PNG==="
											+ ImageType[0] + ".PNG");
									bufferedImage = ImageIO.read(fileP);

									ImageIO.write(bufferedImage, "PNG", newFile);
									fileP = newFile;
								}
								bufferedImage = ImageIO.read(fileP);
								int width = bufferedImage.getWidth();
								int height = bufferedImage.getHeight();
								WritableImage image = new WritableImage(col,
										row, 1, 1, fileP);
								int h = height * 15;
								int w = width * 40;
								CellView navCellView = new CellView();
								navCellView.setSize(w);
								sheet.setColumnView(col, navCellView);
								sheet.setRowView(row, h, false);
								sheet.addImage(image);
							}
						}
						Label label = new Label(col++, row, dv, DTFormat());
						sheet.addCell(label);
					}
					row++;
				}
			}
			long end = System.currentTimeMillis();
		} catch (Exception e) {
			throw e;
		}
		return row;
	}

	/**
	 * 输出EXCEL单个文件
	 * @param sPath          临时文件路径
	 * @param title          大标题
	 * @param fname          文件名称
	 * @param pTit           父级标题
	 * @param cols           父级标题跨越单元格数
	 * @param titH           子级标题
	 * @param titN           标题索引
	 * @param dataList       导出的数据集合
	 * @return               EXCEL文件
	 * @throws Exception
	 */
	public static File xlsWrite(String sPath, String fname, String title, String[] pTit,
			String[] cols, String[] titH, String[] titN,
			List<Map<String, String>> dataList,String width[],String widthP[]) throws Exception {
		WritableWorkbook workbook = null;
		try {
			File existsFile = new File(sPath);
			if(!existsFile.exists()) {
				existsFile.mkdirs();
			}
			File file = new File(sPath,fname+".xls");
			workbook = Workbook.createWorkbook(file);
			WritableSheet sheet = workbook.createSheet(fname, 1);
			//大标题
			writeBTitle(sheet,title);
			int col = 0;
			int row = 1;
			//序号列
			col = writeIndex(sheet, col, row);
			//父级标题
			if (pTit != null && pTit.length > 0) {
				sheet.mergeCells(col - 1, row, col - 1, row + 1);
				col = writePTitle(sheet, pTit, cols, col, row,widthP);
				row++;
			}
			//子级标题
			col = writeSTitle(sheet, titH, col, row,width);
			row++;
			sheet.mergeCells(0, 0, (col - 1), 0);
			writeData(sheet, titN, dataList, row,true);
			workbook.write();
			return file;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (workbook != null)
					workbook.close();
			} catch (Exception e) {
				throw e;
			}
		}

	}

	/**
	 * 输出EXCEL单个文件
	 * @param sPath          临时文件路径
	 * @param title          大标题
	 * @param fname          文件名称
	 * @param pTit           父级标题
	 * @param cols           父级标题跨越单元格数
	 * @param titH           子级标题
	 * @param titN           标题索引
	 * @param dataList       导出的数据集合
	 * @return               EXCEL文件
	 * @throws Exception
	 */
	public static File xlsWritebinding(String sPath, String fname, String title, String[] pTit,
			String[] cols, String[] titH, String[] titN,
			List<Map<String, String>> dataList,String width[],String widthP[]) throws Exception {
		WritableWorkbook workbook = null;
		try {
			File existsFile = new File(sPath);
			if(!existsFile.exists()) {
				existsFile.mkdirs();
			}
			File file = new File(sPath,fname+".xls");
			workbook = Workbook.createWorkbook(file);
			WritableSheet sheet = workbook.createSheet(fname, 1);
			int col = 0;
			int row = 0;
			//父级标题
			if (pTit != null && pTit.length > 0) {
				sheet.mergeCells(col - 1, row, col - 1, row + 1);
				col = writePTitle(sheet, pTit, cols, col, row,widthP);
				row++;
			}
			//子级标题
			col = writeSTitle(sheet, titH, col, row,width);
			row++;
			writeData(sheet, titN, dataList, row,false);
			workbook.write();
			return file;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (workbook != null)
					workbook.close();
			} catch (Exception e) {
				throw e;
			}
		}

	}
	
	

	/**
	 * 写入数据
	 * @param sheet          工作表
	 * @param titN           表格标题索引
	 * @param dataList       数据集合
	 * @param row            起始行
	 * @return
	 * @throws Exception
	 */
	private static int writeDataPage(WritableSheet sheet, String[] titN,
			List<Map<String, String>> dataList, int row, int page, int pageSize,boolean seria)
			throws Exception {
		try {
			long begin = System.currentTimeMillis();
			if (dataList != null) {
				for (int i = page * pageSize; i < dataList.size(); i++) {
					if (i == (page + 1) * pageSize) {
						break;
					}
					int col = 0;
					Map<String, String> record = dataList.get(i);
					if(seria) {
						// 序号
						sheet.addCell(new Label(col++, row, (i + 1) + "",
								DTFormat()));
					}
					for (String idx : titN) {
						String dv = "";
						// BigDecimal无法转为String，先转为Object
						Object obj = record.get(idx);
						if (obj != null) {
							dv = obj.toString();
						}
						if (idx.indexOf("image") != -1) {
							if (dv != null && !dv.equals("")) {
								File fileP = new File(dv);
								String[] ImageType = dv.split("\\.");
								File newFile = null;
								BufferedImage bufferedImage = null;
								if (!ImageType.equals("PNG")) {
									newFile = new File(ImageType[0] + ".PNG");
									System.out.println("ImageType[0]+.PNG==="
											+ ImageType[0] + ".PNG");
									bufferedImage = ImageIO.read(fileP);

									ImageIO.write(bufferedImage, "PNG", newFile);
									fileP = newFile;
								}
								bufferedImage = ImageIO.read(fileP);
								int width = bufferedImage.getWidth();
								int height = bufferedImage.getHeight();
								WritableImage image = new WritableImage(col,
										row, 1, 1, fileP);
								int h = height * 15;
								int w = width * 40;
								CellView navCellView = new CellView();
								navCellView.setSize(w);
								sheet.setColumnView(col, navCellView);
								sheet.setRowView(row, h, false);
								sheet.addImage(image);
							}
						}
						Label label = new Label(col++, row, dv, DTFormat());
						sheet.addCell(label);
					}
					row++;
				}
				long end = System.currentTimeMillis();
			}

		} catch (Exception e) {
			throw e;
		}
		return row;
	}

	/**
	 * 输出EXCEL单个文件
	 * @param sPath          临时文件路径
	 * @param fname          文件名称
	 * @param title          大标题
	 * @param pTit           父级标题
	 * @param cols           父级标题跨越单元格数
	 * @param titH           子级标题
	 * @param titN           标题索引
	 * @param pageSize       分页
	 * @param width          单元格设置宽度
	 * @param widthP         设置大标题宽度
	 * @param dataList       导出的数据集合
	 * @return               EXCEL文件
	 * @throws Exception
	 */
	public static File xlsWritePage(String sPath, String fname,String title, String[] pTit,
			String[] cols, String[] titH, String[] titN,
			List<Map<String, String>> dataList, int pageSize,String width[],String widthP []) throws Exception {
		WritableWorkbook workbook = null;
		try {
			File file = new File(sPath);
			workbook = Workbook.createWorkbook(file);
			int page = 1;
			if (dataList != null && dataList.size() > 0) {
				if (dataList.size() % pageSize != 0) {
					page = dataList.size() / pageSize + 1;
				} else {
					page = dataList.size() / pageSize;
				}

			}
			Map m = new HashMap<String, Object>();
			for (int i = 0; i < page; i++) {
				WritableSheet sheet = workbook.createSheet(fname + (1 + i),
						1 + i);
				//大标题
				writeBTitle(sheet,title);
				int col = 0;
				int row = 1;
				//序号列
				col = writeIndex(sheet, col, row);
				//父级标题
				if (pTit != null && pTit.length > 0) {
					sheet.mergeCells(col - 1, row, col - 1, row + 1);
					col = writePTitle(sheet, pTit, cols, col, row,widthP);
					row++;
				}
				//子级标题
				col = writeSTitle(sheet, titH, col, row,width);
				row++;
				sheet.mergeCells(0, 0, (col - 1), 0);
				writeDataPage(sheet, titN, dataList, row, i, pageSize,true);
			}

			workbook.write();
			return file;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (workbook != null)
					workbook.close();
			} catch (Exception e) {
				throw e;
			}
		}

	}
	
	/**
	 * 输出EXCEL单个文件
	 * @param sPath          临时文件路径
	 * @param fname          文件名称
	 * @param title          大标题
	 * @param pTit           父级标题
	 * @param cols           父级标题跨越单元格数
	 * @param titH           子级标题
	 * @param titN           标题索引
	 * @param pageSize       分页
	 * @param width          单元格设置宽度
	 * @param widthP         设置大标题宽度
	 * @param dataList       导出的数据集合
	 * @return               EXCEL文件
	 * @throws Exception
	 */
	public static File xlsWritePagebinding(String sPath, String fname,String title, String[] pTit,
			String[] cols, String[] titH, String[] titN,
			List<Map<String, String>> dataList, int pageSize,String width[],String widthP []) throws Exception {
		WritableWorkbook workbook = null;
		try {
			File file = new File(sPath);
			workbook = Workbook.createWorkbook(file);
			int page = 1;
			if (dataList != null && dataList.size() > 0) {
				if (dataList.size() % pageSize != 0) {
					page = dataList.size() / pageSize + 1;
				} else {
					page = dataList.size() / pageSize;
				}

			}
			Map m = new HashMap<String, Object>();
			for (int i = 0; i < page; i++) {
				WritableSheet sheet = workbook.createSheet(fname + (1 + i),
						1 + i);
				int col = 0;
				int row = 0;
				//父级标题
				if (pTit != null && pTit.length > 0) {
					sheet.mergeCells(col - 1, row, col - 1, row + 1);
					col = writePTitle(sheet, pTit, cols, col, row,widthP);
					row++;
				}
				//子级标题
				col = writeSTitle(sheet, titH, col, row,width);
				row++;
				writeDataPage(sheet, titN, dataList, row, i, pageSize,false);
			}

			workbook.write();
			return file;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (workbook != null)
					workbook.close();
			} catch (Exception e) {
				throw e;
			}
		}

	}
	
	
	

	 public static void main(String args[]) {
			EXCELObject s =new EXCELObject();
			s.seteFilePath("D:/2.xls");
			String fname = "测试数据";
			String[] pTit = {"AAAAAAA","B","C","D"};
			String width[]={"100","30","40","","","",""};
			String WidthP[]={"100","","",""};
			String[] cols = {"0","1","2","3"};
			String[] titH = {"BBBB1,300","CCCCCCCCCC1","CCCcccccccccccccccccccC2","DDDDDDDDDDDDD1","DDD2","D3"};
			String[] titN = {"A","B1","imag","imag1","D1","D2","D3"};
			
			List<Map<String, String>> dataList = new ArrayList<Map<String,String>>();
			for(int i=0;i<10003;i++) {
				Map<String, String> tmp = new HashMap<String, String>();
				tmp.put("B1","B1");
				tmp.put("image","D://10010-(2S).PNG");
				tmp.put("imag1","D://0-3.PNG");
				 
				 
				tmp.put("D1","D1");
				tmp.put("D2",null);
				tmp.put("D3","D3");
				tmp.put("A",i+"");
				tmp.put("S","S");
				dataList.add(tmp);
			}
			s.setFname("测试数据");
			s.setpTit(pTit);
			s.setCols(cols);
			s.setWidth(width);
			s.setWidthP(WidthP);
			s.setTitH(titH);
			s.setTitN(titN);
			s.setDataList(dataList);
			try {
				File f = s.setData();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	
	public Rule getRuleInstance() {
		return new Rule();
	}

	class Rule {
		public Integer maxLength;
		public Integer minLenght;
		public Integer isNum;//1是数字，
		public Integer isAbc;//1是字母。
		public String exp;
		
		

		public Integer getMaxLength() {
			return maxLength;
		}

		public void setMaxLength(Integer maxLength) {
			this.maxLength = maxLength;
		}

		public Integer getMinLenght() {
			return minLenght;
		}

		public void setMinLenght(Integer minLenght) {
			this.minLenght = minLenght;
		}

		public String getExp() {
			return exp;
		}

		public void setExp(String exp) {
			this.exp = exp;
		}

		public Integer getIsNum() {
			return isNum;
		}

		public void setIsNum(Integer isNum) {
			this.isNum = isNum;
		}

		public Integer getIsAbc() {
			return isAbc;
		}

		public void setIsAbc(Integer isAbc) {
			this.isAbc = isAbc;
		}

	}

	class Elements {
		private String id;
		private String title;
		private Rule rule;
		private boolean isMagic;
		private String msgInfo;

		public String getMsgInfo() {
			return msgInfo;
		}

		public void setMsgInfo(String msgInfo) {
			this.msgInfo = msgInfo;
		}

		public void setMagic(boolean isMagic) {
			this.isMagic = isMagic;
		}

		public boolean getIsMagic() {
			return isMagic;
		}

		public void setIsMagic(boolean isMagic) {
			this.isMagic = isMagic;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Rule getRule() {
			return rule;
		}

		public void setRule(Rule rule) {
			this.rule = rule;
		}

	}

	public Elements getElementsInstance() {
		return new Elements();
	}

	class ListInfo {
		private Integer row;
		private Integer cols;
		private String val;
		private String msgInfo;
		private boolean isImage;
		private byte imageData[];

		public byte[] getImageData() {
			return imageData;
		}

		public void setImageData(byte[] imageData) {
			this.imageData = imageData;
		}

		public boolean isImage() {
			return isImage;
		}

		public void setImage(boolean isImage) {
			this.isImage = isImage;
		}

		public Integer getRow() {
			return row;
		}

		public void setRow(Integer row) {
			this.row = row;
		}

		public Integer getCols() {
			return cols;
		}

		public void setCols(Integer cols) {
			this.cols = cols;
		}

		public String getVal() {
			return val;
		}

		public void setVal(String val) {
			this.val = val;
		}

		public String getMsgInfo() {
			return msgInfo;
		}

		public void setMsgInfo(String msgInfo) {
			this.msgInfo = msgInfo;
		}

	}

}
