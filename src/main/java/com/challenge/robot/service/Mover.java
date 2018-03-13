package com.challenge.robot.service;

import com.challenge.robot.model.Position;
import org.springframework.stereotype.Component;

@Component
public class Mover {

  public Position move(Position currentPosition) {
    int newX = currentPosition.getX();
    int newY = currentPosition.getY();

    switch (currentPosition.getFace()) {
      case NORTH:
        newY++;
        break;
      case SOUTH:
        newY--;
        break;
      case EAST:
        newX++;
        break;
      case WEST:
        newX--;
        break;
    }

    return new Position(newX, newY, currentPosition.getFace());
  }
}
