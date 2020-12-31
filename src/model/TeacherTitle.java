package model;
/*
 * 枚举类
 * 教师职称
 */
public enum TeacherTitle {
	PROFESSOR("教授",0),PROFESSOR2("副教授",1),LECTURER("讲师",2),DEAN("院长",3),DEAN2("副院长",4);
	private String titleName;
	private int index;
	private TeacherTitle(String titleName, int index){
		this.titleName = titleName;
		this.index = index;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.titleName;
	}
}
