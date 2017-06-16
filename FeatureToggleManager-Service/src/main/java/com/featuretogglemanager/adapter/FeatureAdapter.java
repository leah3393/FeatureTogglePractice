package com.featuretogglemanager.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.featuretogglemanager.domain.FeatureTO;
import com.featuretogglemanager.domain.IdGenerator;
import com.featuretogglemanager.mapper.FeatureMapper;
import com.featuretogglemanager.model.FeatureDetail;
import com.featuretogglemanager.repository.FeatureRepository;
import com.featuretogglemanager.sql.SQLFilter;

@Component
public class FeatureAdapter {
	@Autowired
	private FeatureMapper mapper;
	
	@Autowired
	private FeatureRepository featureRepo;
	
	public FeatureDetail getFeatureById(String id){
		FeatureTO featureTO = featureRepo.findOne(id);
		return (featureTO == null? null : mapper.map(featureTO));
	}
	
	public List<FeatureDetail> getAllFeatures(){
		return mapper.map(featureRepo.findAll());
	}
	
	public List<FeatureDetail> getFeaturesByFilter(String featureName, String serviceName, String version, String lifecycle){
		List<SQLFilter> sqlFilters = new ArrayList<>();
		if(featureName != null){
			sqlFilters.add(new SQLFilter("featureName", "featureName", featureName));
		}
		
		if(serviceName != null){
			sqlFilters.add(new SQLFilter("serviceName", "serviceName", serviceName));
		}
		
		if(version != null){
			sqlFilters.add(new SQLFilter("serviceVersion", "serviceVersion", version));
		}
		
		if(lifecycle != null){
			sqlFilters.add(new SQLFilter("lifecycleStatus", "lifecycleStatus", lifecycle));
		}
		
		return mapper.map(featureRepo.findWithFilters(sqlFilters));
	}
	
	public FeatureDetail createFeature(FeatureDetail feature){
		feature.setFeatureId(IdGenerator.generateId());
		if(featureRepo.insert(mapper.map(feature)) == 1){
			return feature;
		}
		return null;
	}
	
	public FeatureDetail updateFeature(FeatureDetail feature){
		if(featureRepo.update(mapper.map(feature)) == 1){
			return feature;
		}
		return null;
	}
}
