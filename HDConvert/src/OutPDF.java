import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 
 * @author Sunxy 生成PDF
 */
public class OutPDF {
	Font FontChinese;
	static String titleDate = null;
	String dirName = null;
	HashMap<String,String> map = null;
	HashMap<String, ArrayList<Subject>> list = null;
	
	/**
	 * 
	 * @param tm   对照表
	 * @param list 汇总数据表
	 */
	public OutPDF(String dirName,HashMap<String, String> tm, HashMap<String, ArrayList<Subject>> list) {
		this.dirName = dirName;
		map = tm;
		this.list = list;
	}

	public void simplePDF(String deviceType) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			FontChinese = new Font(bfChinese, 12, Font.NORMAL);
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("E:\\FIRSTPDF.pdf"));

			// PdfWriter.getInstance(document, baos);
			document.open();
			PdfPTable table = null;
			if (deviceType == "固话") {
				table = new PdfPTable(6);
				table.addCell(getCell("清单类型", 1, 1));
				table.addCell(getCell("使用设备", 1, 1));
				table.addCell(getCell("日期", 1, 1));
				table.addCell(getCell("起始时间", 1, 1));
				table.addCell(getCell("通话时长", 1, 1));
				table.addCell(getCell("通信费用", 1, 1));

				table.addCell(getCell("固网彩铃爱音乐铃声费", 1, 1));
				table.addCell(getCell("2162146padding6", 1, 1));
				table.addCell(getCell("20180801", 1, 1));
				table.addCell(getCell("00:00:00", 1, 1));
				table.addCell(getCell("0", 1, 1));
				table.addCell(getCell("￥0.00", 1, 1));
			} else if (deviceType == "手机") {
				table = new PdfPTable(10);
				table.addCell(getCell("通话类型", 1, 1));
				table.addCell(getCell("开始时间", 1, 1));
				table.addCell(getCell("时长", 1, 1));
				table.addCell(getCell("呼叫类型", 1, 1));
				table.addCell(getCell("对方号码", 1, 1));
				table.addCell(getCell("通话地点", 1, 1));
				table.addCell(getCell("基本费用（元）", 1, 1));
				table.addCell(getCell("信息费用（元）", 1, 1));
				table.addCell(getCell("其他费用（元）", 1, 1));
				table.addCell(getCell("总费用（元）", 1, 1));

				table.addCell(getCell("固网彩铃爱音乐铃声费", 1, 1));
				table.addCell(getCell("2162146padding6", 1, 1));
				table.addCell(getCell("20180801", 1, 1));
				table.addCell(getCell("00:00:00", 1, 1));
				table.addCell(getCell("0", 1, 1));
				table.addCell(getCell("￥0.00", 1, 1));
				table.addCell(getCell("20180801", 1, 1));
				table.addCell(getCell("00:00:00", 1, 1));
				table.addCell(getCell("0", 1, 1));
				table.addCell(getCell("￥0.00", 1, 1));
			}
			table.setWidthPercentage(110); // 设置表格宽度

			document.add(table);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private PdfPCell getCell(String cellValue, int colspan, int rowSpan) {
		PdfPCell cell = new PdfPCell();
		try {
			cell = new PdfPCell(new Phrase(cellValue, FontChinese));
			cell.setRowspan(rowSpan);
			cell.setColspan(colspan);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cell;
	}
	
	
	/**
	 * 打印城市列表
	 */
	public static void outSubs(String dirName,ArrayList<Subject> list) {
		int id = 0;
		int padding = 5;
		Document document = new Document(PageSize.A4); 
		PdfWriter pdf = null;
		try {
			pdf = PdfWriter.getInstance(document, new FileOutputStream(dirName + ".PDF"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		//③打开文档。
		document.open(); 
		//②建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
		
		 //方法一：使用Windows系统字体(TrueType)   
		BaseFont baseFont = null;
		try {
			baseFont = BaseFont.createFont("fonts/SIMSUN.TTC,0",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED); 
			//BaseFont baseFont = BaseFont.createFont("STSong-Light",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);  
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//④向文档中添加内容。
		Font titleFont = new Font(baseFont,18,Font.BOLD);
		Font secFont = new Font(baseFont,15,Font.BOLD);
		Font font = new Font(baseFont,13);   
		String title = titleDate + "全国统一命题试题清样寄发通知";
		Paragraph p = new Paragraph(title,titleFont);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		p.setSpacingAfter(padding*4);
		p.setSpacingBefore(padding*6);
		try {
			document.add(p);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		for(Subject k : list) {
			
			try {
				p = new Paragraph(k.kcdm + " " + k.kcmc + " " + k.ksdy + " " + k.kslx + " 共计" + k.getList().size() + "省",secFont);
				p.setAlignment(Paragraph.ALIGN_LEFT);
				p.setSpacingAfter(padding);
				p.setSpacingBefore(padding);
				p.setIndentationRight(padding*8);
				document.add(p);
				p = new Paragraph("———————————————————————————————————————————");
				p.setAlignment(Paragraph.ALIGN_CENTER);
				document.add(p);
				
				PdfPTable table = new PdfPTable(6);
				
				for(String s:k.getList()) {
					p = new Paragraph(s,font);
					p.setAlignment(Paragraph.ALIGN_CENTER);
					
					PdfPCell cell = new PdfPCell(p);
					cell.setPadding(padding);
					cell.disableBorderSide(15);
					table.addCell(cell);
				}
				if(k.getList().size() % 6 !=0) {
					int dk = 6 - (k.getList().size() % 6);
					for(int i = 0;i<dk;i++) {
						p = new Paragraph("",font);
						PdfPCell cell = new PdfPCell(p);
						cell.setPadding(padding);
						cell.disableBorderSide(15);
						table.addCell(cell);
					}
					
				}
				table.setSpacingAfter(padding*8);
				table.setSpacingBefore(padding*2);
				document.add(table);
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		document.close();
		
	}
	
	
	public void outAll() {
		int id = 0;
		ArrayList<Subject> subs = null;
		int padding = 5;
		Document document = new Document(PageSize.A4); 
		new File(dirName + "/").mkdirs();
		PdfWriter pdf = null;
		try {
			pdf = PdfWriter.getInstance(document, new FileOutputStream(dirName + ".PDF"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		//③打开文档。
		document.open(); 
		for(String k : list.keySet()) {
			subs = list.get(k);
			String title = titleDate + "高等教育自学考试全国统一命题课程试卷清样交接单";
			String p0 = map.get(k) + ":";//"天津市自学考试办公室";
			System.out.println("==>生成:" + map.get(k) + ".PDF");
			String p1 = "现将" + titleDate + "高等教育自学考试全国统一命题共{@count}门课程试卷清样寄给你办，请查收。请将试卷清样交接单回执寄回我处。";
			String p2 = "高等教育自学考试哈尔滨命题中心";
			String p3 = new java.text.SimpleDateFormat("yyyy年MM月dd日").format(new Date());//"2019年1月18日";
			String b0 = "全国统一命题哈尔滨命题中心试卷清样交接单回执";
			String b1 = "高等教育自学考试哈尔滨命题中心：";
			String b2 = "以下课程试卷清样已收到：";
			try {
				
				//②建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
				
				 //方法一：使用Windows系统字体(TrueType)    
		        BaseFont baseFont = BaseFont.createFont("fonts/SIMSUN.TTC,0",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED); 
				//BaseFont baseFont = BaseFont.createFont("STSong-Light",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);  
				//④向文档中添加内容。
				Font titleFont = new Font(baseFont,18,Font.BOLD);
				Font secFont = new Font(baseFont,15,Font.BOLD);
				Font font = new Font(baseFont,13);   
				
				Paragraph p = new Paragraph(title,titleFont);
				p.setAlignment(Paragraph.ALIGN_CENTER);
				p.setSpacingAfter(padding*4);
				p.setSpacingBefore(padding*6);
				Chapter cp = new Chapter(p, 1);
				cp.setNumberDepth(-1);
				cp.setBookmarkTitle(++id + "."+ map.get(k));
				cp.setBookmarkOpen(true);
				document.add(cp);
				p = new Paragraph(p0,secFont);
				p.setAlignment(Paragraph.ALIGN_LEFT);
				p.setSpacingAfter(padding);
				p.setSpacingBefore(padding);
				p.setIndentationLeft(padding*2);
				document.add(p); 
				p = new Paragraph(p1.replace("{@count}", subs.size() + ""),font);
				p.setAlignment(Paragraph.ALIGN_LEFT);
				p.setLeading(padding*5);
				p.setSpacingAfter(padding);
				p.setSpacingBefore(padding);
				p.setFirstLineIndent(24);
				p.setIndentationLeft(padding*2);
				p.setIndentationRight(padding*4);
				document.add(p); 
				p = new Paragraph(p2,secFont);
				p.setAlignment(Paragraph.ALIGN_RIGHT);
				p.setSpacingAfter(padding);
				p.setSpacingBefore(padding);
				p.setIndentationRight(padding*8);
				document.add(p);
				p = new Paragraph(p3,secFont);
				p.setAlignment(Paragraph.ALIGN_RIGHT);
				p.setSpacingAfter(padding);
				p.setSpacingBefore(padding);
				p.setIndentationRight(padding*8);
				document.add(p);
				p = new Paragraph("———————————————————————————————————————————");
				p.setAlignment(Paragraph.ALIGN_CENTER);
				document.add(p);
				
				
				p = new Paragraph(b0,titleFont);
				p.setAlignment(Paragraph.ALIGN_CENTER);
				p.setSpacingAfter(padding*4);
				p.setSpacingBefore(padding*2);
				document.add(p);
				p = new Paragraph(b1,secFont);
				p.setAlignment(Paragraph.ALIGN_LEFT);
				p.setSpacingAfter(padding);
				p.setSpacingBefore(padding);
				p.setIndentationLeft(padding*2);
				document.add(p);
				p = new Paragraph(b2,font);
				p.setAlignment(Paragraph.ALIGN_LEFT);
				p.setIndentationLeft(padding*7);
				p.setSpacingAfter(padding);
				p.setSpacingBefore(padding);
				document.add(p);
				
				PdfPTable table = new PdfPTable(2);
				
				for(Subject s :subs) {
					p = new Paragraph(s.kcdm + " " + s.kcmc,font);
					p.setAlignment(Paragraph.ALIGN_CENTER);
					
					PdfPCell cell = new PdfPCell(p);
					cell.setPadding(padding);
					cell.disableBorderSide(15);
					table.addCell(cell);
				}
				if(subs.size()%2 !=0) {
					p = new Paragraph("",font);
					PdfPCell cell = new PdfPCell(p);
					cell.setPadding(padding);
					cell.disableBorderSide(15);
					table.addCell(cell);
				}
				table.setSpacingAfter(padding*8);
				table.setSpacingBefore(padding*2);
				document.add(table);
				
				p = new Paragraph(map.get(k),secFont);
				p.setAlignment(Paragraph.ALIGN_RIGHT);
				p.setSpacingAfter(padding);
				p.setSpacingBefore(padding);
				p.setIndentationLeft(padding*8);
				p.setIndentationRight(padding*8);
				document.add(p);
				p = new Paragraph("年    月	    日（盖章）",secFont);
				p.setAlignment(Paragraph.ALIGN_RIGHT);
				p.setSpacingAfter(padding);
				p.setSpacingBefore(padding);
				p.setIndentationLeft(padding*8);
				p.setIndentationRight(padding*8);
				document.add(p);
				document.newPage();
				//document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		document.close();
	}
	
	
	public void out() {
		ArrayList<Subject> subs = null;
		int padding = 5;
		for(String k : list.keySet()) {
			subs = list.get(k);
			String title = titleDate + "高等教育自学考试全国统一命题课程试卷清样交接单";
			String p0 = map.get(k) + ":";//"天津市自学考试办公室";
			System.out.println("==>生成:" + map.get(k) + ".PDF");
			String p1 = "现将" + titleDate + "高等教育自学考试全国统一命题共{@count}门课程试卷清样寄给你办，请查收。请将试卷清样交接单回执寄回我处。";
			String p2 = "高等教育自学考试哈尔滨命题中心";
			String p3 = new java.text.SimpleDateFormat("yyyy年MM月dd日").format(new Date());//"2019年1月18日";
			String b0 = "全国统一命题哈尔滨命题中心试卷清样交接单回执";
			String b1 = "高等教育自学考试哈尔滨命题中心：";
			String b2 = "以下课程试卷清样已收到：";
			try {
				Document document = new Document(PageSize.A4); 
				//②建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
				new File(dirName + "/").mkdirs();
				PdfWriter pdf = PdfWriter.getInstance(document, new FileOutputStream(dirName + "/" + map.get(k) + ".PDF")); 
				//③打开文档。
				document.open(); 
				 //方法一：使用Windows系统字体(TrueType)    
		        BaseFont baseFont = BaseFont.createFont("fonts/SIMSUN.TTC,0",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED); 
				//BaseFont baseFont = BaseFont.createFont("STSong-Light",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);  
				//④向文档中添加内容。
				Font titleFont = new Font(baseFont,18,Font.BOLD);
				Font secFont = new Font(baseFont,15,Font.BOLD);
				Font font = new Font(baseFont,13);   
				
				Paragraph p = new Paragraph(title,titleFont);
				p.setAlignment(Paragraph.ALIGN_CENTER);
				p.setSpacingAfter(padding*4);
				p.setSpacingBefore(padding*6);
				document.add(p);
				p = new Paragraph(p0,secFont);
				p.setAlignment(Paragraph.ALIGN_LEFT);
				p.setSpacingAfter(padding);
				p.setSpacingBefore(padding);
				p.setIndentationLeft(padding*2);
				document.add(p); 
				p = new Paragraph(p1.replace("{@count}", subs.size() + ""),font);
				p.setAlignment(Paragraph.ALIGN_LEFT);
				p.setLeading(padding*5);
				p.setSpacingAfter(padding);
				p.setSpacingBefore(padding);
				p.setFirstLineIndent(24);
				p.setIndentationLeft(padding*2);
				p.setIndentationRight(padding*4);
				document.add(p); 
				p = new Paragraph(p2,secFont);
				p.setAlignment(Paragraph.ALIGN_RIGHT);
				p.setSpacingAfter(padding);
				p.setSpacingBefore(padding);
				p.setIndentationRight(padding*8);
				document.add(p);
				p = new Paragraph(p3,secFont);
				p.setAlignment(Paragraph.ALIGN_RIGHT);
				p.setSpacingAfter(padding);
				p.setSpacingBefore(padding);
				p.setIndentationRight(padding*8);
				document.add(p);
				p = new Paragraph("———————————————————————————————————————————");
				p.setAlignment(Paragraph.ALIGN_CENTER);
				document.add(p);
				
				
				p = new Paragraph(b0,titleFont);
				p.setAlignment(Paragraph.ALIGN_CENTER);
				p.setSpacingAfter(padding*4);
				p.setSpacingBefore(padding*2);
				document.add(p);
				p = new Paragraph(b1,secFont);
				p.setAlignment(Paragraph.ALIGN_LEFT);
				p.setSpacingAfter(padding);
				p.setSpacingBefore(padding);
				p.setIndentationLeft(padding*2);
				document.add(p);
				p = new Paragraph(b2,font);
				p.setAlignment(Paragraph.ALIGN_LEFT);
				p.setIndentationLeft(padding*7);
				p.setSpacingAfter(padding);
				p.setSpacingBefore(padding);
				document.add(p);
				
				PdfPTable table = new PdfPTable(2);
				
				for(Subject s :subs) {
					p = new Paragraph(s.kcdm + " " + s.kcmc,font);
					p.setAlignment(Paragraph.ALIGN_CENTER);
					
					PdfPCell cell = new PdfPCell(p);
					cell.setPadding(padding);
					cell.disableBorderSide(15);
					table.addCell(cell);
				}
				if(subs.size()%2 !=0) {
					p = new Paragraph("",font);
					PdfPCell cell = new PdfPCell(p);
					cell.setPadding(padding);
					cell.disableBorderSide(15);
					table.addCell(cell);
				}
				table.setSpacingAfter(padding*8);
				table.setSpacingBefore(padding*2);
				document.add(table);
				
				p = new Paragraph(map.get(k),secFont);
				p.setAlignment(Paragraph.ALIGN_RIGHT);
				p.setSpacingAfter(padding);
				p.setSpacingBefore(padding);
				p.setIndentationLeft(padding*8);
				p.setIndentationRight(padding*8);
				document.add(p);
				p = new Paragraph("年    月	    日（盖章）",secFont);
				p.setAlignment(Paragraph.ALIGN_RIGHT);
				p.setSpacingAfter(padding);
				p.setSpacingBefore(padding);
				p.setIndentationLeft(padding*8);
				p.setIndentationRight(padding*8);
				document.add(p);
				
				document.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	private void simple() throws DocumentException, IOException
    {
		Document document = new Document(PageSize.A4); 
		//②建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
		PdfWriter.getInstance(document, new FileOutputStream("E://Helloworld.PDF")); 
		//③打开文档。
		document.open(); 
		 //方法一：使用Windows系统字体(TrueType)    
        BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/msyh.ttc,0",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED); 
		//BaseFont baseFont = BaseFont.createFont("STSong-Light",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);  
		//④向文档中添加内容。
		Font font = new Font(baseFont);    
		Paragraph p = new Paragraph("河南00182 公共关系学 C 统一安排, 00644 公关礼仪 B 统一安排, 00900 网页设计与制作 C 统一安排, 00911 互联网数据库 A 统一安排, 02389 建筑材料 D 统一安排, 02398 土力学及地基基础 D 统一安排, 02404 工程地质及土力学 C 统一安排, 02442 钢结构 D 统一安排, 02677 田间试验与统计方法 C 统一安排, 03006 护理管理学 A 统一安排, 03200 预防医学(二) B 统一安排, 03201 护理学导论 C 统一安排, 03291 人际关系学 A 统一安排, 03700 护理社会学概论 A 统一安排, 05680 婚姻家庭法 C 统一安排, 05767 食品加工与保藏(本) A 统一安排], ",font);
		p.setAlignment(Paragraph.ALIGN_LEFT);
		
		document.add(p); 
		document.close();
    }
	
	

	

}
