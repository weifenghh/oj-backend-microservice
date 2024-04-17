package com.wf.ojbackendjudgeservice.codesandbox.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

import com.wf.common.exception.BusinessException;
import com.wf.ojbackendjudgeservice.codesandbox.CodeSandbox;
import com.wf.model.codesandbox.ExecuteCodeRequest;
import com.wf.model.codesandbox.ExecuteCodeResponse;
import org.apache.commons.lang3.StringUtils;

import static com.wf.common.model.ErrorCode.API_REQUEST_ERROR;


/**
 * @Author 玉米排骨汤
 * @Date 2024/1/14 18:29
 * @Package com.yupi.oj.judge.codesandbox.impl
 * @Version 1.0
 * @Since 1.0
 */
public class RemoteCodeSandbox implements CodeSandbox {

    //定义鉴权请求头和密钥
    private static final String AUTH_REQUEST_HEADER = "auth";
    private static final String AUTH_REQUEST_SECRET = "secret";
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        String url = "http://localhost:8111/executeCode";
        String json = JSONUtil.toJsonStr(executeCodeRequest);
        String responseStr = HttpUtil.createPost(url)
                .header(AUTH_REQUEST_HEADER,AUTH_REQUEST_SECRET)
                .body(json)
                .execute()
                .body();
        if(StringUtils.isBlank(responseStr)){
            throw new BusinessException(API_REQUEST_ERROR,"executeCode remoteSandbox error" + responseStr);
        }
        return JSONUtil.toBean(responseStr,ExecuteCodeResponse.class);
    }
}
