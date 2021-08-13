package dev.config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import dev.entity.DBDetails;
import dev.repo.DataSourceConfigRepository;

@Component
public class TenantDataSource implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -2418234625461365801L;
	private Map<String, DataSource> dataSources = new HashMap<>();

	@Autowired
	private DataSourceConfigRepository configRepo;

	@PostConstruct
	public Map<String, DataSource> getAllTenantDS() {
		List<DBDetails> dbList = configRepo.findAll();

		Map<String, DataSource> result = dbList.stream()
				.collect(Collectors.toMap(DBDetails::getDbId, db -> getDataSource(db.getDbId())));

		return result;
	}

	public DataSource getDataSource(String dbId) {
		if (dataSources.get(dbId) != null) {
			return dataSources.get(dbId);
		}
		DataSource dataSource = createDataSource(dbId);
		if (dataSource != null) {
			dataSources.put(dbId, dataSource);
		}
		return dataSource;
	}

	private DataSource createDataSource(String dbId) {
		Optional<DBDetails> db = configRepo.findById(dbId);
		if (db != null) {
			DataSourceBuilder factory = DataSourceBuilder.create().driverClassName("oracle.jdbc.OracleDriver")
					.username(db.get().getDbName()).password(db.get().getDbPwd())
					.url("jdbc:oracle:thin:@" + db.get().getUrl());
			DataSource ds = (DataSource) factory.build();
			return ds;
		}
		return null;
	}
}
