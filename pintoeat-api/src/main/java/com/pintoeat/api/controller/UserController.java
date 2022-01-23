package com.pintoeat.api.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pintoeat.api.model.Folder;
import com.pintoeat.api.model.Image;
import com.pintoeat.api.model.Pin;
import com.pintoeat.api.model.User;
import com.pintoeat.api.pojo.AddUpdateOutput;
import com.pintoeat.api.pojo.UserPojo;
import com.pintoeat.api.repository.UserRepository;
import com.pintoeat.api.utils.Utils;


@RestController
@RequestMapping("/api/pintoeat-user")
public class UserController {
	
	final static Logger logger = Logger.getLogger(UserController.class);
	final static Logger cdrLogger = Logger.getLogger(Utils.CDR_LOGGER);
	
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public List<User> getAll(HttpServletRequest request) {
		long start = System.currentTimeMillis();
		List<User> resultList = new ArrayList<User>();
		resultList = (List<User>) userRepo.findAll();
		
		cdrLogger.info(
				Utils.printCdrLog(request.getRemoteAddr(), Thread.currentThread().getStackTrace()[1].getMethodName(),
						request.getRequestURI(), Utils.SUCCESS_CODE, Utils.SUCCESS_MSG, start));
		return resultList;
	}
	
	
	@RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
	public User getById(@PathVariable("id") String id, HttpServletRequest request) {
		long start = System.currentTimeMillis();
		User result = new User();

		try {
			result = userRepo.findByid(id);
			cdrLogger.info(Utils.printCdrLog(request.getRemoteAddr(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), request.getRequestURI(),
					Utils.SUCCESS_CODE, Utils.SUCCESS_MSG, start));
		} catch (Exception e) {
			long finish = System.currentTimeMillis();
			long timeElapsed = finish - start;
			logger.error(e.getMessage());
			cdrLogger.error(Utils.printCdrLog(request.getRemoteAddr(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), request.getRequestURI(),
					Utils.ERROR_CODE, e.getMessage(), timeElapsed));
		}
		return result;
	}
	
	@RequestMapping(value = "/getByEmail/{email}", method = RequestMethod.GET)
	public User getByEmail(@PathVariable("email") String email, HttpServletRequest request) {
		long start = System.currentTimeMillis();
		User result = new User();

		try {
			result = userRepo.findByemail(email);
			cdrLogger.info(Utils.printCdrLog(request.getRemoteAddr(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), request.getRequestURI(),
					Utils.SUCCESS_CODE, Utils.SUCCESS_MSG, start));
		} catch (Exception e) {
			long finish = System.currentTimeMillis();
			long timeElapsed = finish - start;
			logger.error(e.getMessage());
			cdrLogger.error(Utils.printCdrLog(request.getRemoteAddr(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), request.getRequestURI(),
					Utils.ERROR_CODE, e.getMessage(), timeElapsed));
		}
		return result;
	}
	
	@RequestMapping(value = "/getByEmailAndPassword/{email}/{password}", method = RequestMethod.GET)
	public User getByEmailAndPassword(@PathVariable("email") String email, @PathVariable("password") String password,
			HttpServletRequest request) {
		long start = System.currentTimeMillis();
		User result = new User();

		try {
			result = userRepo.findByemailAndPassword(email, password);
			cdrLogger.info(Utils.printCdrLog(request.getRemoteAddr(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), request.getRequestURI(),
					Utils.SUCCESS_CODE, Utils.SUCCESS_MSG, start));
		} catch (Exception e) {
			long finish = System.currentTimeMillis();
			long timeElapsed = finish - start;
			logger.error(e.getMessage());
			cdrLogger.error(Utils.printCdrLog(request.getRemoteAddr(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), request.getRequestURI(),
					Utils.ERROR_CODE, e.getMessage(), timeElapsed));
		}
		return result;
	}
	
	@RequestMapping(value = "/loginUser/{email}/{password}", method = RequestMethod.GET)
	public @ResponseBody AddUpdateOutput loginUser(@PathVariable("email") String email, @PathVariable("password") String password,
			HttpServletRequest request) {
		long start = System.currentTimeMillis();
		AddUpdateOutput result = new AddUpdateOutput();
		User user = new User();

		try {
			user = userRepo.findByemailAndPassword(email, password);
			request.getSession().invalidate();
			
			if (user != null) {
				result.setRowId(user.getId());
				request.getSession().setAttribute("USER_ID", user.getId());
				result.setResponseMsg("Login Success");
			} else {
				result.setRowId(email);
				result.setResponseMsg("Invalid Email or Password");
			}
				
			cdrLogger.info(Utils.printCdrLog(request.getRemoteAddr(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), request.getRequestURI(),
					Utils.SUCCESS_CODE, Utils.SUCCESS_MSG, start));
		} catch (Exception e) {
			long finish = System.currentTimeMillis();
			long timeElapsed = finish - start;
			logger.error(e.getMessage());
			cdrLogger.error(Utils.printCdrLog(request.getRemoteAddr(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), request.getRequestURI(),
					Utils.ERROR_CODE, e.getMessage(), timeElapsed));
		}
		return result;
	}
	
		

