package com.yehwang.design.service;

import com.yehwang.design.entity.PersonalReportsEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YehWang
 * @since 2022-11-21 14:41:02
 */
public interface PersonalReportsService extends IService<PersonalReportsEntity> {

    /**
     * 生成一条报表信息
     */
    int createReport(PersonalReportsEntity personalReports);

    /**
     * 获取所有报表信息
     */
    List<PersonalReportsEntity> getAllReports();

    /**
     * 删除一条信息
     */
    int deleteReport(int id);
}
