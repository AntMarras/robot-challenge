package com.challenge.robot.config;

import com.challenge.robot.dao.Dao;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.derby.jdbc.EmbeddedDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Configuration of the database.
 * For now the only available data source start an embedded in memory Derby database.
 * <p>
 * The Flyway database migration framework is used to create a schema and populate the database with
 * some mock data.
 * {@see https://flywaydb.org/}
 * </p>
 */
@Configuration
@ComponentScan(basePackageClasses = {Dao.class})
class DatabaseConfig {

  @Bean
  public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  @Bean
  @Primary
  public DataSource dataSourcePool() throws SQLException {
    HikariConfig config = new HikariConfig();
    config.setDataSource(createInMemoryDataBase());
    config.setMaximumPoolSize(10);
    config.setPoolName("Connection pool Hikari");
    return new HikariDataSource(config);
  }

  private DataSource createInMemoryDataBase() {
    EmbeddedDataSource ds = new EmbeddedDataSource();
    ds.setDatabaseName("memory:testdb");
    ds.setCreateDatabase("create");

    applyMigrations(ds);

    return ds;
  }

  private void applyMigrations(DataSource ds) {
    Flyway flyway = new Flyway();
    flyway.setDataSource(ds);
    flyway.setLocations("db/migration");

    flyway.migrate();
  }
}