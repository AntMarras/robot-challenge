package com.challenge.robot.service;

import com.challenge.robot.model.Direction;
import com.challenge.robot.model.Face;
import org.springframework.stereotype.Component;

import static com.challenge.robot.model.Face.*;

@Component
public class Rotator {

  public Face rotate(Face face, Direction direction) {
    Face result;
    switch (direction) {
      case LEFT:
        result = rotateLeft(face);
        break;
      case RIGHT:
        result = rotateRight(face);
        break;
      default:
        throw new IllegalStateException("Unsupported direction " + direction);
    }
    return result;
  }

  private Face rotateRight(Face face) {
    switch (face) {
      case NORTH:
        return EAST;
      case EAST:
        return SOUTH;
      case SOUTH:
        return WEST;
      case WEST:
        return NORTH;
      default:
        throw new RuntimeException();
    }
  }

  private Face rotateLeft(Face face) {
    switch (face) {
      case NORTH:
        return WEST;
      case EAST:
        return NORTH;
      case SOUTH:
        return EAST;
      case WEST:
        return SOUTH;
      default:
        throw new RuntimeException();
    }
  }
}
