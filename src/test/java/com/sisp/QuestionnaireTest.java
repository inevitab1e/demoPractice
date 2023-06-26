package com.sisp;

import com.sisp.beans.HttpResponseEntity;
import com.sisp.controller.QuestionnaireController;
import com.sisp.dao.entity.QuestionnaireEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class QuestionnaireTest {

    @Autowired
    private QuestionnaireController questionnaireController;

    static String questionnaireId;

    Logger log = LogManager.getLogger(QuestionnaireTest.class);

    @BeforeAll
    public static void setup() {
        questionnaireId = null;
    }

    @Test
    public void testCreateQuestionnaire() {
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setQuestionnaireName("Test Questionnaire");
        questionnaireEntity.setProjectId("17c605bcd1fa4c27aab5ba946ecd79f3");  // Set the project_id


        //... set other fields

        HttpResponseEntity httpResponseEntity = questionnaireController.insert(questionnaireEntity);

        log.info("---创建问卷结果---");
        log.info("Code: " + httpResponseEntity.getCode());
        log.info("Data: " + httpResponseEntity.getData());

        questionnaireId = (String)httpResponseEntity.getData();
    }

    @Test
    public void testListQuestionnaires() {
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();

        HttpResponseEntity httpResponseEntity = questionnaireController.list(questionnaireEntity);

        log.info("---查看问卷结果---");
        log.info("Code: " + httpResponseEntity.getCode());
        log.info("Data: " + httpResponseEntity.getData());
    }

    @Test
    public void testUpdateQuestionnaire() {
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setId(questionnaireId);
        questionnaireEntity.setQuestionnaireName("Updated Questionnaire");

        //... set other fields to update

        HttpResponseEntity httpResponseEntity = questionnaireController.update(questionnaireEntity);

        log.info("---更新问卷结果---");
        log.info("Code: " + httpResponseEntity.getCode());
    }

    @AfterEach
    public void cleanup() {
        if (questionnaireId != null) {
            QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
            questionnaireEntity.setId(questionnaireId);
            HttpResponseEntity httpResponseEntity = questionnaireController.delete(questionnaireEntity);

            log.info("---删除问卷结果---");
            log.info("Code: " + httpResponseEntity.getCode());
        }
    }
}
