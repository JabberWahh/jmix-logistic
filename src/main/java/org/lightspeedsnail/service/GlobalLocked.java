package org.lightspeedsnail.service;

import org.lightspeedsnail.entity.DocumentsAndReferences;
import org.lightspeedsnail.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class GlobalLocked {

    private final Map<DocumentsAndReferences, Map<User, LocalDateTime>> lockedEntities = new HashMap<>();

    public LockInfo isLocked(DocumentsAndReferences entity, User user) {
        Boolean isLocked = lockedEntities.containsKey(entity);
        LockInfo lockInfo = new LockInfo();
        if (!isLocked) {
            HashMap<User, LocalDateTime> hm = new HashMap<>();
            hm.put(user, LocalDateTime.now());
            lockedEntities.put(entity, hm);
            lockInfo.setLocked(isLocked);
            lockInfo.setUser(user);
            lockInfo.setLocalDateTime(LocalDateTime.now());
        }
        else{
            Map<User, LocalDateTime> hm = lockedEntities.get(entity);
            for (Map.Entry<User, LocalDateTime> entry : hm.entrySet()) {
                lockInfo.setLocked(isLocked);
                lockInfo.setUser(entry.getKey());
                lockInfo.setLocalDateTime(entry.getValue());
            }
        }
        return lockInfo;
    }

    public void unlock(DocumentsAndReferences entity, User user) {
        Map<User, LocalDateTime> hm = lockedEntities.get(entity);
        if (hm != null) {
            if (hm.containsKey(user)) {
                lockedEntities.remove(entity);
            }
        }
    }

    public class LockInfo {

        private Boolean locked;
        private User user;
        private LocalDateTime localDateTime;

        public LockInfo() {
        }

        public LockInfo(Boolean locked, User user, LocalDateTime localDateTime) {
            this.locked = locked;
            this.user = user;
            this.localDateTime = localDateTime;
        }

        public Boolean getLocked() {
            return locked;
        }

        public User getUser() {
            return user;
        }

        public LocalDateTime getLocalDateTime() {
            return localDateTime;
        }

        public void setLocked(Boolean locked) {
            this.locked = locked;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public void setLocalDateTime(LocalDateTime localDateTime) {
            this.localDateTime = localDateTime;
        }
    }

}
