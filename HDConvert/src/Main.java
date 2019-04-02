
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		String dir = "";
		if(args.length != 0) {
			dir = args[0];
		}
		System.out.println("读取对照表数据...");
		//加载对照表
		Table tb = new Table("对照表");
		HashMap<String,String> tm = tb.getMap();
		//System.out.println(tm);
		System.out.println("读取对照表数据成功！");
		//加载汇总数据
		System.out.println("读取汇总数据...");
		File fl = new File(dir + "汇总数据");
		String titleDate = "";
		for(File f : fl.listFiles()) {
			System.out.println("读取 " + f.getName() + "...");
			int k = f.getName().indexOf("月");
			if(k != -1) {
				titleDate = f.getName().substring(0,k + 1);
			}
			SubList total = new SubList(f.getAbsolutePath());
			HashMap<String,ArrayList<Subject>> list = total.getList();
			//System.out.println(total.getSubs());
			//生成pdf打印文件
			System.out.println("生成 " + f.getName() + "...");
			OutPDF.titleDate = titleDate;
			new OutPDF(dir + "生成数据/" +f.getName(),tm,list).out();
			new OutPDF(dir + "生成数据/" +f.getName(),tm,list).outAll();
			OutPDF.outSubs(dir + "生成数据/" +f.getName() + "-清单",total.getSubs());
		}
		
	}

}