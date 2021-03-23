package com.hibernate.advancemapping.onetoone;

import org.hibernate.Session;
import org.junit.*;

import com.hibernate.conf.Factory;

public class InstructorTests {

	private static Session session;
	
	@Before
	public void openSession() {
		session = Factory.getSessionFactory().openSession();
	}
	
	@After
	public void closeSession() {
		session.close();
	}

	@Test
	public void insertRows(){

		try {
			session.beginTransaction();

			InstructorDetail detail = new InstructorDetail(1, "channel", "happy");

			Instructor instructor = new Instructor(1, "sepehr", "mollaei", "sepehrmsm1379@gmail.com");
			instructor.setDetail(detail);

			session.save(instructor);

			session.getTransaction().commit();
		}catch (Exception exception){
			exception.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	@Test
	public void testGetDetail(){

		try {
			session.beginTransaction();

			InstructorDetail detail = session.get(InstructorDetail.class, 1);

			Assert.assertNotNull(detail);

			Assert.assertNotNull(detail.getInstructor());

			Assert.assertEquals(detail.getInstructor().getFirstName(), "sepehr");

			session.getTransaction().commit();
		}catch (Exception exception){
			exception.printStackTrace();
			session.getTransaction().rollback();
		}

	}

	@Test
	public void deleteRows(){
		try {
			session.beginTransaction();

			Instructor instructor = session.get(Instructor.class, 1);
			InstructorDetail detail = session.get(InstructorDetail.class, 1);

			instructor.setDetail(null);

			session.delete(instructor);
			session.delete(detail);

			session.getTransaction().commit();
		}catch (Exception exception){
			exception.printStackTrace();
			session.getTransaction().rollback();
		}

	}

}
