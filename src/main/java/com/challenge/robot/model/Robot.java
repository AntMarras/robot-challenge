package com.challenge.robot.model;

import java.util.UUID;

public class Robot {

  private UUID id;
  private Position position;

  public Robot(Position position) {
    this.id = UUID.randomUUID();
    this.position = position;
  }

  public Robot(UUID id, Position position) {
    this.id = id;
    this.position = position;
  }

  public UUID getId() {
    return id;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }
}
