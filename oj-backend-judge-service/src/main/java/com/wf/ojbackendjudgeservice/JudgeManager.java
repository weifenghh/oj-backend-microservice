package com.wf.ojbackendjudgeservice;

import com.wf.model.dto.questionsubmit.JudgeInfo;
import com.wf.model.entity.QuestionSubmit;
import com.wf.ojbackendjudgeservice.strategy.DefaultJudgeStrategy;
import com.wf.ojbackendjudgeservice.strategy.JavaLanguageJudgeStrategy;
import com.wf.ojbackendjudgeservice.strategy.JudgeContext;
import com.wf.ojbackendjudgeservice.strategy.JudgeStrategy;
import org.springframework.stereotype.Service;

/**
 * @Author 玉米排骨汤
 * @Date 2024/1/17 14:43
 * @Package com.yupi.oj.judge
 * @Version 1.0
 * @Since 1.0
 */
@Service
public class JudgeManager {

    JudgeInfo doJudge(JudgeContext judgeContext){
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if("java".equals(language)){
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}
