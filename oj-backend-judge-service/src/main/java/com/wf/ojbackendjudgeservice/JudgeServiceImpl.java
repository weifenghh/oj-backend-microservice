package com.wf.ojbackendjudgeservice;

import cn.hutool.json.JSONUtil;

import com.wf.common.exception.BusinessException;
import com.wf.common.model.ErrorCode;
import com.wf.model.dto.question.JudgeCase;
import com.wf.model.dto.questionsubmit.JudgeInfo;
import com.wf.model.entity.Question;
import com.wf.model.entity.QuestionSubmit;
import com.wf.model.enums.QuestionSubmitStatusEnum;
import com.wf.ojbackendjudgeservice.codesandbox.CodeSandbox;
import com.wf.ojbackendjudgeservice.codesandbox.CodeSandboxFactory;
import com.wf.ojbackendjudgeservice.codesandbox.CodeSandboxProxy;
import com.wf.model.codesandbox.ExecuteCodeRequest;
import com.wf.model.codesandbox.ExecuteCodeResponse;
import com.wf.ojbackendjudgeservice.strategy.JudgeContext;
import com.wf.ojbackendserviceclient.service.JudgeFeignClient;
import com.wf.ojbackendserviceclient.service.QuestionFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author 玉米排骨汤
 * @Date 2024/1/17 14:49
 * @Package com.yupi.oj.judge
 * @Version 1.0
 * @Since 1.0
 */

@Service
public class JudgeServiceImpl implements JudgeService {

    @Resource
    private QuestionFeignClient questionFeignClient;
    @Value("${codesandbox.type:example}")
    private String type;

    @Resource
    private JudgeManager judgeManager;

    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {
        //根据题目提交Id查询提交的题目
        QuestionSubmit questionSubmit = questionFeignClient.getQuestionSubmitById(questionSubmitId);
        if(questionSubmit == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"提交信息不存在");
        }
        //获取题目提交中的题目Id，查询题目
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionFeignClient.getQuestionById(questionId);
        if(question == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"题目不存在");
        }
        //判断题目状态是否再判题
        if(!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"题目正在判题中");
        }
        //如果不在判题，则重新设置题目提交的状态为运行
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean flag = questionFeignClient.updateQuestionSubmitById(questionSubmitUpdate);
        if(!flag){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"题目状态更新失败");
        }

        //代码沙箱判题
        //根据工厂模式获取想要的判题提沙箱
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        //沙箱的增强
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        //获取到判题用例，转化为java对象，使用stream流取出里面的输入用例
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputList = judgeCaseList
                .stream()
                .map(JudgeCase::getInput)
                .collect(Collectors.toList());

        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        List<String> outputList = executeCodeResponse.getOutputList();

        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);
//        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
//        JudgeInfo judgeInfo = judgeStrategy.doJudge(judgeContext);

        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        flag = questionFeignClient.updateQuestionSubmitById(questionSubmitUpdate);
        if(!flag){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"题目状态更新失败");
        }
        QuestionSubmit questionSubmitResult = questionFeignClient.getQuestionSubmitById(questionId);
        return questionSubmitResult;
    }
}
