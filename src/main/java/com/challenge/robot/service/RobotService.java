package com.challenge.robot.service;

import com.challenge.robot.dao.RobotDao;
import com.challenge.robot.model.Direction;
import com.challenge.robot.model.Face;
import com.challenge.robot.model.Position;
import com.challenge.robot.model.Robot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Simple service that delegate to the underlying DAO.
 */
@Component
public class RobotService {

  private RobotDao dao;
  private TablePositionValidator validator;
  private Rotator rotator;
  private Mover mover;

  @Autowired
  public RobotService(RobotDao dao, TablePositionValidator validator, Rotator rotator, Mover mover) {
    this.dao = dao;
    this.validator = validator;
    this.rotator = rotator;
    this.mover = mover;
  }

  public Optional<UUID> place(Position position) {
    if (validator.validate(position)) {
      Robot robot = new Robot(position);
      dao.create(robot);
      return Optional.ofNullable(robot.getId());
    }
    return Optional.empty();
  }

  public void move(UUID id) {
    Optional<Robot> robot = dao.getRobotById(id);

    if (robot.isPresent()) {
      Position newPosition = mover.move(robot.get().getPosition());
      if (validator.validate(newPosition)) {
        robot.get().setPosition(newPosition);
        dao.updateRobot(robot.get());
      }
    }
  }

  public void rotate(UUID id, Direction direction) {
    Optional<Robot> robot = dao.getRobotById(id);

    if (robot.isPresent()) {
      Face newFace = rotator.rotate(robot.get().getPosition().getFace(), direction);

      Position newPosition = robot.get().getPosition();
      newPosition.setFace(newFace);
      robot.get().setPosition(newPosition);

      dao.updateRobot(robot.get());
    }
  }

  public Optional<Position> report(UUID id) {
    Optional<Robot> robot = dao.getRobotById(id);

    if (robot.isPresent()) {
      return Optional.of(robot.get().getPosition());
    }

    return Optional.empty();
  }

  public List<UUID> getAllRobotIds() {
    return dao.getAllRobotIds();
  }
}
