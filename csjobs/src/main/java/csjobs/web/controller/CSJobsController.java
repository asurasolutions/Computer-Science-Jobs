package csjobs.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import csjobs.model.Application;
import csjobs.model.Degree;
import csjobs.model.Job;
import csjobs.model.User;
import csjobs.model.dao.ApplicationDao;
import csjobs.model.dao.FileDao;
import csjobs.model.dao.JobDao;
import csjobs.model.dao.UserDao;
import csjobs.util.checker;

@Controller
public class CSJobsController {

	@Autowired
	private ServletContext context;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JobDao jobDao;
	
	@Autowired
	private ApplicationDao applicationDao;
	
	@Autowired
	private FileDao fileDao;
	
	private User user;
	private String firstname, lastname, emailid, password, password2, phone, address;
	
	private List<Job> openJobs;
	private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");
	
	private List<Application> applications;
	private List<Job> eligibleJobs;

	private String currentJobInstitution, currentJobTitle, currentJobYear;
	private Job job;
	
	private String degreeName, universityName;
	private int completionYear;
	private List<Degree> degrees;
	private Degree degree;
	
	private Application application;
	private Long user_id, job_id;
	
	private Set<String> roles;
	
	private List<User> reviewers;
	
    private String jobTitle, jobDescription;
    private Date publishDate, closeDate;
    private SimpleDateFormat dateFormate = new SimpleDateFormat("M/d/yyyy");
    private String chairCommitteeId;
    private List<User> reviewCommittee;
    
    private csjobs.model.File cvfile, tsfile, rsfile;
    private String filename; 
    
