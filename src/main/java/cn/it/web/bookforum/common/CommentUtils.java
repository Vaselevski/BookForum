package cn.it.web.bookforum.common;

import cn.it.web.bookforum.entityclass.Comment;

import java.util.*;

public class CommentUtils {

    public static List<Comment> processComments(List<Comment> comments) {
        // 存储父评论和子评论的映射
        Map<Integer, List<Comment>> commentMap = new HashMap<>();
        // 存储最终排序后的评论列表
        List<Comment> sortedComments = new ArrayList<>();
        // 将评论按父评论ID进行分组
        for (Comment comment : comments) {
            if (comment.getParentCommentId() == null) {
                sortedComments.add(comment);  // 父评论直接加入结果列表
            } else {
                commentMap.computeIfAbsent(comment.getParentCommentId(), k -> new ArrayList<>()).add(comment);
            }
        }
        // 对父评论按时间进行排序
        sortedComments.sort(Comparator.comparing(Comment::getCommentCreateAt));
        // 将子评论按时间排序并插入到相应的父评论后面
        List<Comment> resultComments = new ArrayList<>();
        for (Comment parentComment : sortedComments) {
            resultComments.add(parentComment);  // 添加父评论
            List<Comment> children = commentMap.get(parentComment.getCommentId());
            if (children != null) {
                // 按时间排序子评论
                children.sort(Comparator.comparing(Comment::getCommentCreateAt));
                resultComments.addAll(children);  // 添加子评论
            }
        }
        return resultComments;  // 返回排序后的评论列表
    }

}
