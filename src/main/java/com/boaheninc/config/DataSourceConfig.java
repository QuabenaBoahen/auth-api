package com.boaheninc.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class DataSourceConfig {
	
	public DataSource dataSource(){
		EmbeddedDatabaseBuilder builder=new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db=(EmbeddedDatabase) builder
				.setType(EmbeddedDatabaseType.H2)
				.setName("studentdb")
				.build();
		return db;
	}

}
