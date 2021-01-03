package ui;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

import static service.IUserComand.*;

public class UInterface {

  public static SendMessage getInterfaceMessage() {
    SendMessage sendMessage = new SendMessage();
    sendMessage.enableMarkdown(true);
    sendMessage.setReplyMarkup(getKeyboard());
    return sendMessage;
  }

  private static ReplyKeyboardMarkup getKeyboard(){
      ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
      replyKeyboardMarkup.setSelective(true);
      replyKeyboardMarkup.setResizeKeyboard(true);
      replyKeyboardMarkup.setOneTimeKeyboard(true);
      List<KeyboardRow> keyboard = new ArrayList<>();
      KeyboardRow keyboardFirstRow = new KeyboardRow();
      keyboardFirstRow.add(HELP);
      keyboardFirstRow.add(GET_ALL_CITIES);
      keyboard.add(keyboardFirstRow);
      replyKeyboardMarkup.setKeyboard(keyboard);
      return replyKeyboardMarkup;
  }
}
