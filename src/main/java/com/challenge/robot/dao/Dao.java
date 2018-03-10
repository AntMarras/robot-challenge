package com.challenge.robot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class Dao {

  private JdbcTemplate jdbcTemplate;

  @Autowired
  public Dao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  /**
   * Create a new something.
   *
   * @param name name.
   * @param data data.
   */
  public void create(String name, Integer data) {
    jdbcTemplate.update("insert into TICKETS (COMPANY, HOURS) values (?, ?)", name, data);
  }
}
