<<<<<<<< HEAD:src/main/java/com/allways/domain/user/dto/UserDto.java
package com.allways.domain.user.dto;
========
package com.allways.domain.user.entity;
>>>>>>>> 2cbf7e415152e4f799a2c83830f30202645997ff:src/main/java/com/allways/domain/user/entity/UserDto.java

import com.allways.domain.user.domain.User;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long userSeq;

    private String nickname;

    private String blogName;

    public static UserDto toDto(User user) {
        return new UserDto(user.getUserSeq(), user.getNickname()
                , (user.getBlog() != null) ? user.getBlog().getBlogName() : "");
    }

}
