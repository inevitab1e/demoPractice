package com.sisp;

import com.sisp.beans.HttpResponseEntity;
import com.sisp.controller.ProjectController;
import com.sisp.dao.entity.ProjectEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.UUID;
@SpringBootTest
public class ProjectTests {
    @Resource
    private ProjectController projectController;

    private String projectId;

    private Logger log = LogManager.getLogger(ProjectTests.class);

    @BeforeEach
    public void setup() {
        projectId = UUID.randomUUID().toString();
    }

    @Test
    public void testAddProjectInfo() {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(projectId);
        projectEntity.setUserId("SomeUserId");
        projectEntity.setProjectName("SomeProjectName");
        projectEntity.setProjectContent("SomeProjectContent");
        projectEntity.setCreatedBy("TestUser");

        HttpResponseEntity httpResponseEntity = projectController.addProjectInfo(projectEntity);

        log.info("---添加项目结果---");
        log.info("Code: " + httpResponseEntity.getCode());
        log.info("Data: " + httpResponseEntity.getData());
        log.info("Message: " + httpResponseEntity.getMessage());
    }

    @Test
    public void testQueryProjectList() {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setUserId("SomeUserId");

        HttpResponseEntity httpResponseEntity = projectController.queryProjectList(projectEntity);

        log.info("---查询项目列表结果---");
        log.info("Code: " + httpResponseEntity.getCode());
        log.info("Data: " + httpResponseEntity.getData());
        log.info("Message: " + httpResponseEntity.getMessage());
    }

    @Test
    public void testModifyProjectInfo() {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(projectId);
        projectEntity.setProjectName("UpdatedProjectName");

        HttpResponseEntity httpResponseEntity = projectController.modifyProjectInfo(projectEntity);

        log.info("---修改项目结果---");
        log.info("Code: " + httpResponseEntity.getCode());
        log.info("Data: " + httpResponseEntity.getData());
        log.info("Message: " + httpResponseEntity.getMessage());
    }

    @AfterEach
    public void cleanup() {
        if (projectId != null) {
            ProjectEntity projectEntity = new ProjectEntity();
            projectEntity.setId(projectId);
            HttpResponseEntity httpResponseEntity = projectController.deleteProject(projectEntity);

            log.info("---删除项目结果---");
            log.info("Code: " + httpResponseEntity.getCode());
            log.info("Data: " + httpResponseEntity.getData());
            log.info("Message: " + httpResponseEntity.getMessage());
        }
    }
}
