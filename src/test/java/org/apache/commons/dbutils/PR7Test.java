/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.dbutils;

import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PR7Test {
    private Connection setupDb() throws Exception {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:");
        Statement stmt = conn.createStatement();
        String sql =  "create table testtable (id integer auto_increment, col1 varchar(8), col2 varchar(8), primary key (id))";
        stmt.executeUpdate(sql);
        return conn;
    }

    @Test
    public void testColumnListHandler() throws Exception {
        QueryRunner runner = new QueryRunner();
        try (Connection conn = setupDb()) {
            final ColumnListHandler<Integer> handler = new ColumnListHandler<>(1);

            final Object[][] params = {{"col1row1", "col2row1"}, {"col1row2", "col2row2"}};
            final List<Integer> ids = runner.insertBatch(conn, "insert into testtable (col1, col2) values (?, ?)", handler, params);

            assertEquals(params.length, ids.size());
            assertEquals(1, ids.get(0).intValue());
            assertEquals(2, ids.get(1).intValue());
        }
    }
}
