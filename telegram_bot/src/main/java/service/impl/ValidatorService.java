package service.impl;

import org.springframework.stereotype.Service;
import service.IUserComand;
import service.IValidateService;

@Service
public class ValidatorService implements IValidateService {

  private final Integer MAX_SIZE_MESSAGE = 90;
  private final Integer MIN_SIZE_MESSAGE = 3;

  @Override
  public boolean isValidMessage(final String message) {
    if (isCommand(message)) {
      return isExistsCommand(message);
    }
    return isSize(message);
  }

  @Override
  public boolean isValidResponse(final String message) {
    return !message.equals("") && !message.equals("[]");
  }

  private boolean isSize(final String message) {
    return message.length() >= MIN_SIZE_MESSAGE && message.length() <= MAX_SIZE_MESSAGE;
  }

  private boolean isExistsCommand(final String message) {
    return IUserComand.HELP.equals(message.toLowerCase())
        || IUserComand.GET_ALL_CITIES.equals(message.toLowerCase())
        || IUserComand.START.equals(message.toLowerCase());
  }

  private boolean isCommand(final String message) {
    return message.startsWith("/");
  }
}
