package com.example.seChallenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.seChallenge.domain.Friend;
import com.example.seChallenge.friend.FriendsService;

@SpringBootTest
public class FriendsServiceTest {

    @Autowired
    private FriendsService friendsService;
	  
    @Test
    @Order(2) 
    void testSaveFriend(){
        //setup product
        Friend friend = new Friend();
        friend.setFirstName("First");
        friend.setLastName("Last");
        friend.setEmail("Test@gmail.com");
        
        friend = this.friendsService.createFriend(friend);
        
        assertNotNull(friend.getId());
        
        Friend fetchedFriend = this.friendsService.getFriend(friend.getId());

        //should not be null
        assertNotNull(fetchedFriend);

        //should equal
        assertEquals(friend.getFirstName(), fetchedFriend.getFirstName());
    }
}
