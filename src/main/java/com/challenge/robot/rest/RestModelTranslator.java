package com.challenge.robot.rest;

import com.challenge.robot.model.Position;
import com.challenge.robot.rest.model.Report;
import com.challenge.robot.rest.model.RobotIds;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.singletonList;

@Component
class RestModelTranslator {

  Report translate(@SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<Position> position) {
    if (position.isPresent()) {
      return new Report(position.get().getX() + ", "
        + position.get().getY() + ", "
        + position.get().getFace().toString());
    }
    return new Report("Robot missing");
  }

  RobotIds translateIds(@SuppressWarnings("OptionalUsedAsFieldOrParameterType") List<UUID> robotIds) {
    return new RobotIds(robotIds);
  }

  RobotIds translateId(@SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<UUID> robotId) {
    return new RobotIds(singletonList(robotId.orElse(null)));
  }
}
