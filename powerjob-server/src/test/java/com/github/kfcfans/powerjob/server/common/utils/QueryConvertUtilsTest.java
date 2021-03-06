package com.github.kfcfans.powerjob.server.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.github.kfcfans.powerjob.common.PowerQuery;
import com.github.kfcfans.powerjob.common.response.JobInfoDTO;
import com.github.kfcfans.powerjob.server.persistence.core.model.JobInfoDO;
import com.github.kfcfans.powerjob.server.service.JobService;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.time.DateUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test QueryConvertUtils
 *
 * @author tjq
 * @since 2021/1/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QueryConvertUtilsTest {

    @Resource
    private JobService jobService;

    @Test
    void autoConvert() {
        JobInfoQuery jobInfoQuery = new JobInfoQuery();
        jobInfoQuery.setAppIdEq(1L);
        jobInfoQuery.setJobNameLike("DAG");
        jobInfoQuery.setStatusIn(Lists.newArrayList(1));
        jobInfoQuery.setGmtCreateGt(DateUtils.addDays(new Date(), -300));

        List<JobInfoDTO> list = jobService.queryJob(jobInfoQuery);
        System.out.println("size: " + list.size());
        System.out.println(JSONObject.toJSONString(list));
    }

    @Getter
    @Setter
    public static class JobInfoQuery extends PowerQuery {
        private String jobNameLike;
        private Date gmtCreateGt;
        private List<Integer> statusIn;
    }
}