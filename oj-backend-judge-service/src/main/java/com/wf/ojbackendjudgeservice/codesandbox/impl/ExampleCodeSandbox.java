package com.wf.ojbackendjudgeservice.codesandbox.impl;

import com.wf.model.dto.questionsubmit.JudgeInfo;
import com.wf.model.enums.JudgeInfoMessageEnum;
import com.wf.model.enums.QuestionSubmitStatusEnum;
import com.wf.ojbackendjudgeservice.codesandbox.CodeSandbox;
import com.wf.model.codesandbox.ExecuteCodeRequest;
import com.wf.model.codesandbox.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Author 玉米排骨汤
 * @Date 2024/1/14 18:28
 * @Package com.yupi.oj.judge.codesandbox.impl
 * @Version 1.0
 * @Since 1.0
 */

@Slf4j
public class ExampleCodeSandbox implements CodeSandbox {


    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
        String code = executeCodeRequest.getCode();
        String language = executeCodeRequest.getLanguage();

        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setTimeLimit(100L);
        judgeInfo.setMemoryLimit(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }
}
