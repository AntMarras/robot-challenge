package com.challenge.robot.dao;

import com.challenge.robot.model.Face;
import com.challenge.robot.model.Position;
import com.challenge.robot.model.Robot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RobotDao {

  private JdbcTemplate jdbcTemplate;

  @Autowired
  public RobotDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void create(Robot robot) {
    jdbcTemplate.update("insert into robot (robotId, x, y, f) values (?, ?, ?, ?)",
      robot.getId().toString(),
      robot.getPosition().getX(),
      robot.getPosition().getY(),
      robot.getPosition().getFace().toString());
  }

  public Optional<Robot> getRobotById(UUID id) {
    List<Robot> result = jdbcTemplate.query("select x, y, f from robot where robotId=?", (rs, rowNum) -> {
      int x = rs.getInt("x");
      int y = rs.getInt("y");
      Face f = Face.valueOf(rs.getString("f").trim());

      return new Robot(id, new Position(x, y, f));

    }, id.toString());

    return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
  }

  public void updateRobot(Robot robot) {
    jdbcTemplate.update("update robot set x=?, y=?, f=? where robotId = ?",
      robot.getPosition().getX(),
      robot.getPosition().getY(),
      robot.getPosition().getFace().toString(),
      robot.getId().toString());
  }

  public List<UUID> getAllRobotIds() {
    return jdbcTemplate.query("select robotId from robot", (rs, rowNum) -> rs.getString("robotId"))
      .stream()
      .map(UUID::fromString)
      .collect(Collectors.toList());
  }
}
