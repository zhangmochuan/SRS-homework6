package srs.service;

import srs.dao.UserDao;
import srs.daoImp.UserDaoImp;
import srs.model.Person;
import srs.model.User;

public class UserService {
	
	private UserDao dao = new UserDaoImp();
	
	public User login(String username,String password) {
		return dao.login(username, password);
	}
	public Person getPerson(String ssn, int type) {
		return dao.getPerson(ssn, type);
	}
	public int addCourseToPerson(String ssn, String sectionno) {
		return dao.addCourseToPerson(ssn, sectionno);
	}
}
