package com.featuretogglemanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.featuretogglemanager.adapter.FeatureAdapter;
import com.featuretogglemanager.model.FeatureDetail;

@RestController
public class FeatureController {
	
	@Autowired
	private FeatureAdapter adapter;
	
	@RequestMapping(method=RequestMethod.GET, path="/feature/{featureId}")
	public ResponseEntity<FeatureDetail> getFeature(@PathVariable String featureId){
		FeatureDetail feature = adapter.getFeatureById(featureId);
		
		return ResponseEntity.ok(feature);
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/features")
	public ResponseEntity<List<FeatureDetail>> getFeatures(
			@RequestParam(value = "featureName", required = false) String featureName,			
			@RequestParam(value = "lifecycleStatus", required = false) String lifecycle,
			@RequestParam(value = "serviceName", required = false) String service,
			@RequestParam(value = "releaseVersion", required = false) String version){
		
		List<FeatureDetail> features = adapter.getFeaturesByFilter(featureName, service, version, lifecycle);
		
		return ResponseEntity.ok(features);
	}
	
	@RequestMapping(method=RequestMethod.POST, path="/feature")
	public ResponseEntity<FeatureDetail> createFeature(@RequestBody FeatureDetail feature){
		FeatureDetail createdFeature = adapter.createFeature(feature);
		
		return ResponseEntity.ok(createdFeature);
	}
	
	@RequestMapping(method=RequestMethod.PUT, path="/feature")
	public ResponseEntity<FeatureDetail> updateFeature(@RequestBody FeatureDetail feature){
		FeatureDetail updatedFeature = adapter.updateFeature(feature);
		
		return ResponseEntity.ok(updatedFeature);
	}
}
