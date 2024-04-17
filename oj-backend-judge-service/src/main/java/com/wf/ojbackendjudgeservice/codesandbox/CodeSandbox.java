package com.wf.ojbackendjudgeservice.codesandbox;


import com.wf.model.codesandbox.ExecuteCodeRequest;
import com.wf.model.codesandbox.ExecuteCodeResponse;

/**
 * @Author 玉米排骨汤
 * @Date 2024/1/14 18:18
 * @Package com.yupi.oj.judge.codesandbox
 * @Version 1.0
 * @Since 1.0
 */
public interface CodeSandbox {

    /**
     * 执行代码
     * @param executeCodeRequest
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);

}
