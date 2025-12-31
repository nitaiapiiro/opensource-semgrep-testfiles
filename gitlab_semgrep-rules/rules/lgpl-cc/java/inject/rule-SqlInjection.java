// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
package injection;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import org.apache.torque.TorqueException;
import org.apache.torque.util.BasePeer;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import org.springframework.jdbc.core.JdbcTemplate;
import io.vertx.sqlclient.SqlClient;
import io.vertx.sqlclient.SqlConnection;
import javax.persistence.criteria.Root;
import javax.persistence.EntityManager;

import javax.sql.DataSource;
import java.sql.*;

public class SqlInjection {
    private static final String CLIENT_FIELDS = "client_id, client_secret, resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";

    private static final String DEFAULT_INSERT_STATEMENT = "insert into oauth_client_details (" + CLIENT_FIELDS + ")"
            + "values (?,?,?,?,?,?,?,?,?,?,?)";

    private JdbcTemplate jdbcTemplate;

    public class UserEntity {
        private Long id;
        private String test;

        public String getTest() {
            return test;
        }

        public void setTest(String test) {
            this.test = test;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    private static final PersistenceManagerFactory pmfInstance = JDOHelper
            .getPersistenceManagerFactory("transactions-optional");

    public static PersistenceManager getPM() {
        return pmfInstance.getPersistenceManager();
    }

    public void testOne(Connection c, String input) {
        String value = String.format("%s", input);
        String sql = "select * from Users where name = " + input;
        try (Statement statement = c.createStatement()) {
            // ruleid: java_inject_rule-SqlInjection
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println(resultSet);
        } catch (SQLException e) {

        }
    }

    public void testTwo(Connection c, String input) {
        try (Statement statement = c.createStatement()) {
            // ruleid: java_inject_rule-SqlInjection
            ResultSet resultSet = statement.executeQuery(String.format("select * from Users where name = %s", input));
            System.out.println(resultSet);
        } catch (SQLException e) {

        }
    }

    public void getAllFields(String tableName, Connection c) throws SQLException {
        // ruleid: java_inject_rule-SqlInjection
        ResultSet rs = c.createStatement().executeQuery(String.format("SELECT * FROM %s", tableName)); // False Negative
    }

    public void findAccountsById(String id, Connection c) throws SQLException {
        String sql = String.format("SELECT * FROM accounts WHERE id = '%s'", id);
        // ruleid:java_inject_rule-SqlInjection
        ResultSet rs = c.createStatement().executeQuery(sql);
    }

    public void findAccountsByIdHardCoded(Connection c) throws SQLException {
        String sql = String.format("SELECT * FROM accounts WHERE id = 10");
        // ok:java_inject_rule-SqlInjection
        ResultSet rs = c.createStatement().executeQuery(sql);
    }


    public void testJdoQueries(String input) {
        PersistenceManager pm = getPM();

        // ruleid: java_inject_rule-SqlInjection
        pm.newQuery("select * from Users where name = " + input);

        // ruleid: java_inject_rule-SqlInjection
        pm.newQuery("sql", "select * from Products where name = " + input);

        // ok: java_inject_rule-SqlInjection
        pm.newQuery("select * from Config");

        final String query = "select * from Config";
        // ok: java_inject_rule-SqlInjection
        pm.newQuery(query);

        // ok: java_inject_rule-SqlInjection
        pm.newQuery("sql", query);
    }

    public void testJdoQueriesAdditionalMethodSig(String input) {
        PersistenceManager pm = getPM();

        // ruleid: java_inject_rule-SqlInjection
        pm.newQuery(UserEntity.class, new ArrayList(), "id == " + input);

        // ok: java_inject_rule-SqlInjection
        pm.newQuery(UserEntity.class, new ArrayList(), "id == 1");

        // ruleid: java_inject_rule-SqlInjection
        pm.newQuery(UserEntity.class, "id == " + input);

        // ok: java_inject_rule-SqlInjection
        pm.newQuery(UserEntity.class, "id == 1");

        // ruleid: java_inject_rule-SqlInjection
        pm.newQuery((Extent) null, "id == " + input);

        // ok: java_inject_rule-SqlInjection
        pm.newQuery((Extent) null, "id == 1");
    }

    public void testHibernate(SessionFactory sessionFactory, String input) {
        Session session = sessionFactory.openSession();
        String instring = String.format("%s", input);

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object> query = null;

        // ok: java_inject_rule-SqlInjection
        session.createQuery(query);

        // ruleid: java_inject_rule-SqlInjection
        session.createQuery(instring);

        CriteriaQuery<Object> cq = cb.createQuery(Object.class);
        Criteria criteria = session.createCriteria(UserEntity.class);

        //The following would need to be audited

        // ruleid: java_inject_rule-SqlInjection
        criteria.add(Restrictions.sqlRestriction("test=1234" + instring));

        // seems to be a limitation of the taint engine
        // todoruleid: java_inject_rule-SqlInjection
        session.createQuery("select t from UserEntity t where id = " + instring);


        //More sqlRestriction signatures

        // ruleid: java_inject_rule-SqlInjection
        criteria.add(Restrictions.sqlRestriction("param1  = ? and param2 = " + instring,instring, StandardBasicTypes.STRING));
        // ruleid: java_inject_rule-SqlInjection
        criteria.add(Restrictions.sqlRestriction("param1  = ? and param2 = " + instring,new String[] {instring}, new Type[] {StandardBasicTypes.STRING}));

        // ok: java_inject_rule-SqlInjection
        criteria.add(Restrictions.sqlRestriction("test=1234"));

        final String localSafe = "where id=1337";

        // ok: java_inject_rule-SqlInjection
        session.createQuery("select t from UserEntity t " + localSafe);

        final String localSql = "select * from TestEntity " + localSafe;
        // ok: java_inject_rule-SqlInjection
        session.createSQLQuery(localSql);

        //More sqlRestriction signatures (with safe binding)

        // ok: java_inject_rule-SqlInjection
        criteria.add(Restrictions.sqlRestriction("param1  = ?",instring, StandardBasicTypes.STRING));
        // ok: java_inject_rule-SqlInjection
        criteria.add(Restrictions.sqlRestriction("param1  = ? and param2 = ?", new String[] {instring}, new Type[] {StandardBasicTypes.STRING}));
    }

    public void testVertx(SqlConnection conn, SqlClient client, String injection) {
        // true positives
        // ruleid: java_inject_rule-SqlInjection
        client.query(injection);
        // ruleid: java_inject_rule-SqlInjection
        client.preparedQuery(injection);
        // ruleid: java_inject_rule-SqlInjection
        conn.prepare(injection);

        String constantValue = "SELECT * FROM test";
        // ok: java_inject_rule-SqlInjection
        client.query(constantValue);
        // ok: java_inject_rule-SqlInjection
        conn.query(constantValue);
    }

    public void testPreparedStmt(PreparedStatement stmt, String input) throws SQLException {
        // ruleid: java_inject_rule-SqlInjection
        stmt.execute("select * from users where email = " + input);
        // ruleid: java_inject_rule-SqlInjection
        stmt.execute("select * from users where email = " + input, Statement.RETURN_GENERATED_KEYS);
        // ruleid: java_inject_rule-SqlInjection
        stmt.execute("select * from users where email = " + input, new int[]{Statement.RETURN_GENERATED_KEYS});
        // ruleid: java_inject_rule-SqlInjection
        stmt.execute("select * from users where email = " + input, new int[]{Statement.RETURN_GENERATED_KEYS});
        // ruleid: java_inject_rule-SqlInjection
        stmt.executeQuery("select * from users where email = " + input);
        // ruleid: java_inject_rule-SqlInjection
        stmt.executeQuery("select * from users where email = '" + input +"' AND name != NULL");
        // ruleid: java_inject_rule-SqlInjection
        stmt.executeUpdate("update from users set email = '" + input +"' where name != NULL");
        // ruleid: java_inject_rule-SqlInjection
        stmt.executeLargeUpdate("update from users set email = '" + input +"' where name != NULL");
        // ruleid: java_inject_rule-SqlInjection
        stmt.addBatch("update from users set email = '" + input +"' where name != NULL");
    }

    public void good(String clientDetails) {
        final String statementUsingConstants = "insert into oauth_client_details (" + CLIENT_FIELDS + ")"
                + "values (?,?,?,?,?,?,?,?,?,?,?)";
        // ok: java_inject_rule-SqlInjection
        jdbcTemplate.update(statementUsingConstants, clientDetails);
    }

    public void good2(String clientDetails) {
        // ok: java_inject_rule-SqlInjection
        jdbcTemplate.update(DEFAULT_INSERT_STATEMENT, clientDetails);
    }

    public void bad(String clientDetails) {
        String stmtUsingFuncParam = "test" + clientDetails + "test";
        // ruleid: java_inject_rule-SqlInjection
        jdbcTemplate.update(stmtUsingFuncParam, clientDetails);
    }

    public void badInline(String clientDetails) {
        // ruleid: java_inject_rule-SqlInjection
        jdbcTemplate.update("test" + clientDetails + "test", clientDetails);
    }

    // this private method has a single caller passing a constant string => safe (detected FP)
    private void goodPrivateMethod(PreparedStatement stmt, String input) throws Exception {
        // ruleid: java_inject_rule-SqlInjection
        stmt.execute("select * from users where email = " + input);
    }

    public void singleCaller() throws Exception {
        goodPrivateMethod(null, "constant string");
    }

    public void testPropgators(PreparedStatement stmt, String input) throws Exception {
        StringBuilder sb1 = new StringBuilder(input);
        // ruleid: java_inject_rule-SqlInjection
        stmt.execute("select * from users where email = " + sb1.toString());

        StringBuilder sb2 = new StringBuilder();
        sb2.append(input);
        // ruleid: java_inject_rule-SqlInjection
        stmt.execute("select * from users where email = " + sb2.toString());

        String str = String.format("select * from users where email = %s", input);
        // ruleid: java_inject_rule-SqlInjection
        stmt.execute(str);

        // ruleid: java_inject_rule-SqlInjection
        stmt.execute("select * from users where email = ".concat(input));
    }

    public void testJDBI(org.jdbi.v3.core.Handle handle, String input) throws SQLException {
        // ruleid: java_inject_rule-SqlInjection
        handle.createQuery(input);
        // ruleid: java_inject_rule-SqlInjection
        handle.createScript(input);
        // ruleid: java_inject_rule-SqlInjection
        handle.createUpdate(input);
        // ruleid: java_inject_rule-SqlInjection
        handle.execute(input);
        // ruleid: java_inject_rule-SqlInjection
        handle.prepareBatch(input);
        // ruleid: java_inject_rule-SqlInjection
        handle.select(input);
        // ruleid: java_inject_rule-SqlInjection
        new org.jdbi.v3.core.statement.Script(handle, input);
        // ruleid: java_inject_rule-SqlInjection
        new org.jdbi.v3.core.statement.Update(handle, input);
        // ruleid: java_inject_rule-SqlInjection
        new org.jdbi.v3.core.statement.PreparedBatch(handle, input);
    }

    public void danger(DataSource dataSource, String input) throws SQLException {
        String sql = "select * from Users where name = " + input;
        Connection connection = dataSource.getConnection();
        try (Statement statement = connection.createStatement()) {
            // ruleid: java_inject_rule-SqlInjection
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println(resultSet);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new SQLException(e);
        }
    }

    public void danger2(DataSource dataSource, String input) throws SQLException {
        String value = String.format("%s", input);
        String sql = "select * from Users where name = " + input;
        Connection connection = dataSource.getConnection();
        try (Statement statement = connection.createStatement()) {
            // ruleid: java_inject_rule-SqlInjection
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println(resultSet);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new SQLException(e);
        }
    }

    public void danger3(DataSource dataSource, String input) throws SQLException {
        Connection connection = dataSource.getConnection();
        try (Statement statement = connection.createStatement()) {
            // ruleid: java_inject_rule-SqlInjection
            ResultSet resultSet = statement.executeQuery("select * from Users where name = " + input);
            System.out.println(resultSet);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new SQLException(e);
        }
    }

    public void danger4(DataSource dataSource, String input) throws SQLException {
        Connection connection = dataSource.getConnection();
        try (Statement statement = connection.createStatement()) {
            // ruleid: java_inject_rule-SqlInjection
            ResultSet resultSet = statement.executeQuery("select * from Users where name = " + String.format("%s", input));
            System.out.println(resultSet);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new SQLException(e);
        }
    }


    public void danger5(DataSource dataSource, String input) throws SQLException {
        Connection connection = dataSource.getConnection();
        try (Statement statement = connection.createStatement()) {
            // ruleid: java_inject_rule-SqlInjection
            ResultSet resultSet = statement.executeQuery(String.format("select * from Users where name = %s", input));
            System.out.println(resultSet);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new SQLException(e);
        }
    }

    public void ok(DataSource dataSource, String input) throws SQLException {
        String sql = "select * from Users where name = jhon";
        // ok: java_inject_rule-SqlInjection
        Connection connection = dataSource.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println(resultSet);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new SQLException(e);
        }
    }

