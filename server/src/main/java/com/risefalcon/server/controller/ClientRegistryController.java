package com.risefalcon.server.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@RequestMapping("/client_registry")
public interface ClientRegistryController {

    /**
     *
     * @param jsonObject key       | value
     *
     * @return
     */
    @PostMapping("/")
    String register(@RequestBody JSONObject jsonObject);




}
