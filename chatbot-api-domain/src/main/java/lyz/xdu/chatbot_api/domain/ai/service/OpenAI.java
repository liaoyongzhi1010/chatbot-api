package lyz.xdu.chatbot_api.domain.ai.service;

import com.alibaba.fastjson.JSON;
import lyz.xdu.chatbot_api.domain.ai.IOpenAI;
import lyz.xdu.chatbot_api.domain.ai.model.aggregates.AIAnswer;
import lyz.xdu.chatbot_api.domain.ai.model.vo.Choices;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class OpenAI implements IOpenAI {

    @Value("${chatbot-api.openAiKey}")
    private String openAiKey;
    @Value("${chatbot-api.model}")
    private String model;

    @Override
    public String doChatGPT(String question) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.chatanywhere.tech/v1/chat/completions");
        post.addHeader("Authorization", "Bearer " + openAiKey);
        post.addHeader("Content-Type", "application/json; charset=UTF-8");

        String paramJson = "{\n" + "     \"model\": \"" + model + "\",\n" + "     \"messages\": [{\"role\": \"user\", \"content\": \"" + question + "\"}],\n" + "     \"temperature\": 0.7\n" + "   }";

        StringEntity stringEntity = new StringEntity(paramJson, "UTF-8");
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            AIAnswer aiAnswer = JSON.parseObject(jsonStr, AIAnswer.class);
            StringBuilder answers = new StringBuilder();
            List<Choices> choices = aiAnswer.getChoices();
            for (Choices choice : choices) {
                answers.append(choice.getMessage().getContent());
            }
            return answers.toString();
        } else {
            throw new RuntimeException("api.openai.com Err Code is " + response.getStatusLine().getStatusCode());
        }


    }
}
