package lyz.xdu.chatbot_api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

public class ApiTest {

    @Test
    public void query_answered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/15555825814522/topics?scope=unanswered_questions&count=20");

        get.addHeader("Cookie", "zsxq_access_token=9AA7F13F-B373-35BC-2A11-51839A539E88_B4825967E5355A34; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22182884112882112%22%2C%22first_id%22%3A%22192d3bfaee1351-0a8e4ade5bb97c8-26011951-3686400-192d3bfaee218eb%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTkyZDNiZmFlZTEzNTEtMGE4ZTRhZGU1YmI5N2M4LTI2MDExOTUxLTM2ODY0MDAtMTkyZDNiZmFlZTIxOGViIiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiMTgyODg0MTEyODgyMTEyIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22182884112882112%22%7D%7D; abtest_env=product; zsxqsessionid=2540de1180cfa526b740515ed27c7211");
        get.addHeader("Content-Type", "application/json; charset=UTF-8");

        CloseableHttpResponse response = httpClient.execute(get);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/4848525444451488/answer");

        post.addHeader("Cookie", "zsxq_access_token=9AA7F13F-B373-35BC-2A11-51839A539E88_B4825967E5355A34; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22182884112882112%22%2C%22first_id%22%3A%22192d3bfaee1351-0a8e4ade5bb97c8-26011951-3686400-192d3bfaee218eb%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTkyZDNiZmFlZTEzNTEtMGE4ZTRhZGU1YmI5N2M4LTI2MDExOTUxLTM2ODY0MDAtMTkyZDNiZmFlZTIxOGViIiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiMTgyODg0MTEyODgyMTEyIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22182884112882112%22%7D%7D; abtest_env=product; zsxqsessionid=2540de1180cfa526b740515ed27c7211");
        post.addHeader("Content-Type", "application/json; charset=UTF-8");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"自己去百度！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, "UTF-8");
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void test_chatGPT() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.chatanywhere.tech/v1/chat/completions");
        post.addHeader("Authorization", "Bearer sk-Zri6YP9cvTkXkHWxmgxNjatRiPg76tFcSKhVMrmud3rd995D");
        post.addHeader("Content-Type", "application/json; charset=UTF-8");

        String paramJson = "{\n" +
                "     \"model\": \"gpt-4o-mini\",\n" +
                "     \"messages\": [{\"role\": \"user\", \"content\": \"为什么黄瓜是绿色的\"}],\n" +
                "     \"temperature\": 0.7\n" +
                "   }";

        StringEntity stringEntity = new StringEntity(paramJson, "UTF-8");
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }
}
