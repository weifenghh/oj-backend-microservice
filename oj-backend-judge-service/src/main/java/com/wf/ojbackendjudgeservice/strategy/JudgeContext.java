package com.wf.ojbackendjudgeservice.strategy;

/**
 * @Author 玉米排骨汤
 * @Date 2024/1/17 13:49
 * @Package com.yupi.oj.judge.strategy
 * @Version 1.0
 * @Since 1.0
 */


import com.wf.model.dto.question.JudgeCase;
import com.wf.model.dto.questionsubmit.JudgeInfo;
import com.wf.model.entity.Question;
import com.wf.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 判题上下文
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;
    private List<String> inputList;
    private List<String> outputList;
    private List<JudgeCase> judgeCaseList;
    private Question question;
    private QuestionSubmit questionSubmit;

}
