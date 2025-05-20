package mymsa.board.article.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mymsa.board.article.dto.response.ArticleResponse;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

public class ArticleApiTest {
    RestClient restClient = RestClient.create("http://localhost:9000");

    @Test
    void createArticleTest() {
        ArticleResponse response = createArticle(new ArticleCreateRequest(
                "title", "content", 11L, 12L
        ));
        System.out.println("response = " + response);
    }

    ArticleResponse createArticle(ArticleCreateRequest request) {
        return restClient.post()
                .uri("/v1/articles")
                .body(request)
                .retrieve()
                .body(ArticleResponse.class);
    }

    @Test
    void getArticleTest() {
        ArticleResponse response = getArticle(183097215375630336L);
        System.out.println("response = " + response);
    }

    ArticleResponse getArticle(Long articleId) {
        return restClient.get()
                .uri("/v1/articles/{articleId}", articleId)
                .retrieve()
                .body(ArticleResponse.class);
    }

    @Test
    void updateArticleTest() {
        update(183093594714005504L);
        ArticleResponse response = getArticle(183093594714005504L);
        System.out.println("response = " + response);
    }

    void update(Long articleId) {
        restClient.put()
                .uri("/v1/articles/{articleId}", articleId)
                .body(new ArticleUpdateRequest("title 2", "content 2"))
                .retrieve();
    }

    @Test
    void deleteArticleTest() {
        restClient.delete()
                .uri("/v1/articles/{articleId}", 183093594714005504L)
                .retrieve();
    }

    @Getter
    @AllArgsConstructor
    static class ArticleCreateRequest {
        private String title;
        private String content;
        private Long writerId;
        private Long boardId;
    }

    @Getter
    @AllArgsConstructor
    static class ArticleUpdateRequest {
        private String title;
        private String content;
    }
}
