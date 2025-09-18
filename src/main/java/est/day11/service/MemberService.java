package est.day11.service;

import est.day11.domain.Member;
import est.day11.dto.MemberResponseDto;
import est.day11.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<MemberResponseDto> getAllMembersWithTeams() {
        return memberRepository.findAllByTeamIsNotNull()
                .stream()
                .map(member -> {
                    System.out.println("Before accessing teamName: " + member.getTeam().getClass());
                    String teamName = member.getTeam().getName(); // LAZY 로딩 발생
                    System.out.println("After accessing teamName: " + teamName);
                    return MemberResponseDto.from(member);
                })
                .toList();
    }

    public Long join(Member member) {  // 회원가입
//        long startTimeMs = System.currentTimeMillis();
//        try {
            validateDuplicateMember(member); //중복 회원 검증
            memberRepository.save(member);
            return member.getId();
//        } finally { // try 블록의 코드 실행이 성공적으로 끝나거나, 도중에 예외가 발생하더라도 finally 블록은 무조건 실행
//            long finishTimeMs = System.currentTimeMillis();
//            long timeMs = finishTimeMs - startTimeMs;
//            System.out.println("join " + timeMs + "ms");
//        }
    }

    public List<MemberResponseDto> findMembers() {   // 전체 회원 조회
//        long startTimeMs = System.currentTimeMillis();
//        try {
        return memberRepository.findAll().stream()
                .map(MemberResponseDto::from)
                .toList();
//        } finally {
//            long finishTimeMs = System.currentTimeMillis();
//            long timeMs = finishTimeMs - startTimeMs;
//            System.out.println("findMembers " + timeMs + "ms");
//        }
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다: " + member.getName());
                });
    }
}

//  반환된 엔티티는 준영속(detached) : 영속성 컨텍스트에 저장되었다가 분리된 상태

//        return memberRepository.findAll()
//        .stream()
//        .map(member -> {
//            System.out.println("Before accessing teamName: " + member.getTeam().getClass());
//            String teamName = member.getTeam().getName(); // LAZY 로딩 발생
//            System.out.println("After accessing teamName: " + teamName);
//            return MemberResponseDto.from(member);
//        })
//        .toList();
