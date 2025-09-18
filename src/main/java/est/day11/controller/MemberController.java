package est.day11.controller;

import est.day11.domain.Member;
import est.day11.dto.MemberResponseDto;
import est.day11.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 가입
     */
    @PostMapping("/members")
    public String createMember(@RequestBody Member member) {
        Long memberId = memberService.join(member);
        return "회원가입 완료! 생성된 회원 ID: " + memberId;
    }

    /**
     * 전체 회원 조회
     */
    @GetMapping("/members")
    public List<MemberResponseDto> getMembers() {
        return memberService.findMembers();
    }


    @GetMapping("/users")
    public List<MemberResponseDto> getUser() {
        List<MemberResponseDto> members = memberService.getAllMembersWithTeams();
        return members;
    }

//    @GetMapping("/test")
//    public void persistAndFind() {
//        memberService.persistAndFind();
//    }

//    // 전체 멤버 페이징 조회
//    @GetMapping
//    public Page<MemberResponseDto> getMembers(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//        return memberService.getMembers(page, size);
//    }
//
//    // 이름 검색 + 페이징
//    @GetMapping("/search")
//    public Page<MemberResponseDto> searchMembers(
//            @RequestParam String keyword,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//        return memberService.searchMembers(keyword, page, size);
//    }
}
