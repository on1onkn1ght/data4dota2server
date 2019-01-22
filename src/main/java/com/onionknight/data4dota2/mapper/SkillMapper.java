package com.onionknight.data4dota2.mapper;

import com.onionknight.data4dota2.entity.Skill;

import java.util.List;

/**
 * @Author :fdy
 * @Date :Created in 10:32 2019/1/7
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public interface SkillMapper {
    void addSkills(List<Skill> skills);

    List<Skill> getSkills();
}
