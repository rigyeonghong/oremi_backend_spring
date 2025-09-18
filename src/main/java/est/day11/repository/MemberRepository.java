package est.day11.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import est.day11.domain.Member;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // Spring Data JPA repository 메서드는 내부적으로 자체 트랜잭션 생성해서 동작
    // 해당 함수 실행시 트랜잭션 열리고
    // 쿼리 실행
    // 결과를 영속성 컨텍스트에 담고
    // 닫힘
    // 반환된 엔티티는 준영속(detached) : 영속성 컨텍스트에 저장되었다가 분리된 상태

    // Fetch Join으로 User + Orders 한번에 조회 가능
//    @Query("SELECT m FROM Member m JOIN FETCH m.team")
//    List<Member> findAll();

//    @Query("SELECT m FROM Member m JOIN FETCH m.team")
    List<Member> findAllByTeamIsNotNull();

    Optional<Object> findByName(String name);

}
