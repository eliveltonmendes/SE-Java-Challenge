package com.example.seChallenge.repository;

import com.example.seChallenge.domain.Friend;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  FriendRepository extends CrudRepository<Friend,Long> {
}
