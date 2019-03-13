package ua.com.juja.sqlcmd;

import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by indigo on 21.08.2015.
 */
public class JDBCDatabaseManagerTest {

    private DatabaseManager manager;

    @Before
    public void setup() {
        manager = new JDBCDatabaseManager();
        manager.connect("sqlcmd", "postgres", "admin");
    }

    @Test
    public void testGetAllTableNames() {
        String[] tableNames = manager.getTableNames();
        assertEquals("[users, test]", Arrays.toString(tableNames));
    }

    @Test
    public void testGetTableData() {
        // given
        manager.clear("users");

        // when
        DataSet input = new DataSet();
        input.put("id", 13);
        input.put("name", "Stiven");
        input.put("password", "pass");
        manager.create(input);

        // then
        DataSet[] users = manager.getTableData("users");
        assertEquals(1, users.length);

        DataSet user = users[0];
        assertEquals("[id, name, password]", Arrays.toString(user.getNames()));
        assertEquals("[13, Stiven, pass]", Arrays.toString(user.getValues()));
    }

    @Test
    public void testUpdateTableData() {
        // given
        manager.clear("users");

        DataSet input = new DataSet();
        input.put("id", 13);
        input.put("name", "Stiven");
        input.put("password", "pass");
        manager.create(input);

        // when
        DataSet newValue = new DataSet();
        newValue.put("password", "pass2");
        newValue.put("name", "Pup");
        manager.update("users", 13, newValue);

        // then
        DataSet[] users = manager.getTableData("users");
        assertEquals(1, users.length);

        DataSet user = users[0];
        assertEquals("[id, name, password]", Arrays.toString(user.getNames()));
        assertEquals("[13, Pup, pass2]", Arrays.toString(user.getValues()));
    }
}
