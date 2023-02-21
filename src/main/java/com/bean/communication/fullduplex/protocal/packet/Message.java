package com.bean.communication.fullduplex.protocal.packet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Bean
 * @Date 2023/2/21 22:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable {

    private static final long serialVersionUID = 325123123738581347L;

    private String msg;
}
