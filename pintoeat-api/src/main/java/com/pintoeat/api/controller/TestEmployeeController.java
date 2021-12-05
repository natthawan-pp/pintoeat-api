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

import com.pintoeat.api.model.TestEmployee;
import com.pintoeat.api.pojo.PoAddUpdateOutput;
import com.pintoeat.api.pojo.TestEmployeePojo;
import com.pintoeat.api.repository.TestEmployeeRepository;
import com.pintoeat.api.utils.Utils;

@RestController
@RequestMapping("/api/test-master")
public class TestEmployeeController {
	
	final static Logger logger = Logger.getLogger(TestEmployeeController.class);
	final static Logger cdrLogger = Logger.getLogger(Utils.CDR_LOGGER);
	
	@Autowired
	TestEmployeeRepository testEmployeeRepo;
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public List<TestEmployee> getAll(HttpServletRequest request) {
		long start = System.currentTimeMillis();
		List<TestEmployee> resultList = new ArrayList<TestEmployee>();
		resultList = (List<TestEmployee>) testEmployeeRepo.findAll();
		
		cdrLogger.info(
				Utils.printCdrLog(request.getRemoteAddr(), Thread.currentThread().getStackTrace()[1].getMethodName(),
						request.getRequestURI(), Utils.SUCCESS_CODE, Utils.SUCCESS_MSG, start));
		return resultList;
	}
	
	
	@RequestMapping(value = "/getByEmployeeNo/{employeeNo}", method = RequestMethod.GET)
	public TestEmployee getByEmployeeNo(@PathVariable("employeeNo") String employeeNo, HttpServletRequest request) {
		long start = System.currentTimeMillis();
		TestEmployee result = new TestEmployee();

		try {
			result = testEmployeeRepo.findByEmployeeNo(employeeNo);
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
	public @ResponseBody PoAddUpdateOutput addUpdate(@RequestBody TestEmployeePojo pojo, HttpServletRequest request) {
		long start = System.currentTimeMillis();
		PoAddUpdateOutput result = new PoAddUpdateOutput();
		TestEmployee body = new TestEmployee(pojo);
		
		try {
			if (body != null) {
				if (body.getEmployeeNo() == null) {
					body.setEmployeeNo(Utils.UUID());
				}

				testEmployeeRepo.save(body);
				result.setRowId(body.getEmployeeNo());
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

	@RequestMapping(value = "/deleteEmployeeByEmployeeNo/{employeeNo}", method = RequestMethod.GET)
	public @ResponseBody PoAddUpdateOutput deleteEmployeeByEmployeeNo(@PathVariable("employeeNo") String employeeNo,
			HttpServletRequest request) {
		long start = System.currentTimeMillis();
		PoAddUpdateOutput result = new PoAddUpdateOutput();
		TestEmployee employee = new TestEmployee();

		try {
			result.setRowId(employeeNo);
			employee = testEmployeeRepo.findByEmployeeNo(employeeNo);

			testEmployeeRepo.delete(employee);
			result.setResponseMsg("Delete Success");
				
			cdrLogger.info(Utils.printCdrLog(request.getRemoteAddr(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), request.getRequestURI(),
					Utils.SUCCESS_CODE, Utils.SUCCESS_MSG, start));
			testEmployeeRepo.delete(employee);
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


