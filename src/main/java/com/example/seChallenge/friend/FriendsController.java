package com.example.seChallenge.friend;

import com.example.seChallenge.domain.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
@RequestMapping(value = { "/friends" })
public class FriendsController {

    @Autowired
    private FriendsService friendsService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Friend createFriend(@RequestBody Friend friend) {
        return this.friendsService.createFriend(friend);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Friend> getAllFriends() {
        return this.friendsService.getAllFriends();
    }
}
