package com.wf.ojbackendserviceclient.service;


import com.wf.model.entity.QuestionSubmit;

/**
 * @Author 玉米排骨汤
 * @Date 2024/1/16 15:09
 * @Package com.yupi.oj.judge.codesandbox
 * @Version 1.0
 * @Since 1.0
 */
public interface JudgeService {

    QuestionSubmit doJudge(long questionSubmitId);


}
