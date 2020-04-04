package com.george.cloud.controller;

import com.george.cloud.entity.User;
import com.george.cloud.enums.CommonStateEnums;
import com.george.cloud.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * <p></p>
 *
 * @author George Chan
 * @version 1.0
 * @date 2020/4/5 0:00
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/user")
public class UserRibbonController {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${service-url.provider-service}")
    private String providerServiceUrl;

    @GetMapping("/{id}")
    public Result getUser(@PathVariable Long id) {
        return restTemplate.getForObject(providerServiceUrl + "/user/{1}", Result.class, id);
    }

    @GetMapping("/getByUsername")
    public Result getByUsername(@RequestParam String username) {
        return restTemplate.getForObject(providerServiceUrl + "/user/getByUsername?username={1}", Result.class, username);
    }

    @GetMapping("/getEntityByUsername")
    public Result getEntityByUsername(@RequestParam String username) {
        ResponseEntity<Result> entity = restTemplate.getForEntity(providerServiceUrl + "/user/getByUsername?username={1}", Result.class, username);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new Result(false, CommonStateEnums.ERROR.getCode(), CommonStateEnums.ERROR.getMessage());
        }
    }

    @PostMapping("/create")
    public Result create(@RequestBody User user) {
        return restTemplate.postForObject(providerServiceUrl + "/user/create", user, Result.class);
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        return restTemplate.postForObject(providerServiceUrl + "/user/update", user, Result.class);
    }

    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        return restTemplate.postForObject(providerServiceUrl + "/user/delete/{1}", null, Result.class, id);
    }
}
