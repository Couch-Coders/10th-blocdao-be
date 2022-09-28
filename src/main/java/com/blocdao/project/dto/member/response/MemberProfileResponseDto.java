package com.blocdao.project.dto.member.response;

import com.blocdao.project.entity.Member;
import com.blocdao.project.entity.Project;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class MemberProfileResponseDto {
    private String nickName;
    private String imageUrl;
    private String email;
    private String phone;
    private String profileLink;

    @JsonProperty("projects")
    private List<ProjectResponseDto> projectResponseDtoList = new ArrayList<>();

    public MemberProfileResponseDto(Member member) {
        this.nickName = member.getNickName();
        this.imageUrl = member.getImageUrl();
        this.email = member.getEmail();
        this.phone = member.getPhone();
        this.profileLink = member.getProfileLink();

        if(!member.getProjects().isEmpty()){
            member.getProjects().forEach((project -> {
                ProjectResponseDto projectResponseDto = new ProjectResponseDto(project);
                projectResponseDtoList.add(projectResponseDto);
            }));
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProjectResponseDto{
        private String projectTitle;
        private String startTime;
        private String endTime;

        public ProjectResponseDto(Project project){
            this.projectTitle = project.getTitle();
            this.startTime = project.getStartTime();
            this.endTime = project.getEndTime();
        }
    }
}
