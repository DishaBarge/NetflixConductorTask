package com.example.NetflixConductorTask.AtheleteRepo;

import com.example.NetflixConductorTask.AtheletePojo.Athelete;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AtheleteRepository extends MongoRepository<Athelete, String> {

    Optional<Athelete> findById(String id);
    void deleteById(String id);
}
