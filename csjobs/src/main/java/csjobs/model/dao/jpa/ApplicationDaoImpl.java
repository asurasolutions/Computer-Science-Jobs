package csjobs.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import csjobs.model.Application;
import csjobs.model.dao.ApplicationDao;

@Repository
@Transactional
public class ApplicationDaoImpl implements ApplicationDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Application> getApplications(Long user_id) {
		return entityManager.createQuery("from Application where applicant_id = :id", Application.class).setParameter("id", user_id).getResultList();
	}

	@Override
	public Application addApplication(Application application) {
		return entityManager.merge(application);
	}

	@Override
	public Application getApplication(Long user_id, Long job_id) {
		return entityManager.createQuery("from Application where applicant_id = :id and job_id = :job_id", Application.class).setParameter("id", user_id).setParameter("job_id", job_id).getSingleResult();
	}

}
