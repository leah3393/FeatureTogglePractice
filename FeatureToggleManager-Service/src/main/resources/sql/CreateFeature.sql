INSERT INTO "Feature" 
	(
		"featureId", 
		"featureName", 
		"featureStatus", 
		"lifecycleStatus", 
		"serviceName", 
		"serviceVersion", 
		"description"
	) VALUES
	(
		:featureId, 
		:featureName, 
		:featureStatus, 
		:lifecycleStatus, 
		:serviceName, 
		:serviceVersion, 
		:description
	);