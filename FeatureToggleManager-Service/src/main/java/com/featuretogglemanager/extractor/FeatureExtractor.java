package com.featuretogglemanager.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.featuretogglemanager.domain.FeatureTO;

@Component
public class FeatureExtractor implements ResultSetExtractor<List<FeatureTO>> {

	@Override
	public List<FeatureTO> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<FeatureTO> features = new ArrayList<>();
		
		while(rs.next()){
			FeatureTO feature = new FeatureTO();
			feature.setDescription(rs.getString("description"));
			feature.setFeatureId(rs.getString("featureId"));
			feature.setFeatureName(rs.getString("featureName"));
			feature.setFeatureStatus(rs.getBoolean("featureStatus"));
			feature.setLifecycleStatus(rs.getString("lifecycleStatus"));
			feature.setServiceName(rs.getString("serviceName"));
			feature.setServiceVersion(rs.getString("serviceVersion"));
			
			features.add(feature);
		}
		return features;
	}

}
