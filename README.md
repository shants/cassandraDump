"# cassandra" 
create keyspace my_keyspace with replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

CREATE TABLE movie_by_actor ( actor TEXT, release_year INT, rovie_id UUID, title TEXT, genres SET<TEXT>, rating FLOAT, PRIMARY KEY ((actor), release_year, movie_id) ) WITH CLUSTERING ORDER BY ( release_year DSC, movie_id ASC);

create CUSTOM INDEX title ON movie_by_actor (title) USING 'org.apache.cassandra.index.sasi.SASIIndex' WITH OPTIONS = { 'mode' : 'CONTAINS' };


cqlsh:my_keyspace> DESCRIBE movie_by_actor ;

CREATE TABLE my_keyspace.movie_by_actor (
    actor text,
    release_year int,
    movie_id uuid,
    genres set<text>,
    rating float,
    title text,
    PRIMARY KEY (actor, release_year, movie_id)
) WITH CLUSTERING ORDER BY (release_year DESC, movie_id ASC)
    AND bloom_filter_fp_chance = 0.01
    AND caching = {'keys': 'ALL', 'rows_per_partition': 'NONE'}
    AND comment = ''
    AND compaction = {'class': 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy', 'max_threshold': '32', 'min_threshold': '4'}
    AND compression = {'chunk_length_in_kb': '64', 'class': 'org.apache.cassandra.io.compress.LZ4Compressor'}
    AND crc_check_chance = 1.0
    AND dclocal_read_repair_chance = 0.1
    AND default_time_to_live = 0
    AND gc_grace_seconds = 864000
    AND max_index_interval = 2048
    AND memtable_flush_period_in_ms = 0
    AND min_index_interval = 128
    AND read_repair_chance = 0.0
    AND speculative_retry = '99PERCENTILE';
CREATE CUSTOM INDEX title ON my_keyspace.movie_by_actor (title) USING 'org.apache.cassandra.index.sasi.SASIIndex' WITH OPTIONS = {'mode': 'CONTAINS'};
  
INSERT INTO movie_by_actor (actor,release_year,movie_id ) VALUES ( 'rober downing jr',2010,uuid());

cqlsh:my_keyspace> select * from movie_by_actor ;

 actor            | release_year | movie_id                             | genres | rating | title
------------------+--------------+--------------------------------------+--------+--------+-------
       emma stone |         2016 | be4ef540-12ce-4586-9d79-4705637cd00e |   null |   null |  null
 rober downing jr |         2010 | dca43455-8550-407c-8ec2-1ba207daa319 |   null |   null |  null

(2 rows)


Running Code :

java -cp cassandra-1.0-SNAPSHOT.jar:cassandra-driver-core-3.0.0.jar:guava-19.0.jar:slf4j-api-1.7.5.jar:netty-all-4.0.33.Final.jar:metrics-core-3.0.2.jar Test

Test -> Main class 


