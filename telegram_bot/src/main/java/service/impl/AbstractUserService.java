package service.impl;

import lombok.extern.slf4j.Slf4j;
import model.ResponseMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.IMessageService;
import service.IRestClientService;
import service.IValidateService;
import utill.Converter;

import java.io.IOException;

import static service.URL.URL_GET_ALL_CITIES;
import static service.URL.URL_GET_CITY_DESCRIPTION;

@Slf4j
@Component
public class AbstractUserService {

  @Value("${message.for.command.help}")
  protected String messageCommandHelp;

  @Value("${message.for.command.start}")
  protected String messageCommandStart;

  @Value("${message.invalid.command}")
  protected String messageInvalidCommand;

  @Value("${message.no.descriptionCity}")
  protected String messageNoResponseBE;

  @Value("${message.error.request.backend}")
  protected String messageErrorRequestBackend;

  private final IMessageService messageService;
  private final IRestClientService restClientService;
  private final IValidateService validateService;

  AbstractUserService(
      final IMessageService messageService,
      final IRestClientService restClientService,
      final IValidateService validateService) {
    this.messageService = messageService;
    this.restClientService = restClientService;
    this.validateService = validateService;
  }

  protected String getDescriptionCity(final String cityName) {
    try {
      log.info("request to the server to get a description of the city {}", cityName);
      return formResponseUser(
          Converter.convertToPojo(
                  getRequest(String.format(URL_GET_CITY_DESCRIPTION, cityName)),
                  ResponseMessage.class)
              .getMessage());
    } catch (IOException e) {
      log.error(e.getMessage());
      return messageErrorRequestBackend;
    }
  }

  protected String getAllCities() {
    try {
      log.info("Request to receive all cities for which information is available");
      return formResponseUser(getRequest(URL_GET_ALL_CITIES));
    } catch (IOException e) {
      log.error(e.getMessage());
      return messageErrorRequestBackend;
    }
  }

  protected void sendMessage(final String chatId, final String message) {
    try {
      log.info("sending a message {} to the user {}", message, chatId);
      messageService.sendMessageUser(chatId, message);
    } catch (TelegramApiException e) {
      log.error(e.getMessage());
    }
  }

  protected boolean isValidMessage(final String message) {
    log.info("check message {}", message);
    return validateService.isValidMessage(message);
  }

  private String formResponseUser(final String message) {
    if (validateService.isValidResponse(message)) {
      return message;
    }
    return messageNoResponseBE;
  }

  private String getRequest(final String url) throws IOException {
    return restClientService.getRequest(url);
  }
}
