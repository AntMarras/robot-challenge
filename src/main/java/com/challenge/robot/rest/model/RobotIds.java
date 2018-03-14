package com.challenge.robot.rest.model;

import java.util.List;
import java.util.UUID;

public class RobotIds {
  private List<UUID> ids;

  public RobotIds(List<UUID> ids) {
    this.ids = ids;
  }

  public List<UUID> getIds() {
    return ids;
  }
}
