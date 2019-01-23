package com.onionknight.data4dota2.service.impl;

import com.onionknight.data4dota2.entity.Skill;
import com.onionknight.data4dota2.mapper.SkillMapper;
import com.onionknight.data4dota2.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author :fdy
 * @Date :Created in 10:30 2019/1/7
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
@Service(value = "skillService")
public class SkillServiceImpl implements SkillService {
    @Autowired
    SkillMapper skillMapper;
    @Override
    public List<Skill> getSkills() {
        return skillMapper.getSkills();
    }
}
