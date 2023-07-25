package helloServlet.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    @DisplayName("저장소에 회원 저장히기")
    void save(){
        //given
        Member userA = new Member("userA", 20);

        //when
        Member save = memberRepository.save(userA);

        //then
        Member byId = memberRepository.findById(save.getId());
        assertThat(byId).isEqualTo(save);
    }

    @Test
    @DisplayName("모든 회원 조회하기")
    void findAll(){
        //given
        Member userA = new Member("userA", 20);
        Member userB = new Member("userB", 22);

        Member saveUserA = memberRepository.save(userA);
        Member saveUserB = memberRepository.save(userB);
        //when
        List<Member> result = memberRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(userA,userB);

    }
}