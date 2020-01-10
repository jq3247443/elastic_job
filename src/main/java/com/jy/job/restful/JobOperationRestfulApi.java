/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.jy.job.restful;

import com.google.common.base.Optional;
import com.jy.job.lifecycle.domain.JobBriefInfo;
import com.jy.job.lifecycle.domain.ShardingInfo;
import com.jy.job.service.JobAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * 作业维度操作的RESTful API.
 *
 * @author caohao
 */
@RestController
@RequestMapping("/jobs")
public final class JobOperationRestfulApi {

    @Autowired
    private JobAPIService jobAPIService;
    
    /**
     * 获取作业总数.
     * 
     * @return 作业总数
     */
    @RequestMapping("/count")
    public int getJobsTotalCount() {
        return jobAPIService.getJobStatisticsAPI().getJobsTotalCount();
    }
    
    /**
     * 获取作业详情.
     * 
     * @return 作业详情集合
     */
    @RequestMapping
    public Collection<JobBriefInfo> getAllJobsBriefInfo() {
        return jobAPIService.getJobStatisticsAPI().getAllJobsBriefInfo();
    }
    
    /**
     * 触发作业.
     * 
     * @param jobName 作业名称
     */
    @RequestMapping("/{jobName}/trigger")
    public void triggerJob(@PathVariable("jobName") final String jobName) {
        jobAPIService.getJobOperatorAPI().trigger(Optional.of(jobName), Optional.<String>absent());
    }
    
    /**
     * 禁用作业.
     * 
     * @param jobName 作业名称
     */
    @RequestMapping("/{jobName}/disable")
    public void disableJob(@PathVariable("jobName") final String jobName) {
        jobAPIService.getJobOperatorAPI().disable(Optional.of(jobName), Optional.<String>absent());
    }
    
    /**
     * 启用作业.
     *
     * @param jobName 作业名称
     */
//    @RequestMapping("/{jobName}/disable")
    public void enableJob(@PathVariable("jobName") final String jobName) {
        jobAPIService.getJobOperatorAPI().enable(Optional.of(jobName), Optional.<String>absent());
    }
    
    /**
     * 终止作业.
     * 
     * @param jobName 作业名称
     */
    @RequestMapping("/{jobName}/shutdown")
    public void shutdownJob(@PathVariable("jobName") final String jobName) {
        jobAPIService.getJobOperatorAPI().shutdown(Optional.of(jobName), Optional.<String>absent());
    }
    
    /**
     * 获取分片信息.
     * 
     * @param jobName 作业名称
     * @return 分片信息集合
     */
    @RequestMapping("/{jobName}/sharding")
    public Collection<ShardingInfo> getShardingInfo(@PathVariable("jobName") final String jobName) {
        return jobAPIService.getShardingStatisticsAPI().getShardingInfo(jobName);
    }

    /**
     * Disable Sharding.
     *
     * @param jobName job name
     * @param item sharding item
     */
    @RequestMapping("/{jobName}/sharding/{item}/disable")
    public void disableSharding(@PathVariable("jobName") final String jobName, @PathVariable("item") final String item) {
        jobAPIService.getShardingOperateAPI().disable(jobName, item);
    }

    /**
     * EnableS Sharding.
     *
     * @param jobName job name
     * @param item sharding item
     */
//    @RequestMapping("/{jobName}/sharding/{item}/disable")
    public void enableSharding(@PathVariable("jobName") final String jobName, @PathVariable("item") final String item) {
        jobAPIService.getShardingOperateAPI().enable(jobName, item);
    }
}
