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

import javabackend.dao.EventDao;
import javabackend.model.Error;
import javabackend.model.Event;

import javabackend.model.User;

@RestController
public class EventController {

	@Autowired
	private EventDao eventDao;

	@RequestMapping(value = "/postEvent", method = RequestMethod.POST)
	public ResponseEntity<?> postEvent(@RequestBody Event event, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			Error error = new Error(1, "Unauthorized user.. login using valid credentials");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);// 401
		} else {
			eventDao.postEvent(event);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/getAllEvents", method = RequestMethod.GET)
	public ResponseEntity<?> getAllEvents(HttpSession session) {
		List<Event> events = eventDao.getAllEvents();
		return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
	}

	@RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
	public ResponseEntity<Event> getEventById(@PathVariable(value = "id") int id) {
		Event event = eventDao.getEventById(id);
		if (event == null) {
			System.out.println("event is null..........................");
			return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
		}
		System.out.println("returning event object..........................");
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}

	@RequestMapping(value = "/event/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEvent(@PathVariable int id, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			Error error = new Error(1, "Unauthorized user.. login using valid credentials");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);// 401
		} else if (user.getRole().equalsIgnoreCase("ADMIN"))
		/* else if (user.getRole().equalsIgnoreCase("Admin")) */ {
			Event event = eventDao.getEventById(id);
			if (event == null)
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			eventDao.deleteEvent(id);
			return new ResponseEntity<Void>(HttpStatus.OK);

		} else {
			Error error = new Error(2, "Unauthorized user..");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);// 401
		}
	}

}
