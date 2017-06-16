UPDATE "Feature"
SET "featureName" = 'New Name',
	"featureStatus" = false,
    "lifecycleStatus" = 'initial creation',
    "serviceName" = 'New Service',
    "serviceVersion" = '1.0.0-RELEASE',
    "description" = 'updated description'
WHERE "featureId" = 'F-0000';