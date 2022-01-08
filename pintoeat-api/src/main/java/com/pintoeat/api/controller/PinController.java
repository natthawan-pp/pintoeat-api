package com.pintoeat.api.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
import com.pintoeat.api.pojo.NearByOutput;
import com.pintoeat.api.pojo.PinPojo;
import com.pintoeat.api.repository.FolderRepository;
import com.pintoeat.api.repository.PinRepository;
import com.pintoeat.api.repository.UserRepository;
import com.pintoeat.api.utils.Utils;

@RestController
@RequestMapping("/api/pintoeat-pin")
public class PinController {
	
	final static Logger logger = Logger.getLogger(PinController.class);
	final static Logger cdrLogger = Logger.getLogger(Utils.CDR_LOGGER);
	
	@Autowired
	PinRepository pinRepo;
	
	@Autowired
	FolderRepository folderRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public List<Pin> getAll(HttpServletRequest request) {
		long start = System.currentTimeMillis();
		List<Pin> resultList = new ArrayList<Pin>();
		resultList = (List<Pin>) pinRepo.findAll();
		
		cdrLogger.info(
				Utils.printCdrLog(request.getRemoteAddr(), Thread.currentThread().getStackTrace()[1].getMethodName(),
						request.getRequestURI(), Utils.SUCCESS_CODE, Utils.SUCCESS_MSG, start));
		return resultList;
	}
	
	
	@RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
	public Pin getById(@PathVariable("id") String id, HttpServletRequest request) {
		long start = System.currentTimeMillis();
		Pin result = new Pin();

		try {
			result = pinRepo.findByid(id);
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
	public Pin getByName(@PathVariable("name") String name, HttpServletRequest request) {
		long start = System.currentTimeMillis();
		Pin result = new Pin();

		try {
			result = pinRepo.findByname(name);
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
	
	@RequestMapping(value = "/getByFolderId/{folderId}", method = RequestMethod.GET)
	public List<Pin> getByFolderId(
			@PathVariable("folderId") String folderid, HttpServletRequest request) {

		long start = System.currentTimeMillis();
		List<Pin> pin = new ArrayList<Pin>();
		// Folder folder = folderRepo.findByid(folderid);

		try {
			pin = pinRepo.findByfolderId(folderid);
			cdrLogger.info(Utils.printCdrLog(request.getRemoteAddr(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), request.getRequestURI(),
					Utils.SUCCESS_CODE, Utils.SUCCESS_MSG, start));

		} catch (Exception e) {

			logger.error(Thread.currentThread().getStackTrace()[1].getMethodName(), e);
			cdrLogger.error(Utils.printCdrLog(request.getRemoteAddr(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), request.getRequestURI(),
					Utils.ERROR_CODE, e, start));
		}

		return pin;
	}

	@Transactional
	@RequestMapping(value = "/addUpdate", method = RequestMethod.POST)
	public @ResponseBody AddUpdateOutput addUpdate(@RequestBody PinPojo pojo, HttpServletRequest request) {
		long start = System.currentTimeMillis();
		AddUpdateOutput result = new AddUpdateOutput();
		Pin body = new Pin(pojo);
		
		try {
			if (body != null) {
				if (body.getId() == null) {
					body.setCreatedAt(new Date());
					body.setId(Utils.UUID());
				}
				body.setUpdatedAt(new Date());
				
//				// set RowId Image
//				List<Image> currentImage = body.getImage();
//				if(currentImage != null) {
//					for(Image imageValue : currentImage) {
//						if (imageValue.getId() == null) {
//							imageValue.setId(Utils.UUID());
//							imageValue.setPinId(body);
//						}
//					}
//				}

				pinRepo.save(body);
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
		Pin pin = new Pin();

		try {
			result.setRowId(id);
			pin = pinRepo.findByid(id);

			pinRepo.delete(pin);
			result.setResponseMsg("Delete Success");
				
			cdrLogger.info(Utils.printCdrLog(request.getRemoteAddr(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), request.getRequestURI(),
					Utils.SUCCESS_CODE, Utils.SUCCESS_MSG, start));
			pinRepo.delete(pin);
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
	
	@RequestMapping(value = "/getBookmark/{userId}", method = RequestMethod.GET)
	public List<Pin> getBookmark(
			@PathVariable("userId") String userId, HttpServletRequest request) {

		long start = System.currentTimeMillis();
		List<Pin> pin = new ArrayList<Pin>();

		try {
			pin = pinRepo.findByBookmark(userId);
			cdrLogger.info(Utils.printCdrLog(request.getRemoteAddr(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), request.getRequestURI(),
					Utils.SUCCESS_CODE, Utils.SUCCESS_MSG, start));

		} catch (Exception e) {

			logger.error(Thread.currentThread().getStackTrace()[1].getMethodName(), e);
			cdrLogger.error(Utils.printCdrLog(request.getRemoteAddr(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), request.getRequestURI(),
					Utils.ERROR_CODE, e, start));
		}

		return pin;
	}
	
	@RequestMapping(value = "/getNearBy/{userId}/{currentLocation}", method = RequestMethod.GET)
	public List<NearByOutput> getNearBy(
			@PathVariable("userId") String userId, @PathVariable("currentLocation") String currentLocation, HttpServletRequest request) {

		long start = System.currentTimeMillis();
		List<Pin> pin = new ArrayList<Pin>();
		List<NearByOutput> nearBy = new ArrayList<NearByOutput>();

		try {
			pin = pinRepo.findPinByUserId(userId);

			// calculate nearby 
			String[] strings = currentLocation.split(",");
			double currentLat = Double.parseDouble(strings[0]);
			double currentLon = Double.parseDouble(strings[1]);
			
			for (int i = 0; i < pin.size(); i++) {
				NearByOutput data =  new NearByOutput();
				data.setId(pin.get(i).getId());
				data.setName(pin.get(i).getName());
				data.setFavorite(pin.get(i).isFavorite());
				data.setImage(pin.get(i).getImage());
				
				String[] stringsData = pin.get(i).getLocation().split(",");
				double pinLat = Double.parseDouble(stringsData[0]);
				double pinLon = Double.parseDouble(stringsData[1]);
				double calDistance = distance(currentLat, currentLon, pinLat, pinLon);
		        BigDecimal decimal = new BigDecimal(calDistance).setScale(2, RoundingMode.HALF_UP);
		        double length = decimal.doubleValue();
		        
		        data.setLength(length);
				nearBy.add(data);
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

		return nearBy;
	}

	
    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        /* Convert m to km 1.609344 */
        dist = dist * 60 * 1.1515 * 1.609344;
        return (dist);
      }
      
     /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
     /*::  This function converts decimal degrees to radians             :*/
     /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
     private double deg2rad(double deg) {
       return (deg * Math.PI / 180.0);
     }
      
     /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
     /*::  This function converts radians to decimal degrees             :*/
     /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
     private double rad2deg(double rad) {
       return (rad * 180.0 / Math.PI);
     }

	

}


