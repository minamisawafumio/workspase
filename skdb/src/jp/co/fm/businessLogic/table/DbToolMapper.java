package jp.co.fm.businessLogic.table;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface DbToolMapper {
    @Select("${selectSql}")
    List<Map<String, Object>> select(@Param("selectSql") String sql);

    @Update("${updateSql}")
    Integer update(@Param("updateSql") String update);

    @Insert("${insertSql}")
    Integer insert(@Param("insertSql") String insert);

    @Delete("${deleteSql}")
    Integer delete(@Param("deleteSql") String delete);
}
