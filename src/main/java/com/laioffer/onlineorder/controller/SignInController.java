package com.laioffer.onlineorder.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SignInController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    // we only process the failed login request here, if login successfully,
    // it will automatically redirect to home pag
    @RequestMapping("/login")
    public void processFailedLoginRequest(@RequestParam(value = "error") String error,
                                          HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Map<String, Object> result = new HashMap<>();

        result.put("message", "bad credentials");
        response.getOutputStream()
                .println(objectMapper.writeValueAsString(result));
    }
}
