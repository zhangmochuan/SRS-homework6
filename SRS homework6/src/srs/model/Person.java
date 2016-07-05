package srs.model;
/**
 * Person实体类，抽象类，用于被Professor和Student来继承
 * @author lzm
 *
 */
public abstract class Person {

	private String name;
	private String ssn;

	public Person(String name, String ssn) {
		this.setName(name);
		this.setSsn(ssn);
	}

    public Person() {
    }

    public void setName(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public void setSsn(String s) {
		ssn = s;
	}
	
	public String getSsn() {
		return ssn;
	}
	
	@Override
	public abstract String toString(); 
	
	//暂时保留
	public void display() {
		System.out.println("Person Information:");
		System.out.println("\tName:  " + this.getName());
		System.out.println("\tSoc. Security No.:  " + this.getSsn());
	}
}	
