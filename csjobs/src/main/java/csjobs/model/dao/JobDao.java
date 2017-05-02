package csjobs.model.dao;

import java.util.Date;
import java.util.List;

import csjobs.model.Job;

public interface JobDao {
	
	List<Job> getAllOpenJobs(Date currentDate); 
	Job getJobById(Long id);
    List<Job> getOpenJobs();
    Job saveJob( Job job );

}
