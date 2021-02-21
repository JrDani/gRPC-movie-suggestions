package com.github.jrdani.user.service;

import com.github.jrdani.common.*;
import com.github.jrdani.user.*;
import com.github.jrdani.user.repository.*;
import io.grpc.stub.*;
import net.devh.boot.grpc.server.service.*;
import org.springframework.beans.factory.annotation.*;

import javax.transaction.*;

@GrpcService
public class UserService extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void getUserGenre(UserSearchRequest request, StreamObserver<UserResponse> responseObserver) {
        UserResponse.Builder builder = UserResponse.newBuilder();
        this.userRepository.findById(request.getLoginId())
                .ifPresent(user ->
                    builder.setLoginId(user.getLogin())
                    .setName(user.getName())
                    .setGenre(Genre.valueOf(user.getGenre().toUpperCase()))
                );
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

    @Override
    @Transactional
    public void updateUserGenre(UserGenreUpdateRequest request, StreamObserver<UserResponse> responseObserver) {
        UserResponse.Builder builder = UserResponse.newBuilder();
        this.userRepository.findById(request.getLoginId())
            .ifPresent(user -> {
                user.setGenre(request.getGenre().toString());
                builder.setLoginId(user.getLogin())
                        .setName(user.getName())
                        .setGenre(Genre.valueOf(user.getGenre().toUpperCase()));
            });
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}
