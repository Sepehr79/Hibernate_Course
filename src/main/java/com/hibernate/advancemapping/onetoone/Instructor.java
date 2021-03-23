package com.hibernate.advancemapping.onetoone;

import javax.persistence.*;

@Entity
@Table
public class Instructor {
	
	@Id
	private int id;

	private String firstName;

	private String lastName;

	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "detail")
	private InstructorDetail instructorDetail;

	public Instructor(int id, String firstName, String lastName, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Instructor() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public InstructorDetail getDetail() {
		return instructorDetail;
	}

	public void setDetail(InstructorDetail detail) {
		this.instructorDetail = detail;
	}

	@Override
	public String toString() {
		return "Instructor{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", instructorDetail=" + instructorDetail +
				'}';
	}


}
