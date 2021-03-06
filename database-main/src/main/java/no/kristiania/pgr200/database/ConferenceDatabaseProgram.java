package no.kristiania.pgr200.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGPoolingDataSource;

public class ConferenceDatabaseProgram {

    private DataSource dataSource;
    private ConferenceTalkDao talkDao;
    private ConferenceTopicDao topicDao;
    private ArgumentReader argumentReader;

    public ConferenceDatabaseProgram() throws IOException {
        this.dataSource = createDataSource();
        this.talkDao = new ConferenceTalkDao(dataSource);
        this.topicDao = new ConferenceTopicDao(dataSource);

    }

    public static DataSource createDataSource() throws IOException {
        Properties props = new Properties();

        try( FileInputStream in = new FileInputStream("db.properties")) {
            props.load(in);
        }

        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        PGPoolingDataSource dataSource = new PGPoolingDataSource();
        dataSource.setUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);

        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        //flyway.clean();
        //flyway.migrate();

        return dataSource;
    }

    public static void main(String[] args) throws SQLException, IOException {
        new ConferenceDatabaseProgram().run(args);
    }

    public void run(String[] args) throws SQLException, IOException {
        if (args.length == 0) {
            System.out.println("Run the class with one of these arguments:\n" +
                    "For inserting a talk/topic type Insert [Talk/Topic] [Title] [Description] <- Only for Talk \n" +
                    "To Retrieve a single object type Retrieve [Talk/Topic] and ID for talk \n" +
                    "To List either every talk or topic type List [Talk/Topic]");
            System.exit(1);
        }

        argumentReader = new ArgumentReader(args);
    }
}
