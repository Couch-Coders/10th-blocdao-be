package com.blocdao.project.domain.ProjectApplicant;

import com.blocdao.project.domain.projectMember.ProjectMember;
import com.blocdao.project.domain.base.BaseTimeEntity;
import com.blocdao.project.domain.member.Member;
import com.blocdao.project.domain.project.Project;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectApplicant extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_applicant_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToOne
    @JoinColumn(name = "project_member_id")
    private ProjectMember projectMember;
}