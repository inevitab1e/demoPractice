package com.sisp;

import com.sisp.beans.HttpResponseEntity;
import com.sisp.controller.QuestionController;
import com.sisp.dao.entity.QuestionEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest(classes = DemoPracticeApplication.class)
public class QuestionTests {
    @Autowired
    private QuestionController questionController;

    private String questionId;

    private Logger log = LogManager.getLogger(QuestionTests.class);

    @BeforeEach
    public void setup() {
        questionId = UUID.randomUUID().toString();
    }

    @Test
    public void testInsertQuestion() {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setId(questionId);
        questionEntity.setQuestionnaireId("SomeQuestionnaireId");
        questionEntity.setQuestionName("SomeQuestionName");
        questionEntity.setQuestionContent("SomeQuestionContent");
        questionEntity.setQuestionType("1");
        questionEntity.setQuestionOption("SomeQuestionOption");

        HttpResponseEntity httpResponseEntity = questionController.insert(questionEntity);

        log.info("---创建问题结果---");
        log.info("Code: " + httpResponseEntity.getCode());
        log.info("Data: " + httpResponseEntity.getData());
    }

    @Test
    public void testListQuestions() {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setQuestionnaireId("SomeQuestionnaireId");

        HttpResponseEntity httpResponseEntity = questionController.list(questionEntity);

        log.info("---查看问题结果---");
        log.info("Code: " + httpResponseEntity.getCode());
        log.info("Data: " + httpResponseEntity.getData());
    }

    @Test
    public void testUpdateQuestion() {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setId(questionId);
        questionEntity.setQuestionName("UpdatedQuestionName");

        HttpResponseEntity httpResponseEntity = questionController.update(questionEntity);

        log.info("---更新问题结果---");
        log.info("Code: " + httpResponseEntity.getCode());
    }

    @AfterEach
    public void cleanup() {
        if (questionId != null) {
            QuestionEntity questionEntity = new QuestionEntity();
            questionEntity.setId(questionId);
            HttpResponseEntity httpResponseEntity = questionController.delete(questionEntity);

            log.info("---删除问题结果---");
            log.info("Code: " + httpResponseEntity.getCode());
        }
    }
}

