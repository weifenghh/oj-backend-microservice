package com.wf.ojbackendjudgeservice.controller.inner;

import com.wf.model.entity.QuestionSubmit;
import com.wf.ojbackendjudgeservice.JudgeService;
import com.wf.ojbackendserviceclient.service.JudgeFeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author 玉米排骨汤
 * @Date 2024/4/18 11:00
 * @Package com.wf.ojbackendjudgeservice.controller.inner
 * @Version 1.0
 * @Since 1.0
 */
@RestController
@RequestMapping("/inner")
public class JudgeInnerController implements JudgeFeignClient {

    @Resource
    private JudgeService judgeService;

    @PostMapping("/do")
    @Override
    public QuestionSubmit doJudge(@RequestParam("questionSubmitId") long questionSubmitId) {
        return judgeService.doJudge(questionSubmitId);
    }
}
