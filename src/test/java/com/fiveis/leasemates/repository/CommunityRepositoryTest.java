package com.fiveis.leasemates.repository;

import com.fiveis.leasemates.domain.Pageable;
import com.fiveis.leasemates.domain.dto.community.CmtDTO;
import com.fiveis.leasemates.domain.dto.community.PostDTO;
import com.fiveis.leasemates.domain.vo.CmtVO;
import com.fiveis.leasemates.domain.vo.LikeVO;
import com.fiveis.leasemates.domain.vo.PostVO;
import com.fiveis.leasemates.domain.vo.UserVO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class CommunityRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommunityRepository communityRepository;

    UserVO testUser;

    @BeforeEach
    void setUp() {
        testUser = UserVO.builder()
                .userNo("100")
                .id("100")
                .password("1234")
                .role("USER")
                .name("100")
                .email("100@naver.com")
                .phoneNumber("010-0000-0000")
                .build();

        userRepository.createUser(testUser);
    }

    @Test
    @DisplayName("postNo 얻기")
    void getPostNo() {
        Long no = communityRepository.getPostNo();
        System.out.println("postNo = " + no);
    }

    /**
     * 게시물 관련 기능
     */
    @Test
    @DisplayName("게시물 생성")
    void createPost() {
        PostVO postVO = PostVO.builder()
                .postNo(100L)
                .userNo(testUser.getUserNo())
                .title("게시물 제목")
                .content("게시물 내용")
                .build();
        communityRepository.createPost(postVO);
    }

    @Test
    @DisplayName("모든 게시물 출력")
    void findAll() {
        List<PostVO> posts = communityRepository.findPostAll();
        for(PostVO post : posts) System.out.println("post = " + post);
    }

    @Test
    @DisplayName("게시글 전체 개수")
    void findPostAllCount() {
        int postCount = communityRepository.findPostAllCount();
        System.out.println("게시글 전체 개수: " + postCount);
    }

    @Test
    @DisplayName("게시물 페이지네이션")
    void postPagination() {
        Pageable pageable = new Pageable();
        pageable.setPage(1);
        pageable.setPageSize(3);

        List<PostDTO> postDTOList = communityRepository.postPagination(pageable);

        for(PostDTO postDTO : postDTOList) System.out.println("postDTO = " + postDTO);
    }

    @Test
    @DisplayName("postNo로 게시물 찾기")
    void findById() {
        Optional<PostVO> post = communityRepository.findPostById(1L);
        System.out.println("post = " + post.get());
    }

    @Test
    @DisplayName("게시글 수정")
    void updatePost() {
        PostVO postVO = PostVO.builder()
                .postNo(1L)
                .title("게시물 제목 수정")
                .content("게시물 내용 수정")
                .build();

        communityRepository.updatePost(postVO);

        System.out.println("postVO = " + postVO);
    }

    @Test
    void deletePostById() {
        communityRepository.deletePostById(10L);
    }

    @Test
    @DisplayName("게시글 댓글 번호 얻기")
    void getCmtNo() {
        Long cmtNo = communityRepository.getCmtNo();
        System.out.println("cmtNo = " + cmtNo);
    }

    /**
     * 댓글관련 기능
     */
    @Test
    @DisplayName("댓글 생성")
    void createCmt() {
        CmtVO cmtVO = CmtVO.builder()
                .cmtNo(100L)
                .postNo(1L)
                .userNo(testUser.getUserNo())
                .content("댓글 내용")
                .build();

        communityRepository.createCmt(cmtVO);

        System.out.println("cmtVO = " + cmtVO);
    }

    @Test
    @DisplayName("한 게시물의 댓글들 조회")
    void findCmtAll() {
        List<CmtVO> cmts = communityRepository.findCmtAll(1L);
        for(CmtVO cmt : cmts) System.out.println("cmt = " + cmt);
    }

    @Test
    @DisplayName("게시물 댓글 페이지네이션")
    void cmtPagination() {
        Pageable pageable = new Pageable();
        pageable.setPage(1);
        pageable.setPageSize(3);

        List<CmtVO> cmtVOList = communityRepository.cmtPagination(1L, pageable);

        if(cmtVOList.size() == 0) System.out.println("리스트에 아무것도 없음");
        else {
            for(CmtVO cmtVO : cmtVOList) System.out.println("cmtDTO = " + cmtVO);
        }
    }

    @Test
    @DisplayName("댓글 1개 조회")
    void findCmtById() {
        Optional<CmtVO> cmtVO = communityRepository.findCmtById(1L);
        System.out.println("cmtVO = " + cmtVO.get());
    }

    @Test
    @DisplayName("댓글 수정")
    void updateCmt() {
        CmtVO cmtVO = CmtVO.builder()
                .cmtNo(1L)
                .content("댓글 내용 수정")
                .build();

        communityRepository.updateCmt(cmtVO);
        System.out.println("cmtVO = " + cmtVO);
    }

    @Test
    @DisplayName("댓글 삭제")
    void deleteCmtById() {
        communityRepository.deleteCmtById(10L);
    }

    @Test
    @DisplayName("댓글 카운트 해서 post 테이블에 업데이트")
    void updateCmtCnt() {
        communityRepository.updateCmtCnt(1L);
    }

    /**
     * 좋아요 관련 테스트
     */
    @Test
    @DisplayName("게시물 좋아요 했는지 여부")
    void findLikeById() {
        LikeVO likeVO = LikeVO.builder()
                .postNo(1L)
                .userNo(testUser.getUserNo())
                .build();

        int checkLike = communityRepository.findLikeById(likeVO);
        System.out.println("checkLike = " + checkLike);
    }

    @Test
    @DisplayName("게시물 좋아요 개수 post 테이블에 업데이트")
    void updateLikeCnt() {
        communityRepository.updateLikeCnt(1L);
    }

    @Test
    @DisplayName("좋아요 하기")
    void createLike() {
        LikeVO likeVO = LikeVO.builder()
                .postNo(1L)
                .userNo(testUser.getUserNo())
                .build();

        communityRepository.createLike(likeVO);
    }

    @Test
    @DisplayName("좋아요 취소")
    void deleteLikeById() {
        LikeVO likeVO = LikeVO.builder()
                .postNo(2L)
                .userNo(testUser.getUserNo())
                .build();

        communityRepository.createLike(likeVO);
        communityRepository.deleteLikeById(likeVO);
    }

}