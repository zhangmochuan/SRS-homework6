package srs.model;

import java.util.HashMap;

/**
 * 显示某个学期的所有课程安排
 * @author lzm
 *
 */
public class ScheduleOfClasses {

	private String semester;
	private HashMap<String, Section> sectionsOffered; 

	public ScheduleOfClasses(String semester) {
		setSemester(semester);

		sectionsOffered = new HashMap<String, Section>();
	}

	public void addSection(Section s) {
		String key = s.getRepresentedCourse().getCourseNo() + 
			     " - " + s.getSectionNo();
		sectionsOffered.put(key, s);

		s.setOfferedIn(this);
	}

	public Section findSection(String fullSectionNo) {
		return sectionsOffered.get(fullSectionNo);
	}

	public boolean isEmpty() {
		if (sectionsOffered.size() == 0) return true;
		else return false;
	}
	
	//---- get set 方法 ----
	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getSemester() {
		return semester;
	}

	public HashMap<String, Section> getSectionsOffered() {
		return sectionsOffered;
	}

}
