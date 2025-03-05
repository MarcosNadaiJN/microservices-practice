package com.ms.user.services;

import com.ms.user.models.UserModel;
import com.ms.user.producers.UserProducer;
import com.ms.user.repositories.UserRespository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    final UserRespository userRespository;
    final UserProducer userProducer;

    public UserService(UserRespository userRespository, UserProducer userProducer) {
        this.userRespository = userRespository;
        this.userProducer = userProducer;
    }

    @Transactional
    public UserModel save(UserModel userModel) {
        UserModel userModelSalvo = this.userRespository.save(userModel);
        this.userProducer.publishEmail(userModelSalvo);
        return userModelSalvo;
    }
}
