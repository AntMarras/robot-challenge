package com.challenge.robot.rest;

import com.challenge.robot.model.Direction;
import com.challenge.robot.model.Position;
import com.challenge.robot.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.UUID.fromString;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("api/robot")
public class Resource {

  private Service service;
  private ReportTranslator translator;

  @Autowired
  public Resource(Service service, ReportTranslator translator) {
    this.service = service;
    this.translator = translator;
  }

  @RequestMapping(method = GET)
  public List<String> allIds() {
    return service.allIds();
  }

  @RequestMapping(method = POST, consumes = {APPLICATION_JSON_VALUE})
  public void place(@RequestBody Position position) {
    service.place(position);
  }

  @RequestMapping(method = POST, value = "{robotId}/move", consumes = {APPLICATION_JSON_VALUE})
  public void move(@PathVariable String robotId) {
    service.move(fromString(robotId));
  }

  @RequestMapping(method = POST, value = "{robotId}/rotate", consumes = {APPLICATION_JSON_VALUE})
  public void rotate(@PathVariable String robotId, @RequestBody Direction direction) {
    service.rotate(fromString(robotId), direction);
  }

  @RequestMapping(method = GET, value = "{robotId}")
  public Report report(@PathVariable String robotId) {
    Optional<Position> position = service.report(fromString(robotId));
    return translator.translate(position);
  }
}
