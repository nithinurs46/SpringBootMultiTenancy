# Multi-tenancy with Spring Boot using Database per Tenant approach - 

1. Create an interceptor to read the tenantId from the request header.

2. Create a class to hold the tenant context
TenantContext class is used to store the tenant Identifier for each request. ThreadLocal object is used for this purpose. TheadLocal construct allows us to store data that will be accessible only by a specific thread. InheritableThreadLocal allows the child threads created from the main thread in our application to use the tenantId of the Parent Thread.

3. Create an DBDetails.java entity class to read the tenant db details from the master db

4. Create a JPARepository class for the above entity class

5. Create TenantDataSource.java, which will create datasource for all the tenants obtained from the master db and store it in a map. method is called within @PostConstruct to load the datasource

Hibernate related configurations to enable Multi-tenancy

1. Create TenantSchemaResolver.java which implements CurrentTenantIdentifierResolver, this class resolves the tenant identifier to use by making use of the tenant information set in ThreadLocal object

2. Create DataSourceBasedMultiTenantConnectionProviderImpl.java which implements AbstractDataSourceBasedMultiTenantConnectionProviderImpl and override the selectDataSource(). This method returns the datasource for the requested tenantId.

3. Create HibernateConfig.java , set the multi-tenant strategy - 
jpaPropertiesMap.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
This class takes MultiTenantConnectionProvider as argument(above step this class is injected)

Create an entity class User to read the user data from each tenant.

URL - http://localhost:8080/multiTenantApp/users

Set tenantId as 1001 or 1002 in request header