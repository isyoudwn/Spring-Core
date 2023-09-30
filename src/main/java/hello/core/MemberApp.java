package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        /**
         * ApplicationContext는 스프링 컨테이너임 -> 모든 @Bean을 관리
         * ApplicationContext를 생성하기 위해서는 new AnnotationConfigApplicationContext(); 사용
         * parameter로 환경설정 정보를 넣어줌. (AppConfig)
         * AppConfig에 있는 환경설정 정보들을 스프링컨테이너에 넣어줌.
         **/

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        /**
         * getBean으로 객체를 찾아 옴. 이때 객체(빈)의 이름은 메소드의 이름과 동일함.
         * getBean의 두 번째 파라미터는 타입을 의미.
         * 즉 기존에는 AppConfing에서 직접 객체를 조회하였지만, 스프링 컨테이너를 통해서 빈(객체)를 조회하여 필요한 객체를 찾아야 함
         **/
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);


        Member member = new Member(1L, "WOODZ", Grade.VIP);

        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
