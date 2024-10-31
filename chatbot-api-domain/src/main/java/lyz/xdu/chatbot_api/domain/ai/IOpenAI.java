package lyz.xdu.chatbot_api.domain.ai;

import java.io.IOException;

public interface IOpenAI {

    String doChatGPT(String question) throws IOException;

}
