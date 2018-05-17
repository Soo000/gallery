package com.kkwriter.gallery.entity.product;

import com.kkwriter.gallery.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by lisha on 2018/5/17 15:36.
 *
 * @author lisha
 */
@Entity(name = "gly_evaluate")
public class GlyProductEvaluate extends BaseEntity {
    private static final long serialVersionUID = 4950435110491990041L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluate_id")
    private Integer evaluateId;
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "reply_user_id")
    private Integer replyUserId;
    @Column(name = "status")
    private Integer status;
    @Column(name = "evaluate_content", length = 1000)
    private String evaluateContent;
    @Column(name = "evaluate_time")
    private Timestamp evaluateTime;
    @Column(name = "is_valid")
    private Integer valid;

    public Integer getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(Integer evaluateId) {
        this.evaluateId = evaluateId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Integer replyUserId) {
        this.replyUserId = replyUserId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public Timestamp getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(Timestamp evaluateTime) {
        this.evaluateTime = evaluateTime;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "GlyProductEvaluate{" +
                "evaluateId=" + evaluateId +
                ", productId=" + productId +
                ", userId=" + userId +
                ", replyUserId=" + replyUserId +
                ", status=" + status +
                ", evaluateContent='" + evaluateContent + '\'' +
                ", evaluateTime=" + evaluateTime +
                ", valid=" + valid +
                '}';
    }
}
