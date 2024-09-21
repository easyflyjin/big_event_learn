package com.example.bigevent.pojo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @NotNull(groups = {Update.class})
    private Integer id;//主键ID

    @NotEmpty(groups = {Update.class,Add.class})
    private String categoryName;//分类名称

    @NotEmpty(groups = {Update.class,Add.class})
    private String categoryAlias;//分类别名
    @JsonIgnore
    private Integer createUser;//创建人ID

    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间

    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间

    public interface Add extends Default{
        
    }
    public interface Update extends Default {
    
        
    }
}
