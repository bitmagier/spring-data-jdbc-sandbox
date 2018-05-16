package org.purevalue;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.purevalue.ddl.Tables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

@Configuration
@ComponentScan
@EnableJdbcRepositories
@EnableTransactionManagement
public class Application {
    private ComboPooledDataSource c3p0;

    @Autowired
    private Tables tables;

    public static void main(String[] args) {
        final ApplicationContext context =
                new AnnotationConfigApplicationContext(Application.class);

        final Bean1 bean = context.getBean(Bean1.class);
        bean.insertCar();
        bean.listCarsByRepoFunction();
        bean.listCarsByQuery();
    }

    @Bean(name = "transactionManager")
    PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

//    @Bean
//    DataSource dataSource() {
//        final JdbcDataSource ds = new JdbcDataSource();
//        ds.setURL("jdbc:h2:/tmp/h2test");
//        return ds;
//    }


    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        c3p0 = new ComboPooledDataSource();
        c3p0.setDriverClass("org.postgresql.Driver" );
        c3p0.setJdbcUrl("jdbc:postgresql://localhost/postgres?currentSchema=spring");
        c3p0.setUser("spring");
        c3p0.setPassword("spring");
        return c3p0;
    }

    @Bean
    NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @PostConstruct
    void postConstruct() throws SQLException {
        tables.create();
    }

    @PreDestroy
    public void preDestroy() {
        if (c3p0 != null) {
            c3p0.close();
        }
    }
}
