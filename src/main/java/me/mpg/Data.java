package me.mpg;

import lombok.Builder;

@lombok.Data
@Builder
public class Data {
    @Builder.Default private int number = 0;
}
