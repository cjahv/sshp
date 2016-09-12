package com.linkcubic.model.entity.user.notice;

import com.linkcubic.model.enums.user.notice.NoticeType;
import com.sshp.model.entity.BaseEntity;

import javax.persistence.*;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年七月01日 10:49
 */
@Entity
@Table(name = "lcv2_notice_user")
public class NoticeByUser extends BaseEntity {
    private Long userId;
    private Notice notice;
    private NoticeType type;
    private Boolean read;

    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @ManyToOne
    @JoinColumn(name = "notice_id")
    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public NoticeType getType() {
        return type;
    }

    public void setType(NoticeType type) {
        this.type = type;
    }

    @Basic
    @Column(name = "is_read")
    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean readid) {
        this.read = readid;
    }
}
