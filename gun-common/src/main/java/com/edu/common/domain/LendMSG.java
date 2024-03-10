package com.edu.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LendMSG<T> implements Serializable {
  private  T data;
    // 构造函数、Getter和Setter省略
}
