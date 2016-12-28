package javabackend.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javabackend.dao.JobDao;
import javabackend.model.Job;
import javabackend.model.User;
import javabackend.model.Error;

@RestController
public class JobController {

	@Autowired
	private JobDao jobDao;

	@RequestMapping(value = "/postJob", method = RequestMethod.POST)
	public ResponseEntity<?> postJob(@RequestBody Job job, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			Error error = new Error(1, "Unauthorized user.. login using valid credentials");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);// 401
		} else {
			jobDao.postJob(job);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/getAllJobs", method = RequestMethod.GET)
	public ResponseEntity<?> getAllJobs(HttpSession session) {
		List<Job> jobs = jobDao.getAllJobs();
		return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
	}

	@RequestMapping(value = "/job/{id}", method = RequestMethod.GET)
	public ResponseEntity<Job> getJobById(@PathVariable(value = "id") int id) {
		Job job = jobDao.getJobById(id);
		if (job == null) {
			System.out.println("job is null..........................");
			return new ResponseEntity<Job>(HttpStatus.NOT_FOUND);
		}
		System.out.println("returning job object..........................");
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}

	@RequestMapping(value = "/job/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateJob(@PathVariable int id, @RequestBody Job job, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			Error error = new Error(1, "Unauthorized user.. login using valid credentials");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);// 401
		} else if (user.getRole().equalsIgnoreCase("ADMIN")) {
			Job editjob = jobDao.getJobById(id);
			if (editjob == null)
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			Job updatedJob = jobDao.updateJob(id, job);
			return new ResponseEntity<Job>(updatedJob, HttpStatus.OK);
		} else {
			Error error = new Error(2, "Unauthorized user..");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);// 401
		}
	}

	@RequestMapping(value = "/job/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteJob(@PathVariable int id, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			Error error = new Error(1, "Unauthorized user.. login using valid credentials");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);// 401
		} else if (user.getRole().equalsIgnoreCase("ADMIN"))
		/* else if (user.getRole().equalsIgnoreCase("Admin")) */ {
			Job job = jobDao.getJobById(id);
			if (job == null)
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			jobDao.deleteJob(id);
			return new ResponseEntity<Void>(HttpStatus.OK);

		} else {
			Error error = new Error(2, "Unauthorized user..");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);// 401
		}
	}

	@RequestMapping(value = "/jobsta/{status}", method = RequestMethod.GET)
	public ResponseEntity<?> getJobByStatus(@PathVariable(value = "status") String status, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			Error error = new Error(1, "Unauthorized user.. login using valid credentials");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);// 401
		} else if (user.getRole().equalsIgnoreCase("ADMIN")) {
			List<Job> jobs = jobDao.getJobByStatus(status);
			return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
		} else {
			Error error = new Error(2, "Unauthorized user..");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);// 401
		}
	}

	/*@RequestMapping(value = "/jobex/{expired}", method = RequestMethod.GET)
	public ResponseEntity<?> getJobByExpirydate(@PathVariable(value = "expired") String expirydate,
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			Error error = new Error(1, "Unauthorized user.. login using valid credentials");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);// 401
		} else if (user.getRole().equalsIgnoreCase("Admin")) {
			List<Job> jobs = jobDao.getJobByExpirydate(expirydate);
			return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
		} else {
			Error error = new Error(2, "Unauthorized user..");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);// 401
		}
	}*/

}
