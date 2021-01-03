package service;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/** The service is used to send messages to the user */
public interface IMessageService {
  /**
   * The method sends a message to the user
   *
   * @param chatId chat id where you want to send a message
   * @param message message body to send
   * @throws TelegramApiException in case of exceptions while sending
   */
  void sendMessageUser(final String chatId, final String message) throws TelegramApiException;
}
