package com.wf.ojbackendjudgeservice.strategy;



/**
 * @Author 玉米排骨汤
 * @Date 2024/1/17 13:47
 * @Package com.yupi.oj.judge.strategy
 * @Version 1.0
 * @Since 1.0
 */

import com.wf.model.dto.questionsubmit.JudgeInfo;

/**
 * 判题策略
 */
public interface JudgeStrategy {

    JudgeInfo doJudge(JudgeContext judgeContext);

}
