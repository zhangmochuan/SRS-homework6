package srs.model;

import java.util.ArrayList;
import java.util.List;

import srs.service.PlanService;

/**
 * 学习计划类，学生第一次登陆时查看学习计划
 * @author lzm
 *
 */
public class PlanOfStudy {
	
	private Student studentOwner;
	private List<Course> courses;
	
	//展示所有已选
	public List<Course> diplay(){
		PlanService service = new PlanService();
		List<Course> list = new ArrayList<Course>();
		//数据库获取course列表
		list = service.getCourses(studentOwner.getSsn());
		//返回前台展示
		return list;
	}
	public PlanOfStudy() {
		courses = new ArrayList<Course>();
	}
	public Student getStudentOwner() {
		return studentOwner;
	}

	public void setStudentOwner(Student studentOwner) {
		this.studentOwner = studentOwner;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	
}
