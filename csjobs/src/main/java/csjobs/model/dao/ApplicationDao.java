package csjobs.model.dao;

import java.util.List;

import csjobs.model.Application;

public interface ApplicationDao {

	List<Application> getApplications(Long user_id);
	Application addApplication(Application application);
	Application getApplication(Long user_id, Long job_id);
}
