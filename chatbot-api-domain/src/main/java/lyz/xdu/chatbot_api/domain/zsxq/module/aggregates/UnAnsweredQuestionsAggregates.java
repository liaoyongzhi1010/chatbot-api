package lyz.xdu.chatbot_api.domain.zsxq.module.aggregates;

import lyz.xdu.chatbot_api.domain.zsxq.module.res.RespDate;

public class UnAnsweredQuestionsAggregates {

    private boolean succeeded;
    private RespDate resp_data;

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public RespDate getResp_data() {
        return resp_data;
    }

    public void setResp_data(RespDate resp_data) {
        this.resp_data = resp_data;
    }
}
