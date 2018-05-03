package com.z1max.lunchvotingsystem.service;

import com.z1max.lunchvotingsystem.config.AppConfig;
import com.z1max.lunchvotingsystem.config.PersistenceConfig;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration(classes = {PersistenceConfig.class, AppConfig.class})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/insert-data.sql", config = @SqlConfig(encoding = "UTF-8"))
public abstract class AbstractServiceTest {

    private static final Logger log = LoggerFactory.getLogger("result");

    @Rule
    public ExpectedException thrown = ExpectedException.none();
}
