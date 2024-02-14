package com.example.seChallenge.friend;

import com.example.seChallenge.domain.Friend;
import com.example.seChallenge.repository.FriendRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendsService {

    @Autowired
    private FriendRepository friendRepository;

    public Friend createFriend(Friend friend) {
        return friendRepository.save(friend);
    }

    public Iterable<Friend> getAllFriends() {
        return friendRepository.findAll();
    }

    public Friend getFriend(long id) {
        Optional<Friend> friendOptional = this.friendRepository.findById(id);
        if(friendOptional.isPresent()) {
            
            return friendOptional.get();
        }
        return null ;
    }
}
