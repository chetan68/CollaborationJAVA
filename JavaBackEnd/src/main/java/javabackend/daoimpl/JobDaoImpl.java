package javabackend.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javabackend.dao.JobDao;
import javabackend.model.Job;

@Repository
public class JobDaoImpl implements JobDao {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Job> getJobByExpirydate(String expirydate) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Job where hasexpired ='" + expirydate + "'");
		List<Job> jobs = query.list();
		session.close();
		return jobs;
	}

	public List<Job> getAllJobs() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Job where status='A'  order by jobid desc");
		// Query query = session.createQuery("from order by jobid desc");
		List<Job> jobs = query.list();
		session.close();
		return jobs;
	}

	public List<Job> getJobByStatus() {
		Session session = sessionFactory.openSession();
		// Query query = session.createQuery("from Job where status ='" + status
		// + "' order by jobid desc");
		Query query = session.createQuery("from Job order by jobid desc");
		List<Job> jobs = query.list();
		session.close();
		return jobs;
	}

	public Job getJobById(int id) {
		Session session = sessionFactory.openSession();
		// select * from personinfo where id=2
		Job job = (Job) session.get(Job.class, id);
		session.close();
		return job;

	}

	public Job updateJob(int jobid, Job job) {
		Session session = sessionFactory.openSession();
		System.out.println("Id of Job is to update is: " + job.getJobid());
		if (session.get(Job.class, jobid) == null)
			return null;
		session.merge(job); // update query where personid=?
		// select [after modification]
		Job updatedJob = (Job) session.get(Job.class, jobid); // select query
		session.flush();
		session.close();
		return updatedJob;

	}

	public void deleteJob(int jobid) {
		Session session = sessionFactory.openSession();
		// make the object persistent - job
		Job job = (Job) session.get(Job.class, jobid);
		session.delete(job);
		// Transient - job
		session.flush();
		session.close();
	}

	public void postJob(Job job) {
		Session session = sessionFactory.openSession();
		session.save(job);
		session.flush();
		session.close();
	}

	/*
	 * @Override public List<String> getDistinctJobStatus() { Session session =
	 * sessionFactory.openSession(); Query query = session.
	 * createQuery("select distinct status as status from Job order by status");
	 * List<String> statuslist = query.list(); session.close(); return
	 * statuslist; }
	 */

}
