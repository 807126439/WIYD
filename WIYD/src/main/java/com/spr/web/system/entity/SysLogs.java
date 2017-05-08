package com.spr.web.system.entity;

import com.spr.core.annotations.DbField;
import com.spr.core.common.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class SysLogs extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String thread;

    private String className;

    private String method;

    private Date createTime;

    private String loglevel;

    private String msg;

    @DbField(name="THREAD")
    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread == null ? null : thread.trim();
    }

    @DbField(name="CLASS_NAME")
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    @DbField(name="METHOD")
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    @DbField(name="CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @DbField(name="LOGLEVEL")
    public String getLoglevel() {
        return loglevel;
    }

    public void setLoglevel(String loglevel) {
        this.loglevel = loglevel == null ? null : loglevel.trim();
    }

    @DbField(name="MSG")
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append("Super = ").append(super.toString());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", thread=").append(thread);
        sb.append(", className=").append(className);
        sb.append(", method=").append(method);
        sb.append(", createTime=").append(createTime);
        sb.append(", loglevel=").append(loglevel);
        sb.append(", msg=").append(msg);
        sb.append("]");
        return sb.toString();
    }
}