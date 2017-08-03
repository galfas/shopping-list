package com.mjs.shopping.service.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

public class StatusController extends BaseController {

  @RequestMapping("/status")
  public ResponseEntity status() {
    return new ResponseEntity<>("ok", HttpStatus.OK);
  }
}
