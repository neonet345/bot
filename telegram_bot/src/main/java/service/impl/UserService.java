package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.*;

import static service.IUserComand.*;

@Service
public class UserService extends AbstractUserService implements IUserService {

  @Autowired
  public UserService(
      final IMessageService messageService,
      final IRestClientService restClientService,
      final IValidateService validateService) {
    super(messageService, restClientService, validateService);
  }

  @Override
  public void processMessagesFromUser(final String chatId, final String message) {
    if (isValidMessage(message)) {
      processCommand(message, chatId);
    } else {
      sendMessage(chatId, messageInvalidCommand);
    }
  }

  private void processCommand(String message, String chatId) {
    switch (message) {
      case START:
        {
          sendMessage(chatId, messageCommandStart);
          break;
        }
      case HELP:
        {
          sendMessage(chatId, messageCommandHelp);
          break;
        }
      case GET_ALL_CITIES:
        {
          sendMessage(chatId, getAllCities());
          break;
        }
      default:
        {
          sendMessage(chatId, getDescriptionCity(message));
          break;
        }
    }
  }
}
