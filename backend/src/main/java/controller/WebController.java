package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** BE start page */
@RestController
public class WebController {

  /** Info page - about launching be */
  @RequestMapping(value = "/")
  public String index() {
    return "Start BE";
  }
}
