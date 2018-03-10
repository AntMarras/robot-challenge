package com.challenge.robot.rest;

import com.challenge.robot.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("api/public/")
public class Resource {

  private Service service;

  @Autowired
  public Resource(Service service) {
    this.service = service;
  }

  /**
   * @return simple health check.
   */
  @RequestMapping(method = RequestMethod.GET, value = "health", produces = {MediaType.APPLICATION_JSON_VALUE})
  public Map<String, String> getStatus() {
    return Collections.singletonMap("Status", "I'm alive");
  }
}
