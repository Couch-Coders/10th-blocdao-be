package com.blocdao.project.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseTimeEntity implements UserDetails {

    @Id
    @Column(name = "member_id")
    private String uid;

    @Column(nullable = false, length = 20)
    @NotBlank(message = "nickName 값을 입력하세요.")
    private String nickName;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    @NotBlank(message = "email 값을 입력하세요.")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "phone 값을 입력하세요.")
    private String phone;

    @Column
    private String profileLink;

    //탈퇴 여부
    @Column
    private Boolean isWithdrawal = false;

    //탈퇴 날짜
    @Column
    private LocalDate dataWithdrawal;

    @OneToMany(mappedBy = "member")
    private List<MemberStacks> memberStacks = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Project> projects = new ArrayList<>();

    public void addMemberStacks(MemberStacks memberStacks) {
        this.memberStacks.add(memberStacks);
        if (memberStacks.getMember() != this) {
            memberStacks.setMember(this);
        }
    }
    public void addProject(Project project) {
        this.projects.add(project);
        if (project.getMember() != this) {
            project.setMember(this);
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return uid;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
