package com.featuretogglemanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.featuretogglemanager.model.FeatureDetail;

@RestController
public class FeatureController {
	@RequestMapping(method=RequestMethod.GET, path="/feature")
	public ResponseEntity<FeatureDetail> getFeature(){
		FeatureDetail feature = new FeatureDetail();
		
		feature.setDescription("test description");
		feature.setFeatureId("F-0000");
		feature.setFeatureName("feature name");
		feature.setFeatureStatus(true);
		feature.setLifecycleStatus("initialized");
		feature.setServiceName("Cool Service");
		feature.setServiceVersion("1.0.0-SNAPSHOT");
		
		return ResponseEntity.ok(feature);
	}
}
