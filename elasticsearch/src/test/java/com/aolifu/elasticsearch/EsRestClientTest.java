package com.aolifu.elasticsearch;

import cn.hutool.json.JSONUtil;
import com.aolifu.elasticsearch.entity.ArticleEntity;
import java.io.IOException;
import java.util.Date;
import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EsRestClientTest {

    private HttpHost httpHost;

    private Node node;

    private RestClientBuilder restClientBuilder;

    private RestHighLevelClient restHighLevelClient;

    private static final String TEST_INDEX = "aolifu_connect";

    @Before
    public void before() {
        httpHost = new HttpHost("localhost", 9200);
        node = new Node(httpHost);

        restClientBuilder = RestClient.builder(node);
        restHighLevelClient = new RestHighLevelClient(restClientBuilder);
    }

    @After
    public void after() throws IOException {
        restHighLevelClient.close();
    }

    @Test
    public void createIndexTest() throws IOException {

        final IndicesClient indices = restHighLevelClient.indices();
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(TEST_INDEX);
        final CreateIndexResponse response = indices.create(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println(response);
    }


    @Test
    public void saveArticleTest() throws IOException {
        final ArticleEntity article = buildArticleEntity();
        article.setId("3");
        IndexRequest indexRequest = new IndexRequest(TEST_INDEX);
        indexRequest.id(System.currentTimeMillis() + "")
            .source(JSONUtil.toJsonStr(article), XContentType.JSON);
        final IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(response.status());

    }

    @Test
    public void updateArticleTest() throws IOException {
        final ArticleEntity article = buildArticleEntity();
        article.setId("2");
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(TEST_INDEX)
            .id("1665371688635")
            .doc(JSONUtil.toJsonStr(article), XContentType.JSON)
            .docAsUpsert();
        final UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(updateResponse.status());
    }

    @Test
    public void deleteArticleTest() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.index(TEST_INDEX)
            .id("1665372086596");
        final DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(deleteResponse.status());
    }

    @Test
    public void searchArticleByIdTest() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(TEST_INDEX);

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("id", 1));
        searchRequest.source(sourceBuilder);

        final SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(search.getHits().getHits()[0].getSourceAsString());
    }

    @Test
    public void searchArticleGreaterThanIdTest() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(TEST_INDEX);

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        final RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("id");
        rangeQueryBuilder.gte(1).lte(10);

        sourceBuilder.query(rangeQueryBuilder);
        searchRequest.source(sourceBuilder);

        final SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse.getHits().getHits().length);
    }

    @Test
    public void searchScroll() throws IOException {
        String scrollId;
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(TEST_INDEX);
        searchRequest.scroll(TimeValue.timeValueMinutes(1));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(0).size(1);
        searchRequest.source(searchSourceBuilder);
        final SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(search.getHits().getHits().length);

        SearchScrollRequest searchScrollRequest = new SearchScrollRequest();
        searchScrollRequest.scrollId(search.getScrollId());
        final SearchResponse searchResponse = restHighLevelClient.scroll(searchScrollRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse.getHits().getHits().length);

    }

    private ArticleEntity buildArticleEntity() {
        ArticleEntity article = new ArticleEntity();
        article.setId("1");
        article.setContent("Hello  World");
        article.setCreateTime(new Date());
        article.setTitle("Test tile");
        article.setUserId(1);
        return article;
    }

}
