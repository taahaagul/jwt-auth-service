package com.taahaagul.tgjwtsecurity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class VerificationToken {

    @Id
    @GeneratedValue
    private Long id;
    private String token;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    private Date created;
}
