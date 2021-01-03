package service;

import java.io.IOException;

/** The service is designed to send requests to the server */
public interface IRestClientService {
  /**
   * The method sends a get request to the server
   *
   * @param url url request
   * @return response from server
   * @throws IOException in case of an error when sending a request
   */
  String getRequest(String url) throws IOException;
}
