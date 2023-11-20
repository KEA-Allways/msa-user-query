package com.allways.common.feign.user;

import java.util.ArrayList;
import java.util.List;

import com.allways.domain.user.entity.User;
import com.allways.domain.user.exception.UserNotFoundException;
import com.allways.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserFeignService {

    private final UserRepository userRepository;

    public UserFeignResponse queryUser(Long userSeq){
        User user = userRepository.findUserByUserSeq(userSeq).orElseThrow(UserNotFoundException::new);

        return new UserFeignResponse(
                user.getUserId(), user.getUserId(), user.getEmail(), user.getProfileImgSeq()
        );
    }


    @Transactional
    public List<UserByPostFeignResponse> queryUsersByPost(List<UserByPostFeignRequest> userByPostFeignRequestList){

        List<Long> userSeqList = new ArrayList<>();

        for (UserByPostFeignRequest userByPostFeignRequest : userByPostFeignRequestList) {
            userSeqList.add(userByPostFeignRequest.getUserSeq());
        }

        List<User> userList = userRepository.findUserByUserSeqIn(userSeqList);

        List<UserByPostFeignResponse> userByPostFeignResponseList = new ArrayList<>();

        for (UserByPostFeignRequest userByPostFeignRequest : userByPostFeignRequestList) {
            for (User user : userList) {
                if(userByPostFeignRequest.getUserSeq().equals(user.getUserSeq())) {
                    userByPostFeignResponseList.add(new UserByPostFeignResponse(userByPostFeignRequest.getPostSeq(),
                            userByPostFeignRequest.getUserSeq(),
                            user.getUserId(), user.getNickname()));
                }
            }
        }

        return userByPostFeignResponseList;
    }
}
