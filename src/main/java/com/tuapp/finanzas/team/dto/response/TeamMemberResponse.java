package com.tuapp.finanzas.team.dto.response;

import com.tuapp.finanzas.team.domain.model.TeamMember;

import java.time.Instant;

public record TeamMemberResponse(Long id, Long teamId, Long userId, String rol, Instant fechaIngreso) {
    public static TeamMemberResponse from(TeamMember member) {
        return new TeamMemberResponse(member.getId(), member.getTeamId(), member.getUserId(), member.getRol(), member.getFechaIngreso());
    }
}