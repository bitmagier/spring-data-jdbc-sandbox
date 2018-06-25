package org.purevalue.ddl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class Tables {
    private static final Logger log = LoggerFactory.getLogger(Tables.class);

    private final DataSource dataSource;

    @Autowired
    public Tables(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void create() {
        try (Connection c = dataSource.getConnection()) {
            c.setAutoCommit(false);
            executeSQL(c, "drop table if exists car cascade;" +
                    "create table car(\n" +
                    " id bigserial not null,\n" +
                    " brand varchar,\n" +
                    " model varchar\n" +
                    ");");
            log.info("Initial DDL applied");
        } catch (SQLException e) {
            log.warn("Error {} during initial DDL: {}", e.getErrorCode(), e.getMessage());
        }
    }

    private void executeSQL(Connection c, String sql) throws SQLException {
        try (PreparedStatement pStmt = c.prepareStatement(sql)) {
            pStmt.execute();
            c.commit();
        }
    }
}
