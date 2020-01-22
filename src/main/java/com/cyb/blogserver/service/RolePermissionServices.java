package com.cyb.blogserver.service;

import com.cyb.blogserver.domain.RolePermission;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolePermissionServices {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permission
     *
     * @mbg.generated Sat May 04 15:10:52 CST 2019
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permission
     *
     * @mbg.generated Sat May 04 15:10:52 CST 2019
     */
    int insert(RolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permission
     *
     * @mbg.generated Sat May 04 15:10:52 CST 2019
     */
    int insertSelective(RolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permission
     *
     * @mbg.generated Sat May 04 15:10:52 CST 2019
     */
    RolePermission selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permission
     *
     * @mbg.generated Sat May 04 15:10:52 CST 2019
     */
    int updateByPrimaryKeySelective(RolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permission
     *
     * @mbg.generated Sat May 04 15:10:52 CST 2019
     */
    int updateByPrimaryKey(RolePermission record);

    List<RolePermission> selectByRoleId(String roleId);
}