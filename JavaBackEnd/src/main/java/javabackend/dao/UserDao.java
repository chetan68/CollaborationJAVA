package javabackend.dao;

import java.util.List;

import javabackend.model.User;

public interface UserDao {

	User authenticate(User user);

	List<User> getAllUsers();

	User registerUser(User user);

	User updateUser(int id, User user);

	void deleteUser(int id);

	User getUserById(int userid);

	public List<User> getAllUsers(User user);

}
