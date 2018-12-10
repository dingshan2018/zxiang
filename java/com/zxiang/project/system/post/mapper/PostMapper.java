package com.zxiang.project.system.post.mapper;

import java.util.List;

import com.zxiang.project.system.post.domain.Post;
import com.zxiang.project.system.user.domain.User;

/**
 * 岗位信息 数据层
 * 
 * @author zxiang
 */
public interface PostMapper
{

    /**
     * 查询岗位数据集合
     * 
     * @param post 岗位信息
     * @return 岗位数据集合
     */
    public List<Post> selectPostList(Post post);

    /**
     * 查询所有岗位
     * 
     * @return 岗位列表
     */
    public List<Post> selectPostAll();

    /**
     * 根据用户ID查询岗位
     * 
     * @param userId 用户ID
     * @return 岗位列表
     */
    public List<Post> selectPostsByUserId(Long userId);

    /**
     * 通过岗位ID查询岗位信息
     * 
     * @param postId 岗位ID
     * @return 角色对象信息
     */
    public Post selectPostById(Long postId);

    /**
     * 批量删除岗位信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePostByIds(Long[] ids);

    /**
     * 修改岗位信息
     * 
     * @param post 岗位信息
     * @return 结果
     */
    public int updatePost(Post post);

    /**
     * 新增岗位信息
     * 
     * @param post 岗位信息
     * @return 结果
     */
    public int insertPost(Post post);

    /**
     * 校验岗位名称
     * 
     * @param postName 岗位名称
     * @return 结果
     */
    public Post checkPostNameUnique(String postName);

    /**
     * 校验岗位编码
     * 
     * @param postCode 岗位编码
     * @return 结果
     */
    public Post checkPostCodeUnique(String postCode);

    /**
     * 根据用户类型查询用户对象
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<User> selectUserListByUserType(String[] userTypes);
    
}