package javabackend.dao;

import java.util.List;

import javabackend.model.NewsBulletin;

public interface NewsBulletinDao {

	public void createNewsBulletin(NewsBulletin news);

	public NewsBulletin getNewsBulletinById(int bulletinid);

	public List<NewsBulletin> getAllNewsBulletins();

	public NewsBulletin updateNewsBulletin(int bulletinid, NewsBulletin news);

	public void deleteNewsBulletin(int bulletinid);

	//public List<NewsBulletin> getNewsBulletinByStatus(String status);

}
