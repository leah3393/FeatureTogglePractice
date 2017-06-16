package com.featuretogglemanager.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.featuretogglemanager.domain.FeatureTO;
import com.featuretogglemanager.extractor.FeatureExtractor;
import com.featuretogglemanager.sql.SQL;
import com.featuretogglemanager.sql.SQLFilter;

@Repository
public class FeatureRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	private FeatureExtractor extractor;
	
	@SQL(sqlFileName = "sql/RetrieveAllFeatures.sql")
	private String selectAllSQL;
	
	@SQL(sqlFileName = "sql/RetrieveFeatureById.sql")
	private String selectOneSQL;
	
	@SQL(sqlFileName = "sql/CreateFeature.sql")
	private String createSQL;
	
	@SQL(sqlFileName = "sql/UpdateFeature.sql")
	private String updateSQL;
	
	public List<FeatureTO> findAll(){
		return jdbcTemplate.query(selectAllSQL, extractor);
	}
	
	public FeatureTO findOne(String featureId){
		List<FeatureTO> features = jdbcTemplate.query(selectOneSQL, new MapSqlParameterSource("featureId", featureId), extractor);
		return ((features != null && features.size() != 0) ? features.get(0) : null);
	}
	
	public List<FeatureTO> findWithFilters(List<SQLFilter> sqlFilters){
		if(sqlFilters == null || sqlFilters.size() == 0){
			return findAll();
		}
		
		Map<String,String> params = new HashMap<>();
		String query = selectAllSQL + " WHERE " + sqlFilters.get(0);
		params.put(sqlFilters.get(0).getNamedParam(), sqlFilters.get(0).getParamValue());
		
		for(int i = 1; i < sqlFilters.size(); i++){
			query += " AND " + sqlFilters.get(i);
			params.put(sqlFilters.get(i).getNamedParam(), sqlFilters.get(i).getParamValue());
		}
		
		return jdbcTemplate.query(query, new MapSqlParameterSource(params), extractor);
	}

	public int insert(FeatureTO feature) {
		return jdbcTemplate.update(createSQL, new BeanPropertySqlParameterSource(feature));
	}

	public int update(FeatureTO feature) {
		return jdbcTemplate.update(updateSQL, new BeanPropertySqlParameterSource(feature));
	}
}
