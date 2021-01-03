package service.impl;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicHeader;

public class AbstractRestClient {

  protected HttpGet createHttpGet(final String url) {
    HttpGet httpGet = new HttpGet(url);
    httpGet.setHeaders(getHeaders());
    return httpGet;
  }

  private Header[] getHeaders() {
    return new Header[] {
      new BasicHeader("Accept", "application/json"),
      new BasicHeader("Content-type", "application/json"),
    };
  }
}
