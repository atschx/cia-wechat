package im.cia.wechat.processor.domain.repository;

import org.springframework.data.repository.CrudRepository;

import im.cia.wechat.processor.domain.model.UserStatus;

public interface UserStatusRepository extends CrudRepository<UserStatus, String> {

}
