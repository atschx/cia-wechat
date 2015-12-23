package im.cia.wechat.token.domain.repository;

import org.springframework.data.repository.CrudRepository;

import im.cia.wechat.token.domain.model.WeChatAccessToken;

public interface WeChatAccessTokenRepository extends CrudRepository<WeChatAccessToken, String> {

}
