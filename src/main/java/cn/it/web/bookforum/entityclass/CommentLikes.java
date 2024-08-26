package cn.it.web.bookforum.entityclass;

public class CommentLikes {
    private int userid;
    private int commentid;
    public CommentLikes() {}
    public void setUserid(int userid) {
        this.userid = userid;
    }
    public int getUserid() {
        return userid;
    }
    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }
    public int getCommentid() {
        return commentid;
    }
}
