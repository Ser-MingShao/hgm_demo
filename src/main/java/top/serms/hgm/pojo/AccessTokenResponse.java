package top.serms.hgm.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class AccessTokenResponse {
    private String access_token;
    private Long expires_in;
}