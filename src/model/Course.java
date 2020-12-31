package model;
/*
 * 课程信息实体
 */
public class Course {
	private int id;
	private String name;
	private int teacher_id;
	private int max_student_num;
	private String info;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}
	public int getMax_student_num() {
		return max_student_num;
	}
	public void setMax_student_num(int max_student_num) {
		this.max_student_num = max_student_num;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String toString(){
		return this.name;
	}
}
