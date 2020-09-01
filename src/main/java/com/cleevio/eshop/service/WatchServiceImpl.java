package com.cleevio.eshop.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.cleevio.eshop.model.Watch;
import com.cleevio.eshop.repository.WatchRepository;



@Transactional
@Service
public class WatchServiceImpl implements WatchService {
	
	@Autowired
	private WatchRepository watchRepository;

	@Override
	public Watch createWatch(Watch watch) {
		
		return  watchRepository.save(watch);
	}
	

	@Override
	public HttpStatus updateWatch(Watch watch) {
		Optional<Watch> watchInDb = watchRepository.findById(watch.getId());
		if(!watchInDb.isPresent()) {
			return HttpStatus.NOT_FOUND;
		}else {
			Watch watchUpdated = watchInDb.get();
			watchUpdated.setId(watch.getId());
			watchUpdated.setTitle(watch.getTitle());
			watchUpdated.setCreatedDate(new Date());
			watchUpdated.setDescription(watch.getDescription());
			watchUpdated.setFountain(watch.getFountain());
			watchUpdated.setPrice(watch.getPrice());
			watchRepository.save(watchUpdated);
			return HttpStatus.OK;
		}

	}

	@Override
	public List<Watch> getAllWatches() {
		
		return watchRepository.findAll();
	}

	@Override
	public Watch getWatchByID(long watchId) {
		Optional<Watch> watchInDb = watchRepository.findById(watchId);
		if(watchInDb.isPresent()) {
			return watchInDb.get();
		}else {
			return null;
		}
		
	}

	@Override
	public 	HttpStatus deleteWatch(long id) {
		Optional<Watch> watch = watchRepository.findById(id);
		if(!watch.isPresent()) {
			return HttpStatus.NOT_FOUND;
		}else {
			watchRepository.delete(watch.get());
			return HttpStatus.OK;
		}
	}

}
