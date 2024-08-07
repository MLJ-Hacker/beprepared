package com.mmj.beprepared.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@Table(name = "tb_token")
@AllArgsConstructor
@NoArgsConstructor
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private  boolean revoked;
    private  boolean expired;
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "citizen_id")
    private Citizen citizen;


}