    public void dangerBasePeerExecuteStatement2(String input) {
        try  {
            // ruleid:java_inject_rule-SqlInjection
            BasePeer.executeStatement(String.format("select * from Users where name = %s", input));
        }
        catch (TorqueException e){
            e.printStackTrace();
        }
    }

    public void dangerBasePeerExecuteStatement3(String input) {
        try  {
            // ruleid:java_inject_rule-SqlInjection
            BasePeer.executeStatement(new StringBuilder(input).toString());
        }
        catch (TorqueException e){
            e.printStackTrace();
        }
    }

    public void dangerBasePeerExecuteQuery(String input) {
        try  {
            // ruleid:java_inject_rule-SqlInjection
            BasePeer.executeQuery("select * from Users where name = '" + input + "'");
        }
        catch (TorqueException e){
            e.printStackTrace();
        }
    }

    public void dangerBasePeerExecuteQuery2(String input) {
        try  {
            // ruleid:java_inject_rule-SqlInjection
            BasePeer.executeQuery(String.format("select * from Users where name = %s", input));
        }
        catch (TorqueException e){
            e.printStackTrace();
        }
    }

    public void dangerBasePeerExecuteQuery3(String input) {
        try  {
            // ruleid:java_inject_rule-SqlInjection
            BasePeer.executeQuery(new StringBuilder(input).toString());
        }
        catch (TorqueException e){
            e.printStackTrace();
        }
    }

