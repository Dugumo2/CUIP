package com.epoch.service.impl;

import com.epoch.model.po.Comment;
import com.epoch.mapper.CommentMapper;
import com.epoch.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuhaha
 * @since 2024-07-18
 */
@Service
@Slf4j
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
