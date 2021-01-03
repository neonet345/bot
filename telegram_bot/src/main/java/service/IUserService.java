package service;

/** The service processes messages received from the user */
public interface IUserService {
  /**
   * Processes messages received from the user. Determines the type of command and executes the
   * user's request, and then returns the response to him
   *
   * @param chatId chat id from which the request came
   * @param message message from user
   */
  void processMessagesFromUser(final String chatId, final String message);
}
