package com.example.demo2.config;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.h2.H2ConnectionOption;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@Profile("h2")
@EnableR2dbcRepositories
@Configuration
public class R2dbcConfig extends AbstractR2dbcConfiguration {

    @Override
    public ConnectionFactory connectionFactory() {
        return new H2ConnectionFactory(H2ConnectionConfiguration.builder()
                .inMemory("test") // 데이터베이스 이름
                .property(H2ConnectionOption.DB_CLOSE_DELAY, "-1") // DB연결이 닫혀도 유지되도록 설정
                .username("sa")
                .build());
    }



    /** 해당 schema.sql을 h2-console을 이용해서 확인하기 위한 method ,
     * jdbc기반의 h2database이므로 변경되는 데이터들은 반영되어지지 않는다.
     * 기동시 생성되는 테이블과 데이터 확인용 **/
    @Bean
    public ConnectionFactoryInitializer h2DbInitializer() {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();

        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();

        resourceDatabasePopulator.addScript(new ClassPathResource("schema.sql"));

        initializer.setConnectionFactory(connectionFactory());
        initializer.setDatabasePopulator(resourceDatabasePopulator);
        return initializer;
    }
}
