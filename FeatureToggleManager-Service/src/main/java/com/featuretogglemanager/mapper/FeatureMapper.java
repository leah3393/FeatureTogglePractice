package com.featuretogglemanager.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.featuretogglemanager.domain.FeatureTO;
import com.featuretogglemanager.model.FeatureDetail;

@Component
public class FeatureMapper {
	public FeatureDetail map(FeatureTO to){
		FeatureDetail feature = new FeatureDetail();
		
		feature.setDescription(to.getDescription());
		feature.setFeatureId(to.getFeatureId());
		feature.setFeatureName(to.getFeatureName());
		feature.setFeatureStatus(to.isFeatureStatus());
		feature.setLifecycleStatus(to.getLifecycleStatus());
		feature.setServiceName(to.getServiceName());
		feature.setServiceVersion(to.getServiceVersion());
		
		return feature;
	}
	
	public FeatureTO map(FeatureDetail detail){
		FeatureTO to = new FeatureTO();
		
		to.setDescription(detail.getDescription());
		to.setFeatureId(detail.getFeatureId());
		to.setFeatureName(detail.getFeatureName());
		to.setFeatureStatus(detail.isFeatureStatus());
		to.setLifecycleStatus(detail.getLifecycleStatus());
		to.setServiceName(detail.getServiceName());
		to.setServiceVersion(detail.getServiceVersion());
		
		return to;
	}
	
	public List<FeatureDetail> map(List<FeatureTO> to){
		List<FeatureDetail> featureDetails = new ArrayList<>();
		
		for(FeatureTO t : to){
			featureDetails.add(map(t));
		}
		
		return featureDetails;
	}
}
