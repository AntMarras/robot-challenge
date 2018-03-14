package com.challenge.robot.rest;

import com.challenge.robot.model.Position;
import com.challenge.robot.rest.model.Report;
import com.challenge.robot.rest.model.RobotIds;
import com.challenge.robot.rest.model.Rotation;
import com.challenge.robot.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

import static java.util.UUID.fromString;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("api/robot")
public class RobotResource {

  private RobotService service;
  private RestModelTranslator translator;

  @Autowired
  public RobotResource(RobotService service, RestModelTranslator translator) {
    this.service = service;
    this.translator = translator;
  }

  @RequestMapping(method = GET, produces = {APPLICATION_JSON_VALUE})
  public RobotIds getAllRobotIds() {
    return translator.translateIds(service.getAllRobotIds());
  }

  @RequestMapping(method = POST, consumes = {APPLICATION_JSON_VALUE}, produces = {APPLICATION_JSON_VALUE})
  public RobotIds place(@RequestBody Position position) {
    Optional<UUID> robotId = service.place(position);
    return translator.translateId(robotId);
  }

  @RequestMapping(method = POST, value = "{robotId}/move", consumes = {APPLICATION_JSON_VALUE})
  public void move(@PathVariable String robotId) {
    service.move(fromString(robotId));
  }

  @RequestMapping(method = POST, value = "{robotId}/rotate", consumes = {APPLICATION_JSON_VALUE})
  public void rotate(@PathVariable String robotId, @RequestBody Rotation rotation) {
    service.rotate(fromString(robotId), rotation.getDirection());
  }

  @RequestMapping(method = GET, value = "{robotId}")
  public Report report(@PathVariable String robotId) {
    Optional<Position> position = service.report(fromString(robotId));
    return translator.translate(position);
  }
}
