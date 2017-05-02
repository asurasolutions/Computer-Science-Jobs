package csjobs.model.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import csjobs.model.User;
import csjobs.model.dao.UserDao;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	private List<User> users, reviewers;
	
	@Override
	public void registerUser(User user) {
		entityManager.merge(user);
	}

	@Override
	public boolean doesEmailidExists(String emailid) {
		@SuppressWarnings("unchecked")
		List<User> users = entityManager.createQuery("from User").getResultList();
		
		int flag = 0;
		emailid = emailid.toLowerCase();
		
		if (users.size() == 0)
			return false;
		else {
			
			for (User user : users) {
				String email = user.getUsername().toLowerCase();
				if (email.equals(emailid)) {
					flag = 1;
					break;
				}
			}
			
			if (flag == 1)
				return true;
			else
				return false;
		}
	}

	@Override
	public User login(String emailid, String password) {
		List<User> users = entityManager.createQuery("from User where email = :email and password = :password", User.class).setParameter("email", emailid).setParameter("password", password).getResultList();
		
		if (users.size() == 0)
			return null;
		else
			return users.get(0);
	}

	@Override
	public User getUser(Long id) {
		return entityManager.createQuery("from User where id = :id", User.class).setParameter("id", id).getSingleResult();
	}
	
	@Override
	public List<User> getReviewers() {
		 users = entityManager.createQuery("from User", User.class ).getResultList();
		 
		 reviewers = new ArrayList<User>();
		 for(User user: users) {
			 if (user.getRoles().contains("ROLE_REVIEWER"))
				 reviewers.add(user);
		 }
		 
		 return reviewers;
	}

	@Override
	public User getUserByEmailid(String username) {
		return entityManager.createQuery("from User where username = :username", User.class).setParameter("username", username).getSingleResult();
	}

}
