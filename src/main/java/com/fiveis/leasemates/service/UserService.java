package com.fiveis.leasemates.service;

import com.fiveis.leasemates.domain.PageBlockDTO;
import com.fiveis.leasemates.domain.Pageable;
import com.fiveis.leasemates.domain.dto.community.PostDTO;
import com.fiveis.leasemates.domain.dto.user.JoinDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    //회원가입
    Boolean join(JoinDTO joinDTO);

    //나의 게시글목록보이기
    List<PostDTO> userPostPagination(String userNo, Pageable pageable);

    //나의 게시글 페이지네이션 버튼 뿌리기
    PageBlockDTO userPostPaginationBlock(int blockSize, Pageable pageable, String userNo);

}
