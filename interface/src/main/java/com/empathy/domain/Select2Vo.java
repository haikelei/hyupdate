package com.empathy.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tybest
 * @date 2017/9/10 11:32
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
@AllArgsConstructor
public class Select2Vo {

    private int total_count;
    private List<Select2Node> items = new ArrayList<>();
}
