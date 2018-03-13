package com.challenge.robot.model;

public class Position {
  private int x;
  private int y;
  private Face face;

  public Position(int x, int y, Face face) {
    this.x = x;
    this.y = y;
    this.face = face;
  }

  public int getY() {
    return y;
  }

  public Face getFace() {
    return face;
  }

  public void setFace(Face face) {
    this.face = face;
  }

  public int getX() {
    return x;
  }

}
