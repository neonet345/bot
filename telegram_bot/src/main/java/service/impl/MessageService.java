package service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.IMessageService;
import ui.UInterface;

@Slf4j
@Service
public class MessageService extends DefaultAbsSender implements IMessageService {

  @Value("${bot.token}")
  private String botToken;

  public MessageService() {
    super(new DefaultBotOptions());
  }

  protected MessageService(final DefaultBotOptions options) {
    super(options);
  }

  @Override
  public void sendMessageUser(final String chatId, final String message)
      throws TelegramApiException {
    SendMessage sendMessage = UInterface.getInterfaceMessage();
    sendMessage.setText(message);
    sendMessage.setChatId(chatId);
    execute(sendMessage);
  }

  @Override
  public String getBotToken() {
    return botToken;
  }
}
