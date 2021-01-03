package utill;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;

/** Class for converting to pojo */
public class Converter {

  private static final ObjectMapper objectMapper = new ObjectMapper();
  private static final Gson gson = new Gson();

  /** Method converts from string to pojo object */
  public static <T> T convertToPojo(String message, Class<T> valueType) throws IOException {
    try {
      return objectMapper.readValue(message, valueType);
    } catch (IOException e) {
      System.out.println(e);
      throw new IOException("error converts");
    }
  }

  /** convert object to json */
  public static String convertToJson(Object object) {
    return gson.toJson(object);
  }
}
