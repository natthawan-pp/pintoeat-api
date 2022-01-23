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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pintoeat.api.model.Folder;
import com.pintoeat.api.model.Image;
import com.pintoeat.api.model.Pin;
import com.pintoeat.api.model.User;
import com.pintoeat.api.pojo.AddUpdateOutput;
import com.pintoeat.api.pojo.ImageConvertPojo;
import com.pintoeat.api.pojo.ImagePojo;
import com.pintoeat.api.repository.ImageRepository;
import com.pintoeat.api.utils.Utils;
import java.util.Base64;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/pintoeat-image")
public class ImageController {
	
	final static Logger logger = Logger.getLogger(ImageController.class);
	final static Logger cdrLogger = Logger.getLogger(Utils.CDR_LOGGER);
	
	@Autowired
	ImageRepository imageRepo;
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public List<Image> getAll(HttpServletRequest request) {
		long start = System.currentTimeMillis();
		List<Image> resultList = new ArrayList<Image>();
		resultList = (List<Image>) imageRepo.findAll();
		
		cdrLogger.info(
				Utils.printCdrLog(request.getRemoteAddr(), Thread.currentThread().getStackTrace()[1].getMethodName(),
						request.getRequestURI(), Utils.SUCCESS_CODE, Utils.SUCCESS_MSG, start));
		return resultList;
	}
	
	
	@RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
	public Image getById(@PathVariable("id") String id, HttpServletRequest request) {
		long start = System.currentTimeMillis();
		Image result = new Image();

		try {
			result = imageRepo.findByid(id);
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
	
	@RequestMapping(value = "/getByPinId/{pinId}", method = RequestMethod.GET)
	public List<Image> getByPinId(
			@PathVariable("pinId") String pinId, HttpServletRequest request) {

		long start = System.currentTimeMillis();
		List<Image> image = new ArrayList<Image>();
		// Pin pin = pinRepo.findByid(pinid);

		try {
			image = imageRepo.findBypinId(pinId);
			cdrLogger.info(Utils.printCdrLog(request.getRemoteAddr(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), request.getRequestURI(),
					Utils.SUCCESS_CODE, Utils.SUCCESS_MSG, start));

		} catch (Exception e) {

			logger.error(Thread.currentThread().getStackTrace()[1].getMethodName(), e);
			cdrLogger.error(Utils.printCdrLog(request.getRemoteAddr(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), request.getRequestURI(),
					Utils.ERROR_CODE, e, start));
		}

		return image;
	}

	@Transactional
	@RequestMapping(value = "/addUpdate", method = RequestMethod.POST)
	public AddUpdateOutput addUpdate(@ModelAttribute ImageConvertPojo pojo, HttpServletRequest request) {
		long start = System.currentTimeMillis();
		AddUpdateOutput result = new AddUpdateOutput();
		Image body = new Image(pojo);
		
		try {
			if (body != null) {
				if (body.getId() == null) {
					body.setId(Utils.UUID());
					body.setCreatedAt(new Date());
				}
				body.setUpdatedAt(new Date());

				imageRepo.save(body);
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
		Image user = new Image();

		try {
			result.setRowId(id);
			user = imageRepo.findByid(id);

			imageRepo.delete(user);
			result.setResponseMsg("Delete Success");
				
			cdrLogger.info(Utils.printCdrLog(request.getRemoteAddr(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), request.getRequestURI(),
					Utils.SUCCESS_CODE, Utils.SUCCESS_MSG, start));
			imageRepo.delete(user);
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


