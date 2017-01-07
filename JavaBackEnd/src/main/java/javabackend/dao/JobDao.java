package javabackend.dao;

import java.util.List;

import javabackend.model.Job;

public interface JobDao {

	public void postJob(Job job);

	public Job getJobById(int jobid);

	public List<Job> getAllJobs();

	public Job updateJob(int jobid, Job job);

	public void deleteJob(int jobid);

	public List<Job> getJobByStatus();

	public List<Job> getJobByExpirydate(String expirydate);

	// List<String> getDistinctJobStatus();
}
