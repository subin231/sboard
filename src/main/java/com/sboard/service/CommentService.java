package com.sboard.service;

import com.sboard.dto.CommentDTO;
import com.sboard.entity.Comment;
import com.sboard.entity.User;
import com.sboard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    public CommentDTO insertComment(CommentDTO commentDTO) {

        Comment comment = modelMapper.map(commentDTO, Comment.class);

        // CommentDTO의 writer가 Comment 엔티티에는 writer 속성이 없기 때문에 직접 댓글 작성자 아이디를 설정
        User user = User.builder()
                        .uid(commentDTO.getWriter())
                        .build();

        comment.setUser(user);
        log.info(comment);

        Comment savedComment = commentRepository.save(comment);

        return modelMapper.map(savedComment, CommentDTO.class);
    }

    public List<CommentDTO> selectCommentAll() {
        return null;
    }

    public CommentDTO selectComment(int no) {
        return null;
    }

    public void updateComment(CommentDTO commentDTO) {

    }

    public void deleteComment(int no) {

    }
}
