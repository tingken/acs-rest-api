/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.remote.player;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The purpose of this class is to encapsulate rest API to access
 * players system using spring RestTemplate.
 */
public class PlayerApiSpringImpl implements PlayerApi {
    private static class PlayerInterceptor implements ClientHttpRequestInterceptor {

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
                throws IOException {
            if (token != null) {
                request.getHeaders().add("Cookie", "JSESSIONID=" + token);
            }
            String content = new String(body);
            ClientHttpResponse resp = execution.execute(request, content.getBytes("gbk"));
            if (!resp.getHeaders().containsKey("Content-Type")) {
                resp.getHeaders().add("Content-Type", "application/json;charset=gbk");
            }
            return resp;
        }

    }

    private static class PlayerMessageConverterExtractor<T> implements ResponseExtractor<ResponseEntity<T>> {
        private static ObjectMapper objectMapper = new ObjectMapper();
        private final Class<T> responseClass;

        public PlayerMessageConverterExtractor(Class<T> responseType) {
            this.responseClass = (responseType instanceof Class ? (Class<T>) responseType : null);
        }

        public PlayerMessageConverterExtractor(Type responseType) {
            this.responseClass = (responseType instanceof Class ? (Class<T>) responseType : null);
        }

        /* (non-Javadoc)
         * @see org.springframework.web.client.HttpMessageConverterExtractor#extractData(org.springframework.http.client.ClientHttpResponse)
         */
        @Override
        public ResponseEntity<T> extractData(ClientHttpResponse response) throws IOException {
            T body = objectMapper.readValue(readStream(response.getBody()), this.responseClass);
            return ResponseEntity.status(response.getRawStatusCode()).headers(response.getHeaders()).body(body);
        }

        private String readStream(InputStream inputStream) throws IOException {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            return result.toString("gbk");
        }
    }

    private String restUri = "http://192.168.1.6";

    private static String token = null;

    private RestTemplate restTemplate;

    /**
     * Creates a new instance of <code>PlayerSystemClient</code>.
     */
    public PlayerApiSpringImpl(String restUri) {
        this.restUri = restUri;

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);//ms
        factory.setConnectTimeout(15000);//ms

        restTemplate = new RestTemplate(factory);
        List<ClientHttpRequestInterceptor> interceptorList = new ArrayList<ClientHttpRequestInterceptor>();
        interceptorList.add(new PlayerInterceptor());
        restTemplate.setInterceptors(interceptorList);
    }

    @Override
    public LoginResult login() {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUsername("admin");
        loginInfo.setPassword("admin");
        ResponseEntity<LoginResult> postForEntity = restTemplate.postForEntity(restUri + "/login", loginInfo,
                LoginResult.class);
        //        postForEntity.getHeaders().add("Content-Type", "application/json");
        LoginResult body = postForEntity.getBody();
        if (body.getRet() == 0) {
            token = body.getToken();
        }
        return body;
    }

    @Override
    public TermIdListResult getTermIdList() {
        return restTemplate.postForEntity(restUri + "/getTermIds", "", TermIdListResult.class).getBody();
    }

    @Override
    public TermStateListResult getTermStateList(List<Integer> idList) {
        HashMap<String, Object> requestBody = new HashMap<String, Object>();
        requestBody.put("TermIDs", idList);
        return postForEntity(restUri + "/getTermState", requestBody, TermStateListResult.class).getBody();
    }

    @Override
    public GroupsResult getGroups() {
        ResponseEntity<GroupsResult> resp = postForEntity(restUri + "/getGroups", "", GroupsResult.class);
        if (resp.getStatusCodeValue() >= 200 && resp.getStatusCodeValue() < 300) {
            return resp.getBody();
        }
        return null;
    }

    @Override
    public PlayerResult playText(TextPlayInfo requestBody) {
        ResponseEntity<PlayerResult> resp = restTemplate.postForEntity(restUri + "/TextPlay", requestBody,
                PlayerResult.class);
        if (resp.getStatusCodeValue() >= 200 && resp.getStatusCodeValue() < 300) {
            return resp.getBody();
        }
        return null;
    }

    private <T> ResponseEntity<T> postForEntity(String url, @Nullable Object request, Class<T> responseType,
            Object... uriVariables) throws RestClientException {

        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = new PlayerMessageConverterExtractor<T>(responseType);
        return restTemplate.execute(url, HttpMethod.POST, requestCallback, responseExtractor, uriVariables);
    }

}
