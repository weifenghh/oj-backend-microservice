package com.wf.ojbackendquestionservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wf.model.entity.QuestionSubmit;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【question_submit(题目提交表)】的数据库操作Mapper
* @createDate 2023-12-19 19:43:57
* @Entity com.yupi.oj.model.entity.QuestionSubmit
*/
@Mapper
public interface QuestionSubmitMapper extends BaseMapper<QuestionSubmit> {

}




