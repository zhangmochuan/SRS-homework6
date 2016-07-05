package srs.service;

import java.util.List;

import srs.dao.PlanDao;
import srs.daoImp.PlanDaoImp;
import srs.model.Course;

public class PlanService {
	private PlanDao dao = new PlanDaoImp();
	
	public List<Course> getCourses(String ssn){
		return dao.getCourses(ssn);
	}
	
}
