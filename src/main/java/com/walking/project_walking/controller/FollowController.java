package com.walking.project_walking.controller;

import com.walking.project_walking.domain.Users;
import com.walking.project_walking.domain.followdto.FollowProfileDto;
import com.walking.project_walking.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FollowController {
    private final FollowService followService;

    // 사용자를 팔로우
    @PostMapping("/users/{followerId}/follow/{followingId}")
    public ResponseEntity<String> followUser(
            @PathVariable Long followerId,
            @PathVariable Long followingId
    ) {
        followService.followUser(followerId, followingId);
        return ResponseEntity.ok("해당 유저를 팔로우 합니다.");
    }

    // 팔로잉을 조회
    @GetMapping("/users/{userId}/following")
    public ResponseEntity<List<FollowProfileDto>> getFollowing(
            @PathVariable Long userId
    ) {
        List<FollowProfileDto> followings = followService.getFollowing(userId);
        return ResponseEntity.ok(followings);
    }

    // 팔로워를 조회
    @GetMapping("/users/{userId}/follower")
    public ResponseEntity<List<FollowProfileDto>> getFollower(
            @PathVariable Long userId
    ) {
        List<FollowProfileDto> followers = followService.getFollower(userId);
        return ResponseEntity.ok(followers);
    }

}
