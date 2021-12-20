package com.gustavoboliveira.desafiotecnico.dataanalysissystem.runners;

import com.gustavoboliveira.desafiotecnico.dataanalysissystem.DataAnalysisSystemApplication;
import com.gustavoboliveira.desafiotecnico.dataanalysissystem.services.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements ApplicationRunner {

    @Autowired
    private SystemService service;

    private static Logger logger = LoggerFactory.getLogger(DataAnalysisSystemApplication.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(!service.buildReport())
            logger.info("Failed to generate report");
    }
}
