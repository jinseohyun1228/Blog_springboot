package com.jsh.blog.model;

import lombok.Data;

@Data
public class OAuthToken {
  private String access_token;      // 사용자 액세스 토큰 값
  private String token_type;        // 토큰 타입, bearer로 고정
  private String refresh_token;     // 사용자 리프레시 토큰 값
  private int expires_in;           // 액세스 토큰과 ID 토큰의 만료 시간(초)참고
  private String scope;             // OpenID Connect가 활성화된 앱의 토큰 발급 요청인 경우, ID 토큰이 함께 발급되며 scope 값에 openid포함
  private int refresh_token_expires_in; //리프레시 토큰 만료 시간(초)
}