    public void okBasePeerExecuteStatement() {
        try  {
            // ok:java_inject_rule-SqlInjection
            BasePeer.executeStatement("select * from Users where name = jhon'");
        }
        catch (TorqueException e){
            e.printStackTrace();
        }
    }

    public void okBasePeerExecuteQuery() {
        try  {
            // ok:java_inject_rule-SqlInjection
            BasePeer.executeQuery("select * from Users where name = jhon'");
        }
        catch (TorqueException e){
            e.printStackTrace();
        }
    }

    public List<UserEntity> findBySomeCriteria(EntityManager entityManager, String criteriaValue) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = criteriaBuilder.createQuery(YourEntity.class);
        Root<UserEntity> root = query.from(UserEntity.class);
        // ok: java_inject_rule-SqlInjection
        query.select(root).where(criteriaBuilder.equal(root.get("someProperty"), criteriaValue));

        return entityManager.createQuery(query).getResultList();
    }

    public void executeQueryWithUserInput(String userInput) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String url = "URL";

        try {
            conn = DriverManager.getConnection(url);
            st = conn.prepareStatement("SELECT name FROM table where name=?");
            st.setString(1, userInput);
            // ok: java_inject_rule-SqlInjection
            rs = st.executeQuery();
            while (rs.next()) {
                String result = rs.getString(1);
                System.out.println(result);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void dangerStringConcat(String biz) {
        String query = "select foo from bar where" + biz + " limit 1";
        Session session = this.sessionFactory.openSession();
        try {
            // ruleid:java_inject_rule-SqlInjection
            PreparedStatement ps = session.connection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer item = rs.getInt("foo");
            }
        } catch (SQLException e) {
            logger.error("Error!", e);
        } finally {
            session.close();
        }
    }

}
