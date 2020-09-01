package com.cleevio.eshop.service;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.cleevio.eshop.model.Watch;


public interface WatchService {
	
	Watch createWatch(Watch watch);
	
	HttpStatus updateWatch(Watch watch);
	
	List<Watch>  getAllWatches();
	
	Watch getWatchByID(long id);
	
	HttpStatus deleteWatch(long id);

}
