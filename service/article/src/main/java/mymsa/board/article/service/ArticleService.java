package mymsa.board.article.service;

import kuke.board.common.snowflake.Snowflake;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mymsa.board.article.dto.request.ArticleCreateRequest;
import mymsa.board.article.dto.request.ArticleUpdateRequest;
import mymsa.board.article.dto.response.ArticlePageResponse;
import mymsa.board.article.dto.response.ArticleResponse;
import mymsa.board.article.entity.Article;
import mymsa.board.article.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {
    private final Snowflake snowflake = new Snowflake();
    private final ArticleRepository articleRepository;

    // 게시글 생성
    @Transactional
    public ArticleResponse createArticle(ArticleCreateRequest request) {
        Article article = articleRepository.save(
                Article.create(snowflake.nextId(), request.getTitle(), request.getContent(), request.getBoardId(), request.getWriterId())
        );
        return ArticleResponse.from(article);
    }

    // 게시글 조회
    public ArticleResponse getArticle(Long articleId) {
        return ArticleResponse.from(articleRepository.findById(articleId).orElseThrow());
    }

    // 게시글 목록 조회(번호 기반 페이지)
    public ArticlePageResponse getAllArticles(Long boardId, Long page, Long pageSize) {
        log.info("요청 들어옴: getAllArticles()");
        return ArticlePageResponse.of(
                articleRepository.findAll(boardId, (page - 1) * pageSize, pageSize).stream()
                        .map(ArticleResponse::from)
                        .toList(),
                articleRepository.count(
                        boardId,
                        PageLimitCalculator.calculatePageLimit(page, pageSize, 10L)
                )
        );
    }

    // 게시글 수정
    @Transactional
    public ArticleResponse updateArticle(Long articleId, ArticleUpdateRequest request) {
        Article article = articleRepository.findById(articleId).orElseThrow();
        article.update(request.getTitle(), request.getContent());
        return ArticleResponse.from(article);
    }

    // 게시글 삭제
    @Transactional
    public void deleteArticle(Long articleId) {
        articleRepository.deleteById(articleId);
    }

}
