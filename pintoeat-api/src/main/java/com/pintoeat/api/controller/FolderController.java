package com.pintoeat.api.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
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

import com.pintoeat.api.model.Folder;
import com.pintoeat.api.model.Image;
import com.pintoeat.api.model.Pin;
import com.pintoeat.api.model.User;
import com.pintoeat.api.pojo.AddUpdateOutput;
import com.pintoeat.api.pojo.FolderOutput;
import com.pintoeat.api.pojo.FolderPojo;
import com.pintoeat.api.pojo.NearByOutput;
import com.pintoeat.api.repository.FolderRepository;
import com.pintoeat.api.repository.UserRepository;
import com.pintoeat.api.utils.Utils;


@RestController
@RequestMapping("/api/pintoeat-folder")
public class FolderController {
	
	final static Logger logger = Logger.getLogger(FolderController.class);
	final static Logger cdrLogger = Logger.getLogger(Utils.CDR_LOGGER);
	
	@Autowired
	FolderRepository folderRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public List<Folder> getAll(HttpServletRequest request) {
		long start = System.currentTimeMillis();
		List<Folder> resultList = new ArrayList<Folder>();
		resultList = (List<Folder>) folderRepo.findAll();
		
		cdrLogger.info(
				Utils.printCdrLog(request.getRemoteAddr(), Thread.currentThread().getStackTrace()[1].getMethodName(),
						request.getRequestURI(), Utils.SUCCESS_CODE, Utils.SUCCESS_MSG, start));
		return resultList;
	}
	
	
	@RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
	public Folder getById(@PathVariable("id") String id, HttpServletRequest request) {
		long start = System.currentTimeMillis();
		Folder result = new Folder();

		try {
			result = folderRepo.findByid(id);
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
	
	@RequestMapping(value = "/getByName/{name}", method = RequestMethod.GET)
	public Folder getByName(@PathVariable("name") String name, HttpServletRequest request) {
		long start = System.currentTimeMillis();
		Folder result = new Folder();

		try {
			result = folderRepo.findByname(name);
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
	
	@RequestMapping(value = "/getByUserId/{userId}", method = RequestMethod.GET)
	public List<Folder> getByUserId(
			@PathVariable("userId") String userid, HttpServletRequest request) {

		long start = System.currentTimeMillis();
		List<Folder> folder = new ArrayList<Folder>();
		// User user = userRepo.findByid(userid);

		try {
			folder = folderRepo.findByuserId(userid);
			cdrLogger.info(Utils.printCdrLog(request.getRemoteAddr(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), request.getRequestURI(),
					Utils.SUCCESS_CODE, Utils.SUCCESS_MSG, start));

		} catch (Exception e) {

			logger.error(Thread.currentThread().getStackTrace()[1].getMethodName(), e);
			cdrLogger.error(Utils.printCdrLog(request.getRemoteAddr(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), request.getRequestURI(),
					Utils.ERROR_CODE, e, start));
		}

		return folder;
	}
	
	@RequestMapping(value = "/getFolderOutputByUserId/{userId}", method = RequestMethod.GET)
	public List<FolderOutput> getFolderOutputByUserId(
			@PathVariable("userId") String userid, HttpServletRequest request) {

		long start = System.currentTimeMillis();
		List<Folder> folder = new ArrayList<Folder>();
		// User user = userRepo.findByid(userid);
		List<FolderOutput> folderOutput = new ArrayList<FolderOutput>();

		try {
			folder = folderRepo.findByuserId(userid);
			
			// select only first pin and image priority = 1
			for (int i = 0; i < folder.size(); i++) {
				FolderOutput data =  new FolderOutput();
				data.setId(folder.get(i).getId());
				data.setName(folder.get(i).getName());
				data.setFavorite(folder.get(i).isFavorite());
				data.setPinCount(folder.get(i).getPin().size());
				if (folder.get(i).getPin().size() > 0) {
					Pin pin = new Pin();
					// get first pin
					pin.setId(folder.get(i).getPin().get(0).getId()); 
					pin.setName(folder.get(i).getPin().get(0).getName());
					pin.setBookmark(folder.get(i).getPin().get(0).isBookmark());
					pin.setDescription(folder.get(i).getPin().get(0).getDescription());
					pin.setLocation(folder.get(i).getPin().get(0).getLocation());
					
					// get image priority = 1
					if (folder.get(i).getPin().get(0).getImage().size() > 0) { 
						for (int j = 0; j < folder.get(i).getPin().get(0).getImage().size(); j++) {
							if (folder.get(i).getPin().get(0).getImage().get(j).getPriority() == 1) { 
								Image image = new Image();
								image = folder.get(i).getPin().get(0).getImage().get(j);
								List<Image> imageList = new ArrayList<Image>();
								imageList.add(image);
								pin.setImage(imageList);
							}
						}
					}
					List<Pin> pinList = new ArrayList<Pin>();
					pinList.add(pin);
					data.setPin(pinList);
					
				}
				folderOutput.add(data);
			}
			
			cdrLogger.info(Utils.printCdrLog(request.getRemoteAddr(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), request.getRequestURI(),
					Utils.SUCCESS_CODE, Utils.SUCCESS_MSG, start));

		} catch (Exception e) {

			logger.error(Thread.currentThread().getStackTrace()[1].getMethodName(), e);
			cdrLogger.error(Utils.printCdrLog(request.getRemoteAddr(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), request.getRequestURI(),
					Utils.ERROR_CODE, e, start));
		}

		return folderOutput;
	}

	@Transactional
	@RequestMapping(value = "/addUpdate", method = RequestMethod.POST)
	public @ResponseBody AddUpdateOutput addUpdate(@RequestBody FolderPojo pojo, HttpServletRequest request) {
		long start = System.currentTimeMillis();
		AddUpdateOutput result = new AddUpdateOutput();
		Folder body = new Folder(pojo);
		
		try {
			if (body != null) {
				if (body.getId() == null) {
					body.setCreatedAt(new Date());
					body.setId(Utils.UUID());
				}
				body.setUpdatedAt(new Date());
				
//				// set RowId Pin
//				List<Pin> currentPin = body.getPin();
//				if(currentPin != null) {
//					for(Pin pinValue : currentPin) {
//						if (pinValue.getId() == null) {
//							pinValue.setId(Utils.UUID());
//							pinValue.setFolderId(body);
//						}
//						
//						// set RowId Image
//						List<Image> currentImage = pinValue.getImage();
//						if(currentImage != null) {
//							for(Image imageValue : currentImage) {
//								if (imageValue.getId() == null) {
//									imageValue.setId(Utils.UUID());
//									imageValue.setPinId(pinValue);
//								}
//							}
//						}
//					}
//				}

				folderRepo.save(body);
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
		Folder folder = new Folder();

		try {
			result.setRowId(id);
			folder = folderRepo.findByid(id);

			folderRepo.delete(folder);
			result.setResponseMsg("Delete Success");
				
			cdrLogger.info(Utils.printCdrLog(request.getRemoteAddr(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), request.getRequestURI(),
					Utils.SUCCESS_CODE, Utils.SUCCESS_MSG, start));
			folderRepo.delete(folder);
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


