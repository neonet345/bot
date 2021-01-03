package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import service.IUserService;

@Service
public class TelegramBot extends TelegramLongPollingBot {
  private IUserService userService;

  @Autowired
  public TelegramBot(final IUserService userService) {
    this.userService = userService;
  }

  @Value("${bot.name}")
  private String botUsername;

  @Value("${bot.token}")
  private String botToken;

  @Override
  public void onUpdateReceived(final Update update) {
    final String message = update.getMessage().getText().toLowerCase();
    final String chatId = update.getMessage().getChatId().toString();
    userService.processMessagesFromUser(chatId, message);
  }

  @Override
  public String getBotUsername() {
    return botUsername;
  }

  @Override
  public String getBotToken() {
    return botToken;
  }
}
