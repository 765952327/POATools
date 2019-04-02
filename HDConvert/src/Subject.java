import java.util.ArrayList;

public class Subject {
	
	public String kcdm;//课程代码
	public String kcmc;//课程名称
	public String ksdy;//考试单元
	public String kslx;//考试类型
	
	private ArrayList<String> list = new ArrayList<>();
	
	
	/**
	 * 添加城市
	 * @param name
	 * @return
	 */
	public Subject append(String name) {
		list.add(name);
		return this;
	}
	
	public ArrayList<String> getList() {
		return list;
	}
	
	@Override
	public String toString() {
		return kcdm + " " + kcmc + " " + ksdy + " " + kslx;
	}
}
