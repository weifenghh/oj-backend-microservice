package com.wf.ojbackenduserservice.controller.inner;

import com.wf.model.entity.User;
import com.wf.ojbackendserviceclient.service.UserFeignClient;
import com.wf.ojbackenduserservice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @Author 玉米排骨汤
 * @Date 2024/4/18 10:42
 * @Package com.wf.ojbackenduserservice.controller.inner
 * @Version 1.0
 * @Since 1.0
 */
@RestController
@RequestMapping("/inner")
public class UserInnerController implements UserFeignClient {

    @Resource
    private UserService userService;


    @Override
    @GetMapping("/getUser/id")
    public User getById(@RequestParam("userId") long userId) {
        return userService.getById(userId);
    }

    @GetMapping("/getUser/ids")
    @Override
    public List<User> listByIds(@RequestParam("idList") Collection<Long> idList) {
        return userService.listByIds(idList);
    }
}
