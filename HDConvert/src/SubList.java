import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SubList {
	
	public HashMap<String, ArrayList<Subject>> list = new HashMap<String, ArrayList<Subject>>();
	public ArrayList<Subject> subs = new ArrayList<Subject>();
	private String filePath = null;
	public SubList(String path) {
		filePath = path;
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		String cellData = null;
		list.put("教育部", new ArrayList<Subject>());
		wb = readExcel(path);
		if (wb != null) {
			// 获取第一个sheet
			sheet = wb.getSheetAt(0);
			// 获取最大行数
			int rownum = sheet.getPhysicalNumberOfRows();
			// 获取最大列数
			int colnum = 0;
			row: for (int i = 0; i < rownum; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					colnum = row.getPhysicalNumberOfCells();
					if (colnum > 0) {
						int j = 0;
						Subject sub = new Subject();
						col: for (; j < colnum; j++) {
							cellData = (String) getCellFormatValue(row.getCell(j));
							if (j == 0) {
								if (isNumeric(cellData)) {
									continue;
								} else {
									continue row;
								}
							}
							switch (j) {
							case 1:
								sub.kcdm = cellData;
								break;
							case 2:
								sub.kcmc = cellData;
								break;
							case 3:
								sub.ksdy = cellData;
								break;
							case 4:
								sub.kslx = cellData;
								break;
							case 5:
								//System.out.println("LEN:" + cellData);
								break col;
							}
						}
						//System.out.println(sub.toString());
						for (++j; j < colnum; j++) {
							cellData = (String) getCellFormatValue(row.getCell(j));
							if ("".equals(cellData)) {
								break;
							}
							//System.out.println("cellData" + cellData);
							if(list.get(cellData) == null) {
								list.put(cellData, new ArrayList<Subject>());
							}
							list.get(cellData).add(sub);
							
							sub.append(cellData);
						}
						list.get("教育部").add(sub);
						subs.add(sub);
						//System.out.println();
					}

				} else {
					break;
				}

			}
			
		}
	}

	public boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			int chr = str.charAt(i);
			if (chr < 48 || chr > 57)
				return false;
		}
		return str.length() == 0 ? false : true;
	}

	// 读取excel
	public Workbook readExcel(String filePath) {
		Workbook wb = null;
		if (filePath == null) {
			return null;
		}
		String extString = filePath.substring(filePath.lastIndexOf("."));
		InputStream is = null;
		try {
			is = new FileInputStream(filePath);
			if (".xls".equals(extString)) {
				return wb = new HSSFWorkbook(is);
			} else if (".xlsx".equals(extString)) {
				return wb = new XSSFWorkbook(is);
			} else {
				return wb = null;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wb;
	}

	public Object getCellFormatValue(Cell cell) {
		Object cellValue = null;
		if (cell != null) {
			// 判断cell类型
			switch (cell.getCellType()) {
			case NUMERIC: {
				cellValue = String.valueOf(cell.getNumericCellValue());
				break;
			}
			case FORMULA: {
				// 判断cell是否为日期格式
				if (DateUtil.isCellDateFormatted(cell)) {
					// 转换为日期格式YYYY-mm-dd
					cellValue = cell.getDateCellValue();
				} else {
					// 数字
					cellValue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			case STRING: {
				cellValue = cell.getRichStringCellValue().getString();
				break;
			}
			default:
				cellValue = "";
			}
		} else {
			cellValue = "";
		}
		return cellValue;
	}
	
	/**
	 **获取每个省市对应的科目
	 * @return
	 */
	public HashMap<String,ArrayList<Subject>> getList(){
		return list;
	}
	/**
	 * 获取课程列表 
	 * @return
	 */
	public ArrayList<Subject> getSubs(){
		return subs;
	}
}
