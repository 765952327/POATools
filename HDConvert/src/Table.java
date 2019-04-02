import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Table {
	private int count = 0;
	private HashMap<String,String> map = new HashMap<String,String>();
	public Table(String path) {
		Workbook wb =null;
        Sheet sheet = null;
        Row row = null;
        String cellData = null;
        String filePath = path;
        File fl = new File(path);
        for(File f : fl.listFiles()) {
        	wb = readExcel(f.getAbsolutePath());
            if(wb != null){
                //获取第一个sheet
                sheet = wb.getSheetAt(0);
                //获取最大行数
                int rownum = sheet.getPhysicalNumberOfRows();
                //获取最大列数
                int colnum = 0;
                if(rownum>0) {
                	row:for (int i = 1; i<rownum; i++) {
                        row = sheet.getRow(i);
                        if(row !=null){
                        	colnum = row.getPhysicalNumberOfCells();
                        	String key = null;
                        	String value = null;
                            for (int j=0;j<2;j++){
                                cellData = (String) getCellFormatValue(row.getCell(j));
                                if(!"".equals(cellData)) {
                                	switch(j) {
                                    case 0:
                                    	key = cellData;
                                    	break;
                                    case 1:
                                    	value = cellData;
                                    	count ++;
                                    	map.put(key, value);
                                    	break;
                                    }
                                }else {
                                	continue row;
                                }
                                
                            }
                           
                        }else{
                            break;
                        }   
                    }
                
                }
                System.out.println("导入对照项" + count + "个");
            }
        }
        
	}

    
    //读取excel
    public Workbook readExcel(String filePath){
        Workbook wb = null;
        if(filePath==null){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }
    public Object getCellFormatValue(Cell cell){
        Object cellValue = null;
        if(cell!=null){
            //判断cell类型
            switch(cell.getCellType()){
            case NUMERIC:{
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            }
            case FORMULA:{
                //判断cell是否为日期格式
                if(DateUtil.isCellDateFormatted(cell)){
                    //转换为日期格式YYYY-mm-dd
                    cellValue = cell.getDateCellValue();
                }else{
                    //数字
                    cellValue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            }
            case STRING:{
                cellValue = cell.getRichStringCellValue().getString();
                break;
            }
            default:
                cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }
    
    public HashMap<String,String> getMap(){
    	return map;
    }
    /**
     * 
     */
    public static void main(String[] args) {
    	new Table("对照表/对照.xlsx");
    }
}
