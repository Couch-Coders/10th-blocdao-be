package com.blocdao.project.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 20)
    private String nick_name;

    @Column
    private String image_url;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column
    private String profile_link;

    @Column(nullable = false)
    private boolean is_withdrawal = false;

    @Column
    private String data_withdrawal;

    @Column(nullable = false)
    private LocalDateTime create_time = LocalDateTime.now();

}