	@RequestMapping(value = "/home.html", method = RequestMethod.GET)
	protected ModelAndView showHomePage(ModelMap models, @RequestParam Map<String, String> reqPar, HttpSession session) {
		
		System.out.println("Navigate the user to home page !");
		ModelAndView modelAndView = new ModelAndView("home");
		
		System.out.println("Today's date is: " + dateformat.format(new Date())); 
		openJobs = jobDao.getAllOpenJobs(new Date());
		session.setAttribute("openJobsList", openJobs);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/getJobDetails.html", method = RequestMethod.GET)
	protected ModelAndView showJobDetails(ModelMap models, @RequestParam Map<String, String> reqPar, HttpSession session) {
		
		System.out.println("Display Job Description !");
		ModelAndView modelAndView = new ModelAndView("jobdetails");
		
		System.out.println("The user selected: " + reqPar.get("jobtitle"));
		System.out.println("The user selected job with id: " + reqPar.get("id"));
		
		Long id = Long.parseLong( reqPar.get("id") );
		session.setAttribute("selectedJob", jobDao.getJobById(id));
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/applyForJob.html", method = RequestMethod.GET)
	protected ModelAndView applyForJob(ModelMap models, @RequestParam Map<String, String> reqPar, HttpSession session) {
		
		System.out.println("Apply for selected job !");
		ModelAndView modelAndView = new ModelAndView("jobapplication");
		
		System.out.println("The user selected: " + reqPar.get("title"));
		System.out.println("The user selected job with id: " + reqPar.get("id"));
		
		Long id = Long.parseLong( reqPar.get("id") );
		session.setAttribute("applyForJob", jobDao.getJobById(id));
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/viewApplication.html", method = RequestMethod.GET)
	protected ModelAndView viewApplication(ModelMap models, @RequestParam Map<String, String> reqPar, HttpSession session) {
		
		System.out.println("View job application !");
		ModelAndView modelAndView = new ModelAndView("viewjobapplication");
		
		System.out.println("The user id is: " + reqPar.get("user_id"));
		System.out.println("The job id is: " + reqPar.get("job_id"));
		System.out.println("The job title is: " + reqPar.get("job_title"));
		
		String uid = reqPar.get("user_id");
		String jid = reqPar.get("job_id");
		String job_title = reqPar.get("job_title");
		
		user_id = Long.parseLong(uid);
		job_id = Long.parseLong(jid);
		
		application = applicationDao.getApplication(user_id, job_id);
		
		degrees = application.getDegrees();
		
		System.out.println("The application contains the below files");
		
		session.setAttribute("application", application);
		session.setAttribute("job_title", job_title);
		session.setAttribute("application_degrees", degrees);
		return modelAndView;
	}
	
	@RequestMapping(value = "/download.html")
	protected String download(HttpServletResponse response, @RequestParam Map<String, String> reqPar) {
		
		String filename = reqPar.get("filename");
		System.out.println("The filename is: " + filename);
		
		response.setContentType("text/plain");
		response.setHeader("Content-Disposition", "attachment; filename=" + filename);
		
		try {
			FileInputStream in = new FileInputStream(new File(getFileDirectory(), filename));
			OutputStream out = response.getOutputStream();
			
			byte buffer[] = new byte[2048];
			int bytesRead;
			while ( (bytesRead = in.read(buffer)) > 0) {
				out.write(buffer, 0, bytesRead);
			}
			
			in.close();
		} catch(IOException e) {
			System.out.println("The IOException is: " + e);
		}
		
		return null;
	}
	
	@RequestMapping(value = "/register.html", method = RequestMethod.GET)
	protected ModelAndView showRegistrationPage(ModelMap models, @RequestParam Map<String, String> reqPar, HttpSession session) {
		
		System.out.println("Navigate the user to registration page !");
		ModelAndView modelAndView = new ModelAndView("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	protected ModelAndView loginPage(ModelMap models, @RequestParam Map<String, String> reqPar, HttpSession session) {
		
		System.out.println("Navigate the user to login page !");
		ModelAndView modelAndView = new ModelAndView("login");
		
		if (!checker.error.equals(""))
			session.setAttribute("loginerror", checker.error);
		else
			session.removeAttribute("loginerror");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/login.html", method = RequestMethod.POST)
	protected ModelAndView userLogin(ModelMap models, @RequestParam Map<String, String> reqPar, HttpSession session) {
		
		System.out.println("Perform user login !");
		
		emailid = reqPar.get("emailid");
		password = reqPar.get("password");
		
		user = null;
		user = userDao.login(emailid, password);
		ModelAndView modelAndView = null;
		
		if (user != null) {
			session.setAttribute("activeUser", user);
			
			roles = user.getRoles();
			
			for (String role : roles) {
				if (role.equals("ROLE_APPLICANT")) {
					modelAndView = new ModelAndView("applicant");
					
//					applications = user.getApplications();
					applications = applicationDao.getApplications(user.getId());
					
					openJobs = jobDao.getAllOpenJobs(new Date());
					System.out.println("The number of open jobs are: " + openJobs.size());
					
					int flag = 0;
					eligibleJobs = new ArrayList<Job>();
					
					for (Job job : openJobs) {
						flag = 0;
						for (Application application : applications) {
							if (job.getId() == application.getJob().getId()) {
								flag = 1;
								break;
							}
						}
						
						if (flag == 0)
							eligibleJobs.add(job);
					}
					
					session.setAttribute("eligibleJobs", eligibleJobs);
					session.setAttribute("userApplications", applications);
					
				} else if (role.equals("ROLE_ADMIN")) {
					modelAndView = new ModelAndView("admin");
					
					System.out.println("The open jobs are: " + jobDao.getOpenJobs());
					session.setAttribute("openJobsList", jobDao.getOpenJobs());
					
				} else if (role.equals("ROLE_REVIEWER")) {
					modelAndView = new ModelAndView("reviewer");
				}
			}
			
			System.out.println("The user: " + user.getFirstName() + " " + user.getLastName() + " successfully logged in");
			
		} else {
			modelAndView = new ModelAndView("login");
			modelAndView.addObject("loginerror", "Invalid email or password !");
		}
		return modelAndView;
	}
	
    private File getFileDirectory() {
    	
    	String path = context.getRealPath("/WEB-INF/files");
    	return new File(path);
    }
	
	@RequestMapping(value = "/addJobDetails.html", method = RequestMethod.POST)
	protected ModelAndView addJobDetails(ModelMap models, @RequestParam Map<String, String> reqPar, HttpSession session, @RequestParam("CVfile") MultipartFile file1, @RequestParam("TSfile") MultipartFile file2, @RequestParam("RSfile") MultipartFile file3) {
		
		System.out.println("Enter the user's current job details!");
		ModelAndView modelAndView;
		
		currentJobInstitution =reqPar.get("currentJobInstitution"); 
		currentJobTitle = reqPar.get("currentJobTitle"); 
		currentJobYear = reqPar.get("currentJobYear");
		job = (Job) session.getAttribute("applyForJob");
		user = (User) session.getAttribute("activeUser");
		
		if (!currentJobInstitution.equals("") && !currentJobTitle.equals("") && !currentJobYear.equals("")) {

			System.out.println("The job id is: " + job.getId());
			System.out.println("The current job institution is: " + currentJobInstitution);
			System.out.println("The current job title is: " + currentJobTitle);
			System.out.println("The current job year is: " + currentJobYear);
			
			try {

				filename = file1.getOriginalFilename();
				if (filename != null && !filename.equals("")) {
					System.out.println("The original CV filename is: " + filename);
					
					String arr[] = filename.split("\\.");
					for (String s : arr)
						System.out.println("The array item is: " + s);
	
					filename = arr[0] + "_" + user.getId() + "_" +job.getId() + "."+ arr[1];
					System.out.println("The new CV filename is: " + filename);
					
					file1.transferTo(new File(getFileDirectory(), filename));
					
					cvfile = new csjobs.model.File();
					cvfile.setDate(new Date());
					cvfile.setName(filename);
					cvfile.setSize(file1.getSize());
					cvfile.setType(file1.getContentType());
					cvfile.setOwner(user);
					
					cvfile = fileDao.saveFile(cvfile);
					session.setAttribute("cvfileid", cvfile.getId());
				} else {
					System.out.println("The CV file is not selected for upload !");
					session.removeAttribute("cvfileid");
				}
				
				filename = file2.getOriginalFilename();
				if (filename != null && !filename.equals("")) {
					
					System.out.println("The original TS filename is: " + filename);
					
					String arr[] = filename.split("\\.");
					for (String s : arr)
						System.out.println("The array item is: " + s);
	
					filename = arr[0] + "_" + user.getId() + "_" +job.getId() + "."+ arr[1];
					System.out.println("The new TS filename is: " + filename);
					
					file2.transferTo(new File(getFileDirectory(), filename));
					
					tsfile = new csjobs.model.File();
					tsfile.setDate(new Date());
					tsfile.setName(filename);
					tsfile.setSize(file2.getSize());
					tsfile.setType(file2.getContentType());
					tsfile.setOwner(user);
					
					tsfile = fileDao.saveFile(tsfile);
					session.setAttribute("tsfileid", tsfile.getId());
				} else {
					System.out.println("The TS file is not selected for upload !");
					session.removeAttribute("tsfileid");
				}
				
				filename = file3.getOriginalFilename();
				if (filename != null && !filename.equals("")) {
					
					System.out.println("The original RS filename is: " + filename);
					
					String arr[] = filename.split("\\.");
					for (String s : arr)
						System.out.println("The array item is: " + s);
	
					filename = arr[0] + "_" + user.getId() + "_" +job.getId() + "."+ arr[1];
					System.out.println("The new RS filename is: " + filename);
					
					file3.transferTo(new File(getFileDirectory(), filename));
					
					rsfile = new csjobs.model.File();
					rsfile.setDate(new Date());
					rsfile.setName(filename);
					rsfile.setSize(file3.getSize());
					rsfile.setType(file3.getContentType());
					rsfile.setOwner(user);
					
					rsfile = fileDao.saveFile(rsfile);
					session.setAttribute("rsfileid", rsfile.getId());
				} else {
					System.out.println("The RS file is not selected for upload !");
					session.removeAttribute("rsfileid");
				}
				
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			
			session.setAttribute("currentJobInstitution", currentJobInstitution);
			session.setAttribute("currentJobTitle", currentJobTitle);
			session.setAttribute("currentJobYear", currentJobYear);
			session.setAttribute("jobToApply", job);
			modelAndView = new ModelAndView("adddegree");
		} else {
			System.out.println("All the fields are mandatory !");
			modelAndView = new ModelAndView("jobapplication");
			modelAndView.addObject("allinputerror", "Please enter all the fields for registration !");
		}

		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addDegree.html", method = RequestMethod.POST)
	protected ModelAndView addDegree(ModelMap models, @RequestParam Map<String, String> reqPar, HttpSession session) {
		
		System.out.println("Add the user's degree's to the job application !");
		ModelAndView modelAndView = new ModelAndView("adddegree");
		
		degreeName = reqPar.get("degreename");
		universityName = reqPar.get("universityname");
		String year = reqPar.get("completionyear");
		
		if (degreeName != null && universityName != null && year != null) {
			
			if (!degreeName.equals("") && !universityName.equals("") && !year.equals("")) {
				
				completionYear = Integer.parseInt(year);
				System.out.println("The degree name is: " + degreeName);
				System.out.println("The university name is: " + universityName);
				System.out.println("The completion year is: " + completionYear);
				
				degrees =  (List<Degree>) session.getAttribute("degrees");
				if (degrees == null)
					degrees = new ArrayList<Degree>();
				
				degree = new Degree();
				degree.setName(degreeName);
				degree.setSchool(universityName);
				degree.setYear(completionYear);
				
				degrees.add(degree);
				
				session.setAttribute("degrees", degrees);
				System.out.println("The degree added successfully !");
				
			} else {
				System.out.println("All the fields in the degree are mandatory !");
				modelAndView.addObject("allinputerror", "Please enter all the fields for registration !");
			}
		} else {
			System.out.println("All the fields in the degree are mandatory !");
			modelAndView.addObject("allinputerror", "Please enter all the fields for registration !");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/prepareJobApplication.html", method = RequestMethod.POST)
	protected ModelAndView prepareJobApplication(ModelMap models, @RequestParam Map<String, String> reqPar, HttpSession session) {
		
		System.out.println("Prepare job application !");
		
		ModelAndView modelAndView = new ModelAndView("submitapplication");
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/submitJobApplication.html", method = RequestMethod.POST)
	protected ModelAndView submitJobApplication(ModelMap models, @RequestParam Map<String, String> reqPar, HttpSession session) {
		
		System.out.println("Save the job application !");
		application = new Application();
		
		job = (Job) session.getAttribute("jobToApply");
		user = (User) session.getAttribute("activeUser");
		currentJobInstitution = (String) session.getAttribute("currentJobInstitution");
		currentJobTitle = (String) session.getAttribute("currentJobTitle");
		currentJobYear = (String) session.getAttribute("currentJobYear");
		
		degrees = (List<Degree>) session.getAttribute("degrees");
		
		Long cvFileId = (Long) session.getAttribute("cvfileid");
		Long tsFileId = (Long) session.getAttribute("tsfileid");
		Long rsFileId = (Long) session.getAttribute("rsfileid");
		
		if (cvFileId != null) {
			cvfile = fileDao.getFile(cvFileId);
			application.setCv(cvfile);
		}
		
		if (tsFileId != null) {
			tsfile = fileDao.getFile(tsFileId);
			application.setTeachingStatement(tsfile);
		}
		
		if (rsFileId != null) {
			rsfile = fileDao.getFile(rsFileId);
			application.setResearchStatement(rsfile);	
		}
		
		application.setJob(job);
		application.setApplicant(user);
		application.setSubmitDate(new Date());
		application.setCurrentJobInstitution(currentJobInstitution);
		application.setCurrentJobTitle(currentJobTitle);
		application.setCurrentJobYear(Integer.parseInt(currentJobYear));
		application.setDegrees(degrees);
		
		/*if (cvfile != null)
			application.setCv(cvfile);
		else 
			application.setCv(null);
		
		if (tsfile != null)
			application.setTeachingStatement(tsfile);
		else
			application.setTeachingStatement(null);
		
		if (rsfile != null)
			application.setResearchStatement(rsfile);
		else
			application.setResearchStatement(null); */
		
		applicationDao.addApplication(application);
		
		System.out.println("The user: " + user.getFirstName() + " " + user.getLastName() + " has successfully applied for the job !");
		
		user = userDao.getUser(user.getId());
		applications = user.getApplications();
		
		openJobs = jobDao.getAllOpenJobs(new Date());
		System.out.println("The number of open jobs are: " + openJobs.size());
		
		int flag = 0;
		eligibleJobs = new ArrayList<Job>();
		
		for (Job job : openJobs) {
			flag = 0;
			for (Application application : applications) {
				if (job.getId() == application.getJob().getId()) {
					flag = 1;
					break;
				}
			}
			
			if (flag == 0)
				eligibleJobs.add(job);
		}
		
		session.setAttribute("eligibleJobs", eligibleJobs);
		session.setAttribute("userApplications", applications);
		
		degrees =  (List<Degree>) session.getAttribute("degrees");
		degrees.clear();
		session.setAttribute("degrees", degrees);
		
		ModelAndView modelAndView = new ModelAndView("applicant");
		return modelAndView;
	}
	
	@RequestMapping(value = "/logout.html", method = RequestMethod.POST)
	protected ModelAndView userLogout(ModelMap models, @RequestParam Map<String, String> reqPar, HttpSession session) {
		
		System.out.println("Perform user logout !");
		
		session.removeAttribute("activeUser");
		
		ModelAndView modelAndView = new ModelAndView("home");
		return modelAndView;
	}
	
	@RequestMapping(value = "/viewJob.html", method = RequestMethod.GET)
	protected ModelAndView viewJob(ModelMap models, @RequestParam Map<String, String> reqPar, HttpSession session) {
		
		System.out.println("Admin view job !");
		
		String temp = reqPar.get("id");
		job_id = Long.parseLong(temp);
		models.put( "job", jobDao.getJobById(job_id));
		ModelAndView modelAndView = new ModelAndView("viewJob");
		return modelAndView;
	}
	
	@RequestMapping(value = "/editJob.html", method = RequestMethod.GET)
	protected ModelAndView editJob(ModelMap models, @RequestParam Map<String, String> reqPar, HttpSession session) {
		
		System.out.println("Admin editting a job !");
		
		String temp = reqPar.get("id");
		job_id = Long.parseLong(temp);
		models.put( "job", jobDao.getJobById(job_id));
		session.setAttribute("currentJobId", jobDao.getJobById(job_id).getId());
		
    	reviewers = userDao.getReviewers();
    	
    	for (User reviewer : reviewers) {
    		System.out.println("Reviewer name: " + reviewer.getFirstName());
    	}
    	
    	session.setAttribute("reviewers", reviewers);
		
		ModelAndView modelAndView = new ModelAndView("editJob");
		return modelAndView;
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/editJob.html", method = RequestMethod.POST)
	protected ModelAndView saveJob(ModelMap models, @RequestParam Map<String, String> reqPar, HttpSession session, HttpServletRequest request) {
		
		System.out.println("Saving the changes in job.");
		
		job_id = (Long) session.getAttribute("currentJobId");
		job = jobDao.getJobById(job_id);
		
		System.out.println("The job title is: " + job.getTitle());
		
	   	jobTitle = reqPar.get("jobTitle");
    	jobDescription = reqPar.get("jobDescription");
    	String publishDateStr =  reqPar.get("publishDate");
    	String closeDateStr =  reqPar.get("closeDate");
    	
    	if (publishDateStr != null) {
    	  if (!publishDateStr.equals("")) {
        	  publishDate = new Date(publishDateStr);
        	  job.setPublishDate(publishDate);
    	  } else
    		job.setPublishDate(null);
    	} else {
    	  job.setPublishDate(null);
    	}

    	if (closeDateStr != null) {
    	  if (!closeDateStr.equals("")) {
    		closeDate = new Date(closeDateStr);
    		job.setCloseDate(closeDate);
    	  } else
    		job.setCloseDate(null);
    	} else {
    	  job.setCloseDate(null);
    	}
    	
    	chairCommitteeId = reqPar.get("chairCommittee");
    	Long id = Long.parseLong(chairCommitteeId);
    	User chairCommitteeUser = userDao.getUser(id);
    	
    	String CommitteeMembers[] = request.getParameterValues("reviewer_group");  
		
    	job.setTitle(jobTitle);
    	job.setDescription(jobDescription);
    	
    	job.setCommitteeChair(chairCommitteeUser);
    	
//    	reviewCommittee = new ArrayList<User>();
    	reviewCommittee = job.getCommitteeMembers();
    	reviewCommittee.clear();
    	Long reviewerId = 0L;
    	
    	if (CommitteeMembers != null) {
    	  for(String member: CommitteeMembers) {
    		System.out.println(member);
    		reviewerId = Long.parseLong(member);
    		User reviewer = userDao.getUser(reviewerId);
    		reviewCommittee.add(reviewer);
    	  }
    	} 
    	
    	if (!reviewCommittee.contains(chairCommitteeUser))
  			reviewCommittee.add(chairCommitteeUser);
    	
    	job.setCommitteeMembers(reviewCommittee);
    	jobDao.saveJob(job);
    	
    	session.setAttribute("openJobsList", jobDao.getOpenJobs());
		ModelAndView modelAndView = new ModelAndView("admin");
		return modelAndView;
	}
	
    @RequestMapping(value = "/addJob.html", method = RequestMethod.GET)
    protected ModelAndView addJob(HttpSession session) {
    	reviewers = userDao.getReviewers();
    	
    	for (User reviewer : reviewers) {
    		System.out.println("Reviewer name: " + reviewer.getFirstName());
    	}
    	
    	session.setAttribute("reviewers", reviewers);
    	
		ModelAndView modelAndView = new ModelAndView("add");
		return modelAndView;
    }
    
    @SuppressWarnings("deprecation")
	@RequestMapping(value = "/addJob.html", method = RequestMethod.POST)
    public ModelAndView saveJob(HttpSession session, @RequestParam Map<String, String> reqPar, HttpServletRequest request) {
    	
    	System.out.println("Saving the new job.");
    	job = new Job();
    	
    	jobTitle = reqPar.get("jobTitle");
    	jobDescription = reqPar.get("jobDescription");
    	String publishDateStr =  reqPar.get("publishDate");
    	String closeDateStr =  reqPar.get("closeDate");
    	
    	publishDate = new Date(publishDateStr);
    	closeDate = new Date(closeDateStr);
    	
    	chairCommitteeId = reqPar.get("chairCommittee");
    	Long id = Long.parseLong(chairCommitteeId);
    	User chairCommitteeUser = userDao.getUser(id);
    	
    	String CommitteeMembers[] = request.getParameterValues("reviewer_group");  
    	
    	System.out.println("Title: " + jobTitle + " Description: " + jobDescription + " publish date: " + dateFormate.format(publishDate) + " close date: " + dateFormate.format(closeDate) + " chair committee id: " + chairCommitteeId);
    	
    	
    	job.setTitle(jobTitle);
    	job.setDescription(jobDescription);
    	job.setPublishDate(publishDate);
    	job.setCloseDate(closeDate);
    	job.setCommitteeChair(chairCommitteeUser);
    	
    	reviewCommittee = new ArrayList<User>();
    	Long reviewerId = 0L;
    	for(String member: CommitteeMembers) {
    		System.out.println(member);
    		reviewerId = Long.parseLong(member);
    		User reviewer = userDao.getUser(reviewerId);
    		reviewCommittee.add(reviewer);
    	}
    	
    	if (!reviewCommittee.contains(chairCommitteeUser))
    		reviewCommittee.add(chairCommitteeUser);
    	
    	job.setCommitteeMembers(reviewCommittee);
    	jobDao.saveJob(job);
    	
    	session.setAttribute("openJobsList", jobDao.getOpenJobs());
    	ModelAndView modelAndView = new ModelAndView("admin");
        return modelAndView;
    }
	
	@RequestMapping(value = "/register.html", method = RequestMethod.POST)
	protected ModelAndView registerUser(ModelMap models, @RequestParam Map<String, String> reqPar, HttpSession session) {
		
		System.out.println("Register new user account !");
		ModelAndView modelAndView = new ModelAndView("registration");
		
		firstname = reqPar.get("firstname");
		lastname = reqPar.get("lastname");
		emailid = reqPar.get("emailid");
		password = reqPar.get("password");
		password2 = reqPar.get("password2");
		phone = reqPar.get("phone");
		address = reqPar.get("address");
		
		System.out.println("The new user details are as below:"); 
		System.out.println("The firstname is: " + firstname);
		System.out.println("The lastname is: " + lastname);
		System.out.println("The email address is: " + emailid);
		System.out.println("The password is: " + password);
		System.out.println("The password2 is: " + password2);
		System.out.println("The phone number is: " + phone);
		System.out.println("The address is: " + address);
		
		user = new User();
		
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setUsername(emailid);
		user.setPassword(password);
		user.setPhone(phone);
		user.setAddress(address);
		user.setEnabled(true);
		
		Set<String> roles = new HashSet<String>();
		roles.add("ROLE_APPLICANT");
		
		user.setRoles(roles);
		
		boolean emailResult = false;
		
		if (!firstname.equals("") && !lastname.equals("") && !emailid.equals("") && !password.equals("") && !password2.equals("") && !phone.equals("") && !address.equals("")) {
			
			emailResult = userDao.doesEmailidExists(emailid); 
			if (emailResult == false) {
				
				if (password.equals(password2)) {
					userDao.registerUser(user);
					System.out.println("The new user: " + reqPar.get("firstname") + " " + reqPar.get("lastname") + " has been added in the database !");
					modelAndView.addObject("success", "The user: " + user.getFirstName() + " " + user.getLastName() + " has registered successfully !");
					
				} else {
					System.out.println("The password-1 and password-2 are not matching !");
					modelAndView.addObject("allinputerror", "The password-1 and password-2 are not matching !");
				}
				
			} else {
				System.out.println("A user with this email id: " + emailid + " already exists, please enter different email id !");
				modelAndView.addObject("allinputerror", "A user with this email id: " + emailid + " already exists, please enter different email id !");
			}
			
		} else {
			System.out.println("Please enter all the fields for registration !");
			modelAndView.addObject("allinputerror", "Please enter all the fields for registration !");
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/adminhome.html", method = RequestMethod.GET)
	protected ModelAndView takeToAdminHome(ModelMap models, @RequestParam Map<String, String> reqPar, HttpSession session) {
	
		System.out.println("Navigating the administrator to home page.");
		
		ModelAndView modelAndView = new ModelAndView("admin");
		
		System.out.println("The open jobs are: " + jobDao.getOpenJobs());
		session.setAttribute("openJobsList", jobDao.getOpenJobs());
		return modelAndView;
	}
	
	@RequestMapping(value = "/applicanthome.html", method = RequestMethod.GET)
	protected ModelAndView takeToApplicantHome(ModelMap models, @RequestParam Map<String, String> reqPar, HttpSession session) {
	
		System.out.println("Navigating the applicant to home page.");
		
		ModelAndView modelAndView = new ModelAndView("applicant");
		Long user_id = (Long) session.getAttribute("currentUserId");
		user = userDao.getUser(user_id);
		applications = applicationDao.getApplications(user.getId());
		
		openJobs = jobDao.getAllOpenJobs(new Date());
		System.out.println("The number of open jobs are: " + openJobs.size());
		
		int flag = 0;
		eligibleJobs = new ArrayList<Job>();
		
		for (Job job : openJobs) {
			flag = 0;
			for (Application application : applications) {
				if (job.getId() == application.getJob().getId()) {
					flag = 1;
					break;
				}
			}
			
			if (flag == 0)
				eligibleJobs.add(job);
		}
		
		session.setAttribute("eligibleJobs", eligibleJobs);
		session.setAttribute("userApplications", applications);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/reviewerhome.html", method = RequestMethod.GET)
	protected ModelAndView takeToReviewerHome(ModelMap models, @RequestParam Map<String, String> reqPar, HttpSession session) {
	
		System.out.println("Navigating the reviewer to home page.");
		
		ModelAndView modelAndView = new ModelAndView("reviewer");
		return modelAndView;
	}
}
