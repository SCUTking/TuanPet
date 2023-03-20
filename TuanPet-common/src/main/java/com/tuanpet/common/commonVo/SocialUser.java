package com.tuanpet.common.commonVo;

public class SocialUser {
    //    "session_key": "O8RH9G8Ffjc+QdXcyYtUSg==",
    //    "openid": "ouVcW40tfsrif3vsCzfF7Er4jNm8",
    //    "unionid": "o9XLI6E9JQ_iNaoJSDquzZB3QfIg"}
    public String session_key;
    public String openid;
    public String unionid;

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getOpenid() {
        return openid;
    }

    @Override
    public String toString() {
        return "SocialUser{" +
                "session_key='" + session_key + '\'' +
                ", openid='" + openid + '\'' +
                ", unionid='" + unionid + '\'' +
                '}';
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
