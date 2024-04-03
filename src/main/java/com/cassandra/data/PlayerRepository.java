package com.cassandra.data;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface PlayerRepository extends CassandraRepository<Player, Integer> {
}
