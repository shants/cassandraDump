/**
 * Created by shantanus on 12/8/2017.
 */

import com.datastax.driver.core.*;
import com.datastax.driver.core.querybuilder.QueryBuilder;

import com.datastax.driver.core.querybuilder.QueryBuilder.*;

class Test {

    public void addMovie(Session s, String name, int year, String title) {

    }

    public static void main(String[] args) {

        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        cluster.init();
        Session session = cluster.connect("my_keyspace");
        ResultSet movie = session.execute("SELECT * from my_keyspace.movie_by_actor");

        Statement allMovie = QueryBuilder.select().all().from("my_keyspace","movie_by_actor");
        ResultSet mv = session.execute(allMovie);

        for (Row row : mv)
        {
            System.out.format("movie actor: %s %s %s\n",row.getString("actor"),  Integer.toString(row.getInt("release_year")), row.getUUID("movie_id").toString());

        }

    }
}