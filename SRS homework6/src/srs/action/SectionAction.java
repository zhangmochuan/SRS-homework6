package srs.action;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import srs.dao.SectionDao;
import srs.dao.TranscriptDao;
import srs.daoImp.SectionDaoImp;
import srs.daoImp.TranscriptDaoImp;
import srs.model.Course;
import srs.model.PlanOfStudy;
import srs.model.ScheduleOfClasses;
import srs.model.Section;
import srs.model.Student;
import srs.model.Transcript;
import srs.model.TranscriptEntry;
import srs.service.PlanService;
import srs.service.SectionService;
import srs.service.UserService;

public class SectionAction extends ActionSupport {
	private static final long serialVersionUID = 5283293241313468644L;
	
	private Student s;
	private JSONArray root;
	SectionService service = new SectionService();
	HttpServletRequest request = ServletActionContext.getRequest();
	UserService ser = new UserService();
	

	@Override
	public String execute() throws Exception {
		
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		List<Section> list = new ArrayList<Section>();
		Map<String,Object>  map = new HashMap<String,Object>();
		

		list = service.getSections();
		for (Section section : list) {
			SectionDao dao = new SectionDaoImp();
			ScheduleOfClasses soc = dao.getSchedule(section.getSectionNo());
			soc.addSection(section);
			section.setOfferedIn(soc);
			map = section.display();
			String ssn = request.getParameter("ssn");
			Student stu = (Student) ser.getPerson(ssn, 2);
			List<Integer> sList = getFromPerson(stu);
			for (Integer tempNo : sList) {
				if(section.getSectionNo()==tempNo){
					
					map.put("mark",1);
					break;
				}else{
				
					map.put("mark",0);
				}
			}
			mapList.add(map);
		}
		root = JSONArray.fromObject(mapList);
		return SUCCESS;
	}

	private List<Integer> getFromPerson(Student s){
		SectionDao dao = new SectionDaoImp();
		return dao.getHasSectionNo(s);
	}

	@SuppressWarnings("all")
	public String inSection() throws IOException {
		

		String ssn = request.getParameter("ssn");
		String sectionno = request.getParameter("sectionno");
		s = (Student) ser.getPerson(ssn, 2);
		

		PlanOfStudy plan = new PlanOfStudy();
		PlanService pService = new PlanService();
		List<Course> list = pService.getCourses(s.getSsn());
		plan.setStudentOwner(s);
		plan.setCourses(list);
	
		s.setPlan(plan);
		
	
		Transcript t = new Transcript(s);
		TranscriptDao dao = new TranscriptDaoImp();
		List<TranscriptEntry> tList = dao.getTranscript();
		for (TranscriptEntry transcriptEntry : tList) {
			if(transcriptEntry.getStudent().getSsn().equals(s.getSsn())) 	
				t.addTranscriptEntry(transcriptEntry);
		}
		Section section = service.getSectionByNo(Integer.parseInt(sectionno));
	
		String msg = section.enroll(s).value();
	 
		if(msg.equals("加入成功 ！  :o)")){
			try {
				Class clazz = Class.forName("com.srs.daoImp.UserDaoImp");
				Method m = clazz.getDeclaredMethod("addCourseToPerson",new Class[]{String.class,String.class});
				m.invoke(clazz.newInstance(),new String[]{ssn,sectionno});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		

		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print(msg);

		return null;
	}

	public Student getS() {
		return s;
	}

	public void setS(Student s) {
		this.s = s;
	}

	public JSONArray getRoot() {
		return root;
	}

	public void setRoot(JSONArray root) {
		this.root = root;
	}
	
}
