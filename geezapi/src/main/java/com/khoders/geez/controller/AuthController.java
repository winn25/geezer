package com.khoders.geez.controller;

import com.khoders.geez.mapper.ApiEndpoint;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Authentication - Endpoint")
@RequestMapping(ApiEndpoint.AUTH_ENDPOINT)
public class AuthController {

}
