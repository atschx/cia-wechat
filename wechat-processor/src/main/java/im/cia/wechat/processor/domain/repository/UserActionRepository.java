package im.cia.wechat.processor.domain.repository;

import org.springframework.data.repository.CrudRepository;

import im.cia.wechat.processor.domain.model.UserAction;

public interface UserActionRepository extends CrudRepository<UserAction, String> {

}
