package mymsa.board.article.controller;

import lombok.RequiredArgsConstructor;
import mymsa.board.article.dto.request.ArticleCreateRequest;
import mymsa.board.article.dto.request.ArticleUpdateRequest;
import mymsa.board.article.dto.response.ArticleResponse;
import mymsa.board.article.service.ArticleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    // 게시글 작성
    @PostMapping("/v1/articles")
    public ArticleResponse createArticle(@RequestBody ArticleCreateRequest request) {
        return articleService.createArticle(request);
    }

    // 게시글 조회
    @GetMapping("/v1/articles/{articleId}")
    public ArticleResponse getArticle(@PathVariable Long articleId) {
        return articleService.getArticle(articleId);
    }

    // 게시글 수정
    @PutMapping("/v1/articles/{articleId}")
    public ArticleResponse updateArticle(@PathVariable Long articleId, @RequestBody ArticleUpdateRequest request) {
        return articleService.updateArticle(articleId, request);
    }

    // 게시글 삭제
    @DeleteMapping("/v1/articles/{articleId}")
    public void deleteArticle(@PathVariable Long articleId) {
        articleService.deleteArticle(articleId);
    }

}
