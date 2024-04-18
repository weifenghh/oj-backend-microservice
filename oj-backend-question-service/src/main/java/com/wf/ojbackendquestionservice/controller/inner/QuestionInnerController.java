package com.wf.ojbackendquestionservice.controller.inner;

import com.wf.model.entity.Question;
import com.wf.model.entity.QuestionSubmit;
import com.wf.ojbackendquestionservice.service.QuestionService;
import com.wf.ojbackendquestionservice.service.QuestionSubmitService;
import com.wf.ojbackendserviceclient.service.QuestionFeignClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author 玉米排骨汤
 * @Date 2024/4/18 10:53
 * @Package com.wf.ojbackendquestionservice.controller.inner
 * @Version 1.0
 * @Since 1.0
 */
@RestController
@RequestMapping("/inner")
public class QuestionInnerController implements QuestionFeignClient {

    @Resource
    private QuestionService questionService;
    @Resource
    private QuestionSubmitService questionSubmitService;

    @GetMapping("/get/id")
    @Override
    public Question getQuestionById(@RequestParam("questionId") long questionId) {
        return questionService.getById(questionId);
    }

    @GetMapping("/question_submit/get/id")
    @Override
    public QuestionSubmit getQuestionSubmitById(@RequestParam("questionSubmitId") long questionSubmitId) {
        return questionSubmitService.getById(questionSubmitId);
    }

    @PostMapping("/question_submit/update")
    @Override
    public boolean updateQuestionSubmitById(@RequestBody QuestionSubmit questionSubmit) {
        return questionSubmitService.updateById(questionSubmit);
    }
}
