package com.aolifu.elasticsearch;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aolifu.elasticsearch.entity.ArticleEntity;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsRequest;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsResponse;
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
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetMappingsRequest;
import org.elasticsearch.client.indices.GetMappingsResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EsRestClientTest {

    private HttpHost httpHost;

    private Node node;

    private RestClientBuilder restClientBuilder;

    private RestHighLevelClient restHighLevelClient;

    private RestClient restClient;

    private static final String TEST_INDEX = "aolifu_connect";

    @Before
    public void before() {
        httpHost = new HttpHost("localhost", 9200);
        node = new Node(httpHost);

        restClientBuilder = RestClient.builder(node);
        restClient = restClientBuilder.build();
        restHighLevelClient = new RestHighLevelClient(restClientBuilder);
        //需要用户名和密码的认证
//        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("userName", "password"));
//        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("127.0.0.1", 9300))
//            .setHttpClientConfigCallback(httpAsyncClientBuilder -> httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider));

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
        article.setId("5");
        article.setContent("test");
        IndexRequest indexRequest = new IndexRequest(TEST_INDEX);
        indexRequest
            .id(System.currentTimeMillis() + "")
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
        sourceBuilder.query(QueryBuilders.termQuery("id", 2));
        searchRequest.source(sourceBuilder);

        final SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        if (search.getHits().getHits().length == 0) {
            System.out.println("no data");
            return;
        }
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
        searchSourceBuilder.from(0).size(3);
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

    @Test
    public void searchShardNumberTest() throws IOException {
        GetSettingsRequest getSettingsRequest = new GetSettingsRequest();
        getSettingsRequest.indices(TEST_INDEX);
        getSettingsRequest.names("index.number_of_shards");
        final GetSettingsResponse settings = restHighLevelClient.indices().getSettings(getSettingsRequest, RequestOptions.DEFAULT);
        System.out.println(settings);
    }

    @Test
    public void searchShardInfoTest() throws IOException {
        Request request = new Request(HttpGet.METHOD_NAME, "/aolifu_connect/_search_shards");
        final Response response = restClient.performRequest(request);
        System.out.println(response);
        final InputStream content = response.getEntity().getContent();
        byte arr[]=new byte[1024];
        int len = content.read(arr);
        System.out.println(new String(arr, 0, len));
    }

    @Test
    public void searchArticleByShardTest() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(TEST_INDEX);
        searchRequest.preference("_shards:0");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        final RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("id");
        // sourceBuilder.query(QueryBuilders.termQuery("id", 2));
        sourceBuilder.query(rangeQueryBuilder.gte(1));
        searchRequest.source(sourceBuilder);

        final SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        if (search.getHits().getHits().length == 0) {
            System.out.println("no data");
            return;
        }
        System.out.println(search.getHits().getHits()[0].getSourceAsString());
    }

    @Test
    public void parsePrimaryShard() throws IOException {
        String str = "{\n" +
            "  \"nodes\" : {\n" +
            "    \"mT0DvEaeQDKXVOrsfqAm9g\" : {\n" +
            "      \"name\" : \"aolifu-2.local\",\n" +
            "      \"ephemeral_id\" : \"Pi-ID_96T6C1sXbIhrLLPg\",\n" +
            "      \"transport_address\" : \"127.0.0.1:9300\",\n" +
            "      \"attributes\" : {\n" +
            "        \"ml.machine_memory\" : \"8589934592\",\n" +
            "        \"xpack.installed\" : \"true\",\n" +
            "        \"ml.max_open_jobs\" : \"20\"\n" +
            "      }\n" +
            "    }\n" +
            "  },\n" +
            "  \"indices\" : {\n" +
            "    \"aolifu_connect\" : { }\n" +
            "  },\n" +
            "  \"shards\" : [\n" +
            "    [\n" +
            "      {\n" +
            "        \"state\" : \"STARTED\",\n" +
            "        \"primary\" : true,\n" +
            "        \"node\" : \"mT0DvEaeQDKXVOrsfqAm9g\",\n" +
            "        \"relocating_node\" : null,\n" +
            "        \"shard\" : 0,\n" +
            "        \"index\" : \"aolifu_connect\",\n" +
            "        \"allocation_id\" : {\n" +
            "          \"id\" : \"RY_36kOJR822OnthFq6L_w\"\n" +
            "        }\n" +
            "      }\n" +
            "    ]\n" +
            "  ]\n" +
            "}\n";
        Request request = new Request(HttpGet.METHOD_NAME, "/aolifu_connect/_search_shards");
        final Response response = restClient.performRequest(request);
        System.out.println(response);
        final InputStream content = response.getEntity().getContent();
        byte arr[]=new byte[1024];
        int len = content.read(arr);
        str = new String(arr, 0, len);
        final JSONObject jsonObject = JSON.parseObject(str);
        final List<Map.Entry<String, Object>> shards = jsonObject.entrySet().stream().filter(entry -> entry.getKey().equals("shards")).collect(Collectors.toList());
        final JSONArray value = (JSONArray) shards.get(0).getValue();
        final Iterator<Object> iterator = value.iterator();
        while (iterator.hasNext()) {
            final Object next = iterator.next();
            final JSONObject object = (JSONObject) ((JSONArray) next).get(0);
            if (object.getBoolean("primary")) {
                System.out.println(object.getInteger("shard"));
            }
        }
    }

    @Test
    public void searchMapping() throws IOException {
        GetMappingsRequest request = new GetMappingsRequest().indices(TEST_INDEX);
        final GetMappingsResponse mapping = restHighLevelClient.indices().getMapping(request, RequestOptions.DEFAULT);
        System.out.println(mapping.mappings().get(TEST_INDEX).sourceAsMap());
    }



}
