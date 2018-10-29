package no.kristiania.pgr200.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

public class ConferenceTalkDao extends AbstractDao {

    private DataSource dataSource;

    public ConferenceTalkDao(DataSource dataSource) {
        super(dataSource);

        this.dataSource = dataSource;
    }

    public ConferenceTalk retrieve(int id) throws SQLException {
        return retrieveSingleObject("select * from conference_talk where id = ?", this::mapToTalk, id);
    }

    public List<ConferenceTalk> list() throws SQLException {
        return list("select * from conference_talk", this::mapToTalk);
    }

    private ConferenceTalk mapToTalk(ResultSet rs) throws SQLException {
        ConferenceTalk talk = new ConferenceTalk();
        talk.setId(rs.getInt("id"));
        talk.setTitle(rs.getString("title"));
        talk.setDescription(rs.getString("description"));
        talk.setTopic(rs.getString("topic"));
        return talk;
    }

    public void insertTalk(ConferenceTalk talk) throws SQLException {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "insert into CONFERENCE_TALK (TITLE, DESCRIPTION, TOPIC) values (?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, talk.getTitle());
                statement.setString(2, talk.getDescription());
                statement.setString(3, talk.getTopic());
                statement.executeUpdate();

                try(ResultSet resultSet = statement.getGeneratedKeys()) {
                    resultSet.next();
                    talk.setId(resultSet.getInt("id"));
                }
            }
        }
    }
}

