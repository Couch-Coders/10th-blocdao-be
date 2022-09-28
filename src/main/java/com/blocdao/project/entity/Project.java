package com.blocdao.project.entity;

import com.blocdao.project.dto.project.request.ProjectRequestDto;
import com.blocdao.project.entity.enums.RecruitmentType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_uid")
    private Member member;

    // 제목
    @Column(nullable = false)
    private String title;

    // 프로젝트 타입 : 스터디 or 프로젝트
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RecruitmentType recruitmentType;

    // 총 모집인원
    @Column(nullable = false)
    private Integer recruitmentNumber;

    // 온라인 or 오프라인
    @Column(nullable = false)
    private Boolean isOnline;

    // 시작일
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String startTime;
    // 종료일
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String endTime;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private String expectedStartDate;
    // 연락방법
    private String contact;
    // 모집여부 : True or False
    private Boolean isRecruitment;
    // todo 조회 시 마다 뷰 갱신
    private Integer view = 0;
    // 주소
    private String address;
    // 생성자 id
    private String createUid;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProjectStack> projectStacks = new ArrayList<>();

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public static Project ofProject(ProjectRequestDto projectRequestDto, Member member){

        Project project = Project.builder()
                .recruitmentType(projectRequestDto.getRecruitmentType())
                .recruitmentNumber(projectRequestDto.getRecruitmentNumber())
                .isOnline(projectRequestDto.getIsOnline())
                .startTime(projectRequestDto.getExpectedStartDate())
                .endTime(projectRequestDto.getExpectedEndDate())
                .createDate(LocalDateTime.now())
                .expectedStartDate(projectRequestDto.getExpectedStartDate())
                .contact(projectRequestDto.getContact())
                .isRecruitment(projectRequestDto.getIsRecruitment())
                .address(projectRequestDto.getAddress())
                .title(projectRequestDto.getTitle())
                .content(projectRequestDto.getContent())
                .createUid(member.getUid())
                .member(member)
                .build();

        return project;
    }
}


