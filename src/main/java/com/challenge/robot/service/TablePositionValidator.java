package com.challenge.robot.service;

import com.challenge.robot.model.Position;
import org.springframework.stereotype.Component;

@Component
public class TablePositionValidator {

  private static final int X_MAX = 5;
  private static final int Y_MAX = 5;

  public boolean validate(Position position) {
    int x = position.getX();
    int y = position.getY();

    return x < X_MAX && x >= 0 && y < Y_MAX && y >= 0;
  }
}
