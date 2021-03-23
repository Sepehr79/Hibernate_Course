package com.hibernate.advancemapping.onetoone;

import javax.persistence.*;

@Entity
@Table(name = "instructor_detail")
public class InstructorDetail {

	@Id
	private int id;

	private String youtubeChannel;

	private String hobby;

	@OneToOne(mappedBy = "instructorDetail",
			cascade = CascadeType.ALL)
	private Instructor instructor;

	public InstructorDetail(int id, String youtubeChannel, String hobby) {
		this.id = id;
		this.youtubeChannel = youtubeChannel;
		this.hobby = hobby;
	}

	public InstructorDetail() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getYoutubeChannel() {
		return youtubeChannel;
	}

	public void setYoutubeChannel(String youtubeChannel) {
		this.youtubeChannel = youtubeChannel;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	@Override
	public String toString() {
		return "InstructorDetail{" +
				"id=" + id +
				", youtubeChannel='" + youtubeChannel + '\'' +
				", hobby='" + hobby + '\'' +
				", instructor=" + instructor +
				'}';
	}
}
