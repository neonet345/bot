package service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import service.IRestClientService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class RestClientService extends AbstractRestClient implements IRestClientService {

  @Value("${host}")
  private String host;

  private final CloseableHttpClient httpclient = HttpClients.createDefault();

  @Override
  public String getRequest(final String url) throws IOException {
    log.info("request {}", url);
    try {
      return EntityUtils.toString(
          httpclient.execute(createHttpGet(host + url)).getEntity(), StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new IOException("Network error");
    }
  }
}
