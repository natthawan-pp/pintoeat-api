package com.pintoeat.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pintoeat.api.model.User;
import com.pintoeat.api.pojo.PoAddUpdateOutput;
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

	@Transactional
	@RequestMapping(value = "/addUpdate", method = RequestMethod.POST)
	public @ResponseBody PoAddUpdateOutput addUpdate(@RequestBody UserPojo pojo, HttpServletRequest request) {
		long start = System.currentTimeMillis();
		PoAddUpdateOutput result = new PoAddUpdateOutput();
		User body = new User(pojo);
		
		try {
			if (body != null) {
				if (body.getId() == null) {
					body.setId(Utils.UUID());
				}

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
	public @ResponseBody PoAddUpdateOutput deleteById(@PathVariable("id") String id,
			HttpServletRequest request) {
		long start = System.currentTimeMillis();
		PoAddUpdateOutput result = new PoAddUpdateOutput();
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


