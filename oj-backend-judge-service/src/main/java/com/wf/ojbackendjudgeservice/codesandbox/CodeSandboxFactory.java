package com.wf.ojbackendjudgeservice.codesandbox;

/**
 * @Author 玉米排骨汤
 * @Date 2024/1/14 18:34
 * @Package com.yupi.oj.judge.codesandbox
 * @Version 1.0
 * @Since 1.0
 */


import com.wf.ojbackendjudgeservice.codesandbox.impl.ExampleCodeSandbox;
import com.wf.ojbackendjudgeservice.codesandbox.impl.RemoteCodeSandbox;
import com.wf.ojbackendjudgeservice.codesandbox.impl.ThirdPartyCodeSandbox;

/**
 * 代码沙箱工厂
 */
public class CodeSandboxFactory {

    public static CodeSandbox newInstance(String type){
        switch(type){
            case "example":
                return new ExampleCodeSandbox();
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdParty":
                return new ThirdPartyCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }

}
