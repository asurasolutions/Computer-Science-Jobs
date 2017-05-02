package csjobs.model.dao;

import java.util.List;
import csjobs.model.User;

public interface UserDao {
	
	void registerUser(User user);
	boolean doesEmailidExists(String emailid);
	User login(String emailid, String password);
	User getUser(Long id);
	List<User> getReviewers();
	User getUserByEmailid(String emailid);
}
