package srs.dao;

import srs.model.Person;
import srs.model.User;

public interface UserDao {

	public User login(String username,String password);

	public Person getPerson(String ssn,int type);

	public int addCourseToPerson(String ssn,String sectionno);
}
