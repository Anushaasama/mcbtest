package com.interview.accounts.model;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private int number;
    private String name;
    private double balance;
}
