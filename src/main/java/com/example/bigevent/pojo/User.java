package com.example.bigevent.pojo;



import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @NotNull(groups = {update.class})
    private Integer id;//主键ID
    
    @Pattern(regexp = "^\\S{0,16}$")
    private String username;//用户名
    
    //@NotEmpty
    @JsonIgnore
    @Pattern(regexp = "^\\S{0,16}$")
    private String password;//密码
    
    private String nickname;//昵称
    
    @Email
    @NotEmpty
    private String email;//邮箱
    
    private String userPic;//用户头像地址

    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间

    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间
    
    public interface update extends Default{
        
    }
}
