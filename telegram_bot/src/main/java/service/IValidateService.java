package service;

/** The service validates messages received from the user and the server */
public interface IValidateService {
  /**
   * The method checks the message received from the user
   *
   * @param message message from user
   * @return result check
   */
  boolean isValidMessage(String message);
  /**
   * The method checks the message received from the server
   *
   * @param response from server
   * @return result check
   */
  boolean isValidResponse(String response);
}
