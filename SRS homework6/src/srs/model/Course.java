package srs.model;

import java.util.ArrayList;
import java.util.Collection;
/**
 * 课程类，之间关联的类为section，不与student类直接关联
 * @author lzm
 *
 */
public class Course {

	private String courseNo;
	private String courseName;
	private double credits;
	private ArrayList<Section> offeredAsSection; 
	private ArrayList<Course> prerequisites; 

	public Course(String cNo, String cName, double credits) {
		setCourseNo(cNo);
		setCourseName(cName);
		setCredits(credits);

		offeredAsSection = new ArrayList<Section>();
		prerequisites = new ArrayList<Course>();
	}
	
	/**
	 * 添加先修课
	 * @param c 要成为先修课的课程
	 */
	public void addPrerequisite(Course c) {
		prerequisites.add(c);
	}
	
	/**
	 * 是否有先修课
	 * @return 布尔类型 
	 */
	public boolean hasPrerequisites() {
		if (prerequisites.size() > 0) return true;
		else return false;
	}
	
	/**
	 * 获取所有先修课
	 * @return 返回容器接口，可为所有容器接收
	 */
	public Collection<Course> getPrerequisites() {
		return prerequisites;
	}
	
	/**
	 * 类似课程表，罗列所有的班次信息
	 * @param day
	 * @param time
	 * @param room
	 * @param capacity
	 * @return
	 */
	public Section scheduleSection(String day, String time, String room,
				       int capacity) {

		Section s = new Section(offeredAsSection.size() + 1, 
				day, time, this, room, capacity);
		

		addSection(s);
		
		return s;
	}
	
	/**
	 * 某个课程增添一个班次
	 * @param s
	 */
	public void addSection(Section s) {
		offeredAsSection.add(s);
	}
	
	public void display() {
		System.out.println("Course Information:");
		System.out.println("\tCourse No.:  " + getCourseNo());
		System.out.println("\tCourse Name:  " + getCourseName());
		System.out.println("\tCredits:  " + getCredits());
		System.out.println("\tPrerequisite Courses:");

		for (Course c : prerequisites) {
			System.out.println("\t\t" + c.toString());
		}

		System.out.print("\tOffered As Section(s):  ");
		for (Section s : offeredAsSection) {
			System.out.print(s.getSectionNo() + " ");
		}

		System.out.println();
	}
	
	@Override
	public String toString() {
		return getCourseNo() + ":  " + getCourseName();
	}

	
	//---- get  set 方法  ----
	public void setCourseNo(String cNo) {
		courseNo = cNo;
	}
	
	public String getCourseNo() {
		return courseNo;
	}
	
	public void setCourseName(String cName) {
		courseName = cName;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCredits(double c) {
		credits = c;
	}
	
	public double getCredits() {
		return credits;
	}
		
}
