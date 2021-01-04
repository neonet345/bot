package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** The model is used to convey a description of the city */
@Getter
@Setter
@AllArgsConstructor
public class ResponseMessage {
  public ResponseMessage() {}

  private String message;
}
