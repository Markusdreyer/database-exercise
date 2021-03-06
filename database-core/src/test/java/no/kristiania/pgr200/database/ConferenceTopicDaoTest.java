package no.kristiania.pgr200.database;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ConferenceTopicDaoTest {
    private DataSource createDataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        dataSource.setUser("sa");

        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.clean();
        flyway.migrate();
        return dataSource;
    }

    @Test
    public void shouldInsertConferenceTopics() throws SQLException {
        ConferenceTopic topic = new ConferenceTopic("Gardening");
        ConferenceTopicDao dao = new ConferenceTopicDao(createDataSource());
        dao.insert(topic);
        assertThat(dao.list()).extracting(ConferenceTopic::getTitle).contains("Gardening");
    }

    @Test
    public void shouldRetrieveOneTopic() throws SQLException {
        ConferenceTopic topic = new ConferenceTopic("Cool Topic");
        ConferenceTopicDao topicDao = new ConferenceTopicDao(createDataSource());
        topicDao.insert(topic);

        assertThat(topicDao.retrieve(1).getId()).isEqualTo(1);
    }

    @Test
    public void shouldListConferenceTopics() throws SQLException {
        ConferenceTopic topic = new ConferenceTopic("Technology");
        ConferenceTopicDao dao = new ConferenceTopicDao(createDataSource());
        dao.insert(topic);
        assertThat(dao.list().size()).isEqualTo(topic.getId());
    }

    @Test
    public void confirmCorrectTopicId() throws SQLException {
        ConferenceTopic topic = new ConferenceTopic("Fiction");
        ConferenceTopicDao dao = new ConferenceTopicDao(createDataSource());
        dao.insert(topic);
        assertThat(dao.list()).extracting(ConferenceTopic::getId).contains(topic.getId());
    }
}