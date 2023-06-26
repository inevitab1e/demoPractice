package com.sisp;


import com.sisp.beans.HttpResponseEntity;
import com.sisp.controller.AnswerController;
import com.sisp.dao.entity.AnswerEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest(classes = DemoPracticeApplication.class)
public class AnswerTests {
    @Autowired
    private AnswerController answerController;

    private String answerId;

    private Logger log = LogManager.getLogger(AnswerTests.class);

    @BeforeEach
    public void setup() {
        answerId = UUID.randomUUID().toString();
    }

    @Test
    public void testInsertAnswer() {
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setId(answerId);
        answerEntity.setQuestionnaireId("SomeQuestionnaireId");
        answerEntity.setRoleId("SomeRoleId");
        answerEntity.setAnswerStatus("SomeAnswerStatus");
        answerEntity.setAnswer("SomeAnswer");

        HttpResponseEntity httpResponseEntity = answerController.insert(answerEntity);

        log.info("---创建回答结果---");
        log.info("Code: " + httpResponseEntity.getCode());
        log.info("Message: " + httpResponseEntity.getMessage());
    }

    @Test
    public void testListAnswers() {
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setQuestionnaireId("SomeQuestionnaireId");

        HttpResponseEntity httpResponseEntity = answerController.list(answerEntity);

        log.info("---查看回答结果---");
        log.info("Code: " + httpResponseEntity.getCode());
        log.info("Data: " + httpResponseEntity.getData());
    }

    @AfterEach
    public void cleanup() {
        // If your AnswerMapper has a delete method, you can call it here to clean up after each test
    }
}
