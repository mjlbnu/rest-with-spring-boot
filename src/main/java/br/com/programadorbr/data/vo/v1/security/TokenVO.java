package br.com.programadorbr.data.vo.v1.security;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class TokenVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String userName;
    private Boolean authenticated;
    private Date created;
    private Date expiration;
    private String accessToken;
    private String refreshToken;

    public TokenVO() {
    }

    public TokenVO(String userName,
                   Boolean authenticated,
                   Date created,
                   Date expiration,
                   String accessToken,
                   String refreshToken) {
        this.userName = userName;
        this.authenticated = authenticated;
        this.created = created;
        this.expiration = expiration;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TokenVO tokenVO = (TokenVO) o;

        if (!Objects.equals(userName, tokenVO.userName)) return false;
        if (!Objects.equals(authenticated, tokenVO.authenticated))
            return false;
        if (!Objects.equals(created, tokenVO.created)) return false;
        if (!Objects.equals(expiration, tokenVO.expiration)) return false;
        if (!Objects.equals(accessToken, tokenVO.accessToken)) return false;
        return Objects.equals(refreshToken, tokenVO.refreshToken);
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (authenticated != null ? authenticated.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (expiration != null ? expiration.hashCode() : 0);
        result = 31 * result + (accessToken != null ? accessToken.hashCode() : 0);
        result = 31 * result + (refreshToken != null ? refreshToken.hashCode() : 0);
        return result;
    }

}
