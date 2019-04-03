
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		String dir = "";
		if(args.length != 0) {
			dir = args[0];
		}
		System.out.println("读取对照表数据...");
		//加载对照表
		Table tb = new Table("对照表");
		Map<String,String> tm = tb.getMap();
		System.out.println("读取对照表数据成功！");
		System.out.println(tm);
		String bg_color = null;
		for (Map.Entry<String,String> item:tm.entrySet()
			 ) {
			String i = item.getKey();
			if(i.contains(".")==true){
				i = i.substring(0, i.indexOf("."));
			}

			int tag_id = Integer.parseInt("1"+i);
			int tag_lv = 0;
			int father_id = 0;
			String tag_name = item.getValue();

			if(i.length()==2){
				tag_lv = 1;
				father_id = 1;
				bg_color = "#"+randomHexString(6);
				System.out.println(father_id);
			}else if (i.length()==4){
				tag_lv = 2;
				father_id = Integer.parseInt(i.substring(0,3));
				System.out.println(father_id);
			}else if (i.length()==6){
				tag_lv = 3;
				father_id = Integer.parseInt(i.substring(0,5));
				System.out.println(father_id);
			}else {
				System.out.println("error:"+item.getKey());
			}
			JDBC jdbc = new JDBC();
			Connection connection = jdbc.getCon();
			try {
				String sql = "INSERT INTO tb_tag (tag_id, tag_lv,father_id,tag_name,bg_color) VALUES ( "+tag_id+","+tag_lv+","+father_id+",'"+tag_name+"','"+bg_color+"')";
				Statement statement = connection.createStatement();
				statement.execute(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	public static String randomHexString(int len)  {
		try {
			StringBuffer result = new StringBuffer();
			for(int i=0;i<len;i++) {
				result.append(Integer.toHexString(new Random().nextInt(16)));
			}
			return result.toString().toUpperCase();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
		return null;

	}

}