	@Transactional
	@RequestMapping(value = "/addUpdate", method = RequestMethod.POST)
	public @ResponseBody AddUpdateOutput addUpdate(@RequestBody UserPojo pojo, HttpServletRequest request) {
		long start = System.currentTimeMillis();
		AddUpdateOutput result = new AddUpdateOutput();
		User body = new User(pojo);
		boolean checkNewUser = false;
		String id = Utils.UUID();
		
		try {
			if (body != null) {
				if (body.getId() == null) {
					body.setId(id);
					body.setCreatedAt(new Date());
					checkNewUser = true;
				}
				body.setUpdatedAt(new Date());
			
				// add default folder when create new user
				if (checkNewUser == true) {
					List<Folder> folderList = new ArrayList<Folder>();
					Folder folder = new Folder();
					folder.setId(Utils.UUID());
					folder.setUserId(id);
					folder.setName("Default Folder");
					folder.setCreatedAt(new Date());
					folder.setUpdatedAt(new Date());
					folderList.add(folder);
					body.setFolder(folderList);
				}
//			
//				// set RowId Folder
//				List<Folder> currentFolder = body.getFolder();
//				if(currentFolder != null) {
//					for(Folder folderValue : currentFolder) {
//						if (folderValue.getId() == null) {
//							folderValue.setId(Utils.UUID());
//							folderValue.setUserId(body);
//						}
//						// set RowId Pin
//						List<Pin> currentPin = folderValue.getPin();
//						if(currentPin != null) {
//							for(Pin pinValue : currentPin) {
//								if (pinValue.getId() == null) {
//									pinValue.setId(Utils.UUID());
//									pinValue.setFolderId(folderValue);
//								}
//								
//								// set RowId Image
//								List<Image> currentImage = pinValue.getImage();
//								if(currentImage != null) {
//									for(Image imageValue : currentImage) {
//										if (imageValue.getId() == null) {
//											imageValue.setId(Utils.UUID());
//											imageValue.setPinId(pinValue);
//										}
//									}
//								}
//							}
//						}
//					}
//				}
				

				userRepo.save(body);
				result.setRowId(body.getId());
				result.setResponseMsg("Save Success");
			}
			cdrLogger.info(Utils.printCdrLog(request.getRemoteAddr(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), request.getRequestURI(),
					Utils.SUCCESS_CODE, Utils.SUCCESS_MSG, start));
		} catch (Exception e) {
			result.setResponseMsg("Error : " + e.getMessage());
			logger.error(Thread.currentThread().getStackTrace()[1].getMethodName(), e);
			cdrLogger.error(Utils.printCdrLog(request.getRemoteAddr(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), request.getRequestURI(),
					Utils.ERROR_CODE, e, start));
		}
		return result;
	}

	@RequestMapping(value = "/deleteById/{id}", method = RequestMethod.GET)
	public @ResponseBody AddUpdateOutput deleteById(@PathVariable("id") String id,
			HttpServletRequest request) {
		long start = System.currentTimeMillis();
		AddUpdateOutput result = new AddUpdateOutput();
		User user = new User();

		try {
			result.setRowId(id);
			user = userRepo.findByid(id);

			userRepo.delete(user);
			result.setResponseMsg("Delete Success");
				
			cdrLogger.info(Utils.printCdrLog(request.getRemoteAddr(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), request.getRequestURI(),
					Utils.SUCCESS_CODE, Utils.SUCCESS_MSG, start));
			userRepo.delete(user);
			result.setResponseMsg("Delete Success");
			cdrLogger.info(Utils.printCdrLog(request.getRemoteAddr(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), request.getRequestURI(),
					Utils.SUCCESS_CODE, Utils.SUCCESS_MSG, start));
		} catch (Exception e) {
			long finish = System.currentTimeMillis();
			long timeElapsed = finish - start;
			logger.error(e.getMessage());
			cdrLogger.error(Utils.printCdrLog(request.getRemoteAddr(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), request.getRequestURI(),
					Utils.ERROR_CODE, e.getMessage(), timeElapsed));
		}
		return result;
	}
	

}


