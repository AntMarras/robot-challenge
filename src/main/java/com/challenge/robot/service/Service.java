package com.challenge.robot.service;

import com.challenge.robot.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Simple service that delegate to the underlying DAO.
 */
@Component
public class Service {

  private Dao dao;

  @Autowired
  public Service(Dao dao){
    this.dao = dao;
  }

  /**
   * Create.
   *
   * @param name name.
   * @param data data.
   */
  public void createTicketFor(String name, Integer data) {
    dao.create(name, data);
  }
}
