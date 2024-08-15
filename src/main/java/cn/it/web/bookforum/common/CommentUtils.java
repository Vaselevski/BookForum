package cn.it.web.bookforum.common;

import cn.it.web.bookforum.comment.Comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentUtils {

    public static Map<String, Object> processComments(List<Comment> comments) {
        // 存储父评论和子评论的映射
        Map<Integer, List<Comment>> commentMap = new HashMap<>();
        // 存储最终的前端格式化数据
        List<Map<String, Object>> formattedComments = new ArrayList<>();

        // 将评论按父评论ID进行分组
        for (Comment comment : comments) {
            if (comment.getParentCommentId() == null) {
                formattedComments.add(formatComment(comment, new ArrayList<>()));
            } else {
                commentMap.computeIfAbsent(comment.getParentCommentId(), k -> new ArrayList<>()).add(comment);
            }
        }

        // 添加子评论到父评论中
        for (Map<String, Object> parentComment : formattedComments) {
            int parentId = (int) parentComment.get("commentId");
            List<Comment> children = commentMap.get(parentId);
            if (children != null) {
                parentComment.put("children", formatChildrenComments(children));
            }
        }

        return Map.of("comments", formattedComments);
    }

    private static Map<String, Object> formatComment(Comment comment, List<Comment> children) {
        Map<String, Object> formatted = new HashMap<>();
        formatted.put("commentId", comment.getCommentId());
        formatted.put("userId", comment.getUserId());
        formatted.put("username", comment.getUsername());
        formatted.put("parentCommentId", comment.getParentCommentId());
        formatted.put("bookId", comment.getBookId());
        formatted.put("commentCreateAt", comment.getCommentCreateAt());
        formatted.put("commentLikesCount", comment.getCommentLikesCount());
        formatted.put("comment", comment.getComment());
        formatted.put("children", children);
        return formatted;
    }

    private static List<Map<String, Object>> formatChildrenComments(List<Comment> comments) {
        List<Map<String, Object>> formattedChildren = new ArrayList<>();
        for (Comment comment : comments) {
            formattedChildren.add(formatComment(comment, new ArrayList<>()));
        }
        return formattedChildren;
    }
}
