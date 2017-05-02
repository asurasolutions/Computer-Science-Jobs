package csjobs.model.dao.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import csjobs.model.Job;
import csjobs.model.dao.JobDao;

@Repository
@Transactional
public class JobDaoImpl implements JobDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> getAllOpenJobs(Date currentDate) {
		return entityManager.createQuery("from Job where closeDate is null or close_date > :currentdate").setParameter("currentdate", currentDate).getResultList();
	}

	@Override
	public Job getJobById(Long id) {
		return (Job) entityManager.createQuery("from Job where id = :id").setParameter("id", id).getSingleResult();
	}
	
    @Override
    public List<Job> getOpenJobs() {
        String query = "from Job where publishDate < :now "
            + "and (closeDate is null or closeDate > :now) "
            + "order by publishDate asc";

        return entityManager.createQuery( query, Job.class )
            .setParameter( "now", new Date() )
            .getResultList();
    }

    @Override
    @Transactional
    public Job saveJob( Job job ) {
        return entityManager.merge( job );
    }

}
