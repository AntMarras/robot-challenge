package com.challenge.robot.rest;

import com.challenge.robot.model.Position;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReportTranslator {

  public Report translate(@SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<Position> position) {
    if (position.isPresent()) {
      return new Report(
        " " + position.get().getX() + ", "
          + position.get().getY() + ", "
          + position.get().getFace().toString());
    }

    return new Report("Robot missing");
  }

}
