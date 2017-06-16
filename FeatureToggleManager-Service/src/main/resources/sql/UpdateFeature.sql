UPDATE "Feature"
SET "featureName" = :featureName,
	"featureStatus" = :featureStatus,
    "lifecycleStatus" = :lifecycleStatus,
    "serviceName" = :serviceName,
    "serviceVersion" = :serviceVersion,
    "description" = :description
WHERE "featureId" = :featureId;