package csjobs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import csjobs.model.User;
import csjobs.model.dao.UserDao;
import csjobs.util.checker;

@Service("userService")
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	
	private User user;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		try {
			user = userDao.getUserByEmailid(username);
			checker.error = "";
		} catch(Exception e) {
			System.out.println("The exception is: " + e);
		}
		if (user != null) {
			System.out.println("The Spring security returned user id: " + user.getUsername());
			return user;
		} else {
			System.out.println("Came to exception");
			checker.error = "Invalid username or password, please try again.";
			throw new UsernameNotFoundException( username + " is not found." );
		}
	}

}
