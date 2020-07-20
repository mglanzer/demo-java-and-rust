package me.mpg;

import lombok.Builder;

import java.util.List;

@lombok.Data
@Builder
public class Message {
    private String cmd;
    private List<Data> data;
}
