package cn.zhanx.ke.cheng.mapper;

import cn.zhanx.ke.cheng.domain.PlayRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayRecordMapper {

    int saveRecord(PlayRecord playRecord);

}